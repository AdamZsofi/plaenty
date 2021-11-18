package io.swagger.model.system;

import io.swagger.model.configuration.Configuration;
import io.swagger.model.system.sensor.LightLevel;
import io.swagger.model.system.actuator.Growlight;
import io.swagger.model.system.actuator.Pump;
import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensor.SensorContainer;
import io.swagger.model.system.sensor.SensorData;
import io.swagger.model.system.sensormock.EcSensorMock;
import io.swagger.model.system.sensormock.NaturalLightSensorMock;
import io.swagger.model.system.sensormock.PhSensorMock;
import io.swagger.repository.ConfigurationRepository;
import io.swagger.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
	SensorContainer sensors;

	Growlight growlight;
	Pump pump;
	Sensor lightSensor;
	private Configuration activeConfiguration;

	private Timer pumpOnTimer;
	private Timer pumpOffTimer;
	private Timer sensorTimer;

	private HydroponicSystem() {
		sensors.add(new PhSensorMock("mock pH sensor"));
		sensors.add(new EcSensorMock("mock EC sensor"));
		lightSensor = new NaturalLightSensorMock("mock light sensor");
		sensors.add(lightSensor);

		pump = new Pump();
		growlight = new Growlight(LocalTime.of(7, 0), LocalTime.of(18, 0));

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
		startSensorCycle();
	}

	private void startSensorCycle() {
		if(sensorTimer!=null) {
			sensorTimer.cancel();
			sensorTimer = new Timer();
			sensorTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					updateSensors();
				}
			}, 0, 5*60000);
		}
	}

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

	public void updateActiveConfiguration(long configId) throws RuntimeException {
		activeConfiguration = configurationRepository.getConfiguration(configId);
	}

	public Configuration getActiveConfiguration() {
		return activeConfiguration;
	}
}
