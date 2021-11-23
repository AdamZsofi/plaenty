package plaentyapp.model.io.sensor.sensormock;

import plaentyapp.model.io.sensor.Sensor;
import plaentyapp.model.io.sensor.SensorData;

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
