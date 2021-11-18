package io.swagger.model.system.sensormock;

import io.swagger.model.system.sensor.Sensor;

public class PhSensorMock extends Sensor {
	public PhSensorMock(String name) {
		super(name, "");
	}

	@Override
	public double takeMeasurement() {
		return (Math.random()*4.0)+4.0;
	}
}
