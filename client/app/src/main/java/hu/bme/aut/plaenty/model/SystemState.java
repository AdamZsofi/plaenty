package hu.bme.aut.plaenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
public @Data final class SystemState {
	private final Configuration activeConfiguration;
	private final HashMap<Long, SensorData> sensorState;
	private final boolean isPumpOn;
	private final boolean isGrowlightOn;
}