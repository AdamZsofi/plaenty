package hu.bme.aut.plaenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public @Data class Sensor{
	private static long nextId = 0;
	protected long sensorId;
	private String name;
	private String dimension;
}
