package plaentyapp.model.system.sensormock;

import plaentyapp.model.system.sensor.SensorData;
import plaentyapp.model.system.sensor.Sensor;

import java.time.LocalDateTime;

public class EcSensorMock extends Sensor {
	public EcSensorMock(String name) {
		super(name, "dS/m");
	}

	@Override
	public SensorData takeMeasurement() {
		SensorData sensorData = new SensorData();
		sensorData.setSensorId(this.sensorId);
		sensorData.setValue(Math.random()+1.0);
		sensorData.setTime(LocalDateTime.now());
		return sensorData;
	}
}
