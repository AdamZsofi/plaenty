package io.swagger.model.system;

import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensormock.EcSensorMock;
import io.swagger.model.system.sensormock.NaturalLightSensorMock;
import io.swagger.model.system.sensormock.PhSensorMock;

import java.util.HashSet;
import java.util.Set;

/**
 * A singleton class holding the configuration (sensors and actuators) of the system
 */
public class System {
	private final Set<Sensor> sensors = new HashSet<>();

	private static System instance = new System();
	private System() {
		sensors.add(new PhSensorMock("mock pH sensor"));
		sensors.add(new EcSensorMock("mock EC sensor"));
		sensors.add(new NaturalLightSensorMock("mock light sensor"));
	}

	public System getInstance() {
		return instance;
	}

	public SystemState getSystemState() {

		return new SystemState();
	}
}
