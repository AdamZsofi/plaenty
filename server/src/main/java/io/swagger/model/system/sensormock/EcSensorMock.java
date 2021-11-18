package io.swagger.model.system.sensormock;

import io.swagger.model.system.sensor.Sensor;

public class EcSensorMock extends Sensor {
	public EcSensorMock(String name) {
		super(name, "dS/m");
	}

	@Override
	public double takeMeasurement() {
		return Math.random()+1.0;
	}
}
