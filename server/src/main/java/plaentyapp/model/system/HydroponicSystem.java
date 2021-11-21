package plaentyapp.model.system;

import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import plaentyapp.model.configuration.ConfigurationNotFoundException;
import plaentyapp.model.configuration.Configuration;
import plaentyapp.model.system.actuator.Growlight;
import plaentyapp.model.system.actuator.Pump;
import plaentyapp.model.system.sensor.LightLevel;
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
import java.io.File;
import java.io.IOException;
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
		sensors.add(new PhSensorMock("mock pH sensor"));
		sensors.add(new EcSensorMock("mock EC sensor"));
		sensors.add(lightSensor);

		// TODO add default active configuration from repository, don't create it here
		Configuration defaultConfig = new Configuration();
		defaultConfig.setName("generic configuration");
		defaultConfig.setEcmin(0.8);
		defaultConfig.setEcmin(1.3);
		defaultConfig.setPhmax(6.5);
		defaultConfig.setPhmin(5.5);
		defaultConfig.setLightRequired(LightLevel.NORMAL);
		defaultConfig.setPumpoff(120);
		defaultConfig.setPumpon(5);
		activeConfiguration = configurationRepository.saveConfiguration(defaultConfig);

		startPumpCycle();
	}

	// not scheduled, as that cannot change the rates at runtime :c
	private void startPumpCycle() {
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
	}

	public SystemState getSystemState() {
		HashMap<Sensor, SensorData> allSensorData = new HashMap<>();
		sensors.getSensorList().forEach((s -> allSensorData.put(s, sensorDataRepository.getLastMeasurement(s))));
		return new SystemState(activeConfiguration, allSensorData, pump.isActuatorOn(), growlight.isActuatorOn());
	}

	@Scheduled(fixedRate=5*60000)
	public void updateSensors() {
		for (Sensor sensor : sensors.getSensorList()) {
			sensorDataRepository.saveMeasurement(sensor.takeMeasurement());
		}

		if(activeConfiguration.getLightRequired().minValue > sensorDataRepository.getLastMeasurement(lightSensor).getValue()) {
			growlight.turnActuatorOn();
		} else {
			growlight.turnActuatorOff();
		}
	}

	public Configuration updateActiveConfiguration(long configId) throws ConfigurationNotFoundException {
		activeConfiguration = configurationRepository.getConfiguration(configId);
		return activeConfiguration;
	}

	public Configuration getActiveConfiguration() {
		return activeConfiguration;
	}
}
