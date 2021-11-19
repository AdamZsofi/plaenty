package plaentyapp.model.system.sensormock;

import plaentyapp.model.system.sensor.Sensor;
import plaentyapp.model.system.sensor.SensorData;

import java.time.LocalDateTime;

public class NaturalLightSensorMock extends Sensor {
	public NaturalLightSensorMock(String name) {
		super(name, "lux");
	}

	@Override
	public SensorData takeMeasurement() {
		// 30 000 - 100 000 - direct sunlight
		// 10 000 lux - ambient daylight
		// 1000 lux - overcast daylight
		// 400 lux - sunset/sunrise
		// 0.01 - 1 lux - night (moonlight)
		SensorData sensorData = new SensorData();
		sensorData.setSensorId(this.sensorId);
		sensorData.setValue(Math.random()*100000.0);
		sensorData.setTime(LocalDateTime.now());
		return sensorData;
	}
}
