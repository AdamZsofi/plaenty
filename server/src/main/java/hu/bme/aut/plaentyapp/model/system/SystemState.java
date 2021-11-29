package hu.bme.aut.plaentyapp.model.system;

import hu.bme.aut.plaentyapp.model.configuration.Configuration;
import hu.bme.aut.plaentyapp.model.io.sensor.SensorData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
public @Data final class SystemState {
	private final Configuration activeConfiguration;
	private final HashMap<Long, SensorData> sensorState;
	private boolean isPumpOn;
	private boolean isGrowlightOn;
}