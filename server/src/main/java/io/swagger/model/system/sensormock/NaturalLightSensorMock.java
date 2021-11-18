package io.swagger.model.system.sensormock;

import io.swagger.model.system.sensor.Sensor;

public class NaturalLightSensorMock extends Sensor {
	public NaturalLightSensorMock(String name) {
		super(name, "lux");
	}

	@Override
	public double takeMeasurement() {
		// 30 000 - 100 000 - direct sunlight
		// 10 000 lux - ambient daylight
		// 1000 lux - overcast daylight
		// 400 lux - sunset/sunrise
		// 0.01 - 1 lux - night (moonlight)
		return Math.random()*100000.0;
	}
}
