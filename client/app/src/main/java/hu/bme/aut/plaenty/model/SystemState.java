package hu.bme.aut.plaenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
public @Data final class SystemState {
	private Configuration activeConfiguration;
	private HashMap<Sensor, SensorData> sensorState;
	boolean isPumpOn;
	boolean isGrowlightOn;
}