package io.swagger.sensormock;

import io.swagger.model.sensor.Sensor;

public class PhSensorMock extends Sensor {
	public PhSensorMock(String name) {
		super("mockPhSensor", "");
	}

	@Override
	public double takeMeasurement() {
		return (Math.random()*4.0)+4.0;
	}
}
