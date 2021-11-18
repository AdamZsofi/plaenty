package io.swagger.sensormock;

import io.swagger.model.sensor.Sensor;

public class EcSensorMock extends Sensor {
	public EcSensorMock(String name, String dimension) {
		super("mockEcSensor", "dS/m");
	}

	@Override
	public double takeMeasurement() {
		return Math.random()+1.0;
	}
}
