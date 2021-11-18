package io.swagger.model.system;

import io.swagger.model.sensor.Sensor;

import java.util.HashSet;
import java.util.Set;

/**
 * A singleton class holding the configuration (sensors and actuators) of the system
 */
public class System {
	private final Set<Sensor> sensors = new HashSet<>();

	private static System instance = new System();
	private System() {
		sensors.add(new Sensor("pH sensor", ""));
		sensors.add(new Sensor("EC sensor", "dS/m"));
		sensors.add(new Sensor("light sensor", "lux"));
	}

	public System getInstance() {
		return instance;
	}

	public SystemState getSystemState() {

		return new SystemState();
	}
}
