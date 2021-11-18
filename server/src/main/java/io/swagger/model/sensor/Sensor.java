package io.swagger.model.sensor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@EqualsAndHashCode
public @Data abstract class Sensor implements MeasurementSensor {
	private static long nextId = 0;
	private long sensorId;
	private String name;
	private String dimension;

	public Sensor(String name, String dimension) {
		this.name = name;
		this.dimension = dimension;
		this.sensorId = nextId;
		nextId++;
	}
}
