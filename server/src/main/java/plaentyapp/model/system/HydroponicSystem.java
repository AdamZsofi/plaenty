package plaentyapp.model.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import plaentyapp.model.configuration.ConfigurationNotFoundException;
import plaentyapp.model.configuration.Configuration;
import plaentyapp.model.system.actuator.Growlight;
import plaentyapp.model.system.actuator.Pump;
import plaentyapp.model.system.sensor.Sensor;
import plaentyapp.model.system.sensor.SensorContainer;
import plaentyapp.model.system.sensor.SensorData;
import plaentyapp.model.system.sensormock.EcSensorMock;
import plaentyapp.model.system.sensormock.NaturalLightSensorMock;
import plaentyapp.model.system.sensormock.PhSensorMock;
import plaentyapp.repository.ConfigurationRepository;
import plaentyapp.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A singleton class holding the configuration (sensors and actuators) of the system
 */
// TODO add configurations and persistent DBs
@Component
public class HydroponicSystem {
	private Logger logger = LoggerFactory.getLogger(HydroponicSystem.class);

	@Autowired
	private SensorDataRepository sensorDataRepository;
	@Autowired
	private ConfigurationRepository configurationRepository;
	@Autowired
	private SensorContainer sensors;

	private final Growlight growlight;
	private final Pump pump;
	private final Sensor lightSensor;
	private Configuration activeConfiguration;

	private Timer pumpOnTimer;
	private Timer pumpOffTimer;

	private HydroponicSystem() {
		lightSensor = new NaturalLightSensorMock("mock light sensor");
		pump = new Pump();
		growlight = new Growlight(LocalTime.of(7, 0), LocalTime.of(18, 0));
	}

	@PostConstruct
	private void initializeSystem() {
		logger.info("Initializing hydroponic system...");
		sensors.add(new PhSensorMock("mock pH sensor"));
		sensors.add(new EcSensorMock("mock EC sensor"));
		sensors.add(lightSensor);
		logger.debug("Sensors added to system");

		activeConfiguration = configurationRepository.getConfiguration(1);
		logger.debug("Active configuration set to default");
		startPumpCycle();
		logger.debug("Pump cycle started");
		logger.info("Initialization done");
	}

	// not scheduled, as that cannot change the rates at runtime
	private void startPumpCycle() {
		logger.info("Starting pump cycle, adding timers...");
		if(pumpOnTimer!=null) {
			pumpOnTimer.cancel();
		}
		if(pumpOffTimer!=null) {
			pumpOffTimer.cancel();
		}

		pumpOnTimer = new Timer();
		pumpOnTimer.scheduleAtFixedRate(
				new TimerTask() {
					 @Override
					 public void run() {
						 pump.turnActuatorOn();
					 }
				 },
				0,
				60L*1000*(activeConfiguration.getPumpon() + activeConfiguration.getPumpoff()));

		pumpOffTimer = new Timer();
		pumpOffTimer.scheduleAtFixedRate(
				new TimerTask() {
					@Override
					public void run() {
						pump.turnActuatorOff();
					}
				},
				60L*1000*activeConfiguration.getPumpon(),
				60L*1000*(activeConfiguration.getPumpon()+ activeConfiguration.getPumpoff()));
		logger.info("Pump cycle timers set");
	}

	public SystemState getSystemState() {
		logger.info("Fetching system state...");
		HashMap<Sensor, SensorData> allSensorData = new HashMap<>();
		sensors.getSensorList().forEach((s -> allSensorData.put(s, sensorDataRepository.getLastMeasurement(s))));
		return new SystemState(activeConfiguration, allSensorData, pump.isActuatorOn(), growlight.isActuatorOn());
	}

	@Scheduled(fixedRate=5*60000)
	public void updateSensors() {
		logger.info("Taking sensor measurements");
		for (Sensor sensor : sensors.getSensorList()) {
			sensorDataRepository.saveMeasurement(sensor.takeMeasurement());
		}

		logger.info("Setting growlight to proper setting:");
		if(activeConfiguration.getLightRequired().minValue > sensorDataRepository.getLastMeasurement(lightSensor).getValue()) {
			growlight.turnActuatorOn();
			logger.info("Growlight is now ON");
		} else {
			growlight.turnActuatorOff();
			logger.info("Growlight is now OFF");
		}
	}

	public Configuration updateActiveConfiguration(long configId) throws ConfigurationNotFoundException {
		logger.info("Updating active configuration");
		activeConfiguration = configurationRepository.getConfiguration(configId);
		logger.info("Active configuration set to new value: " + activeConfiguration);
 		return activeConfiguration;
	}

	public Configuration getActiveConfiguration() {
		logger.info("Fetching active configuration");
		return activeConfiguration;
	}

	@PreDestroy
	private void turnActuatorsOff() {
		logger.info("System is shutting down, turning actuators off...");
		growlight.turnActuatorOff();
		pump.turnActuatorOff();
		logger.info("Actuators turned off successfully");
	}
}
