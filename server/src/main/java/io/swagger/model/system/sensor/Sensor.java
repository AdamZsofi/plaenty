package io.swagger.model.system.sensor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public @Data abstract class Sensor implements MeasurementSensor {
	private static long nextId = 0;
	protected long sensorId;
	private String name;
	private String dimension;

	public Sensor(String name, String dimension) {
		this.name = name;
		this.dimension = dimension;
		this.sensorId = nextId;
		nextId++;
	}
}
