package io.swagger.model.system.sensormock;

import io.swagger.model.system.sensor.Sensor;
import io.swagger.model.system.sensor.SensorData;

import java.time.LocalDateTime;

public class PhSensorMock extends Sensor {
	public PhSensorMock(String name) {
		super(name, "");
	}

	@Override
	public SensorData takeMeasurement() {
		SensorData sensorData = new SensorData();
		sensorData.setSensorId(this.sensorId);
		sensorData.setValue((Math.random()*4.0)+4.0);
		sensorData.setTime(LocalDateTime.now());
		return sensorData;
	}
}
