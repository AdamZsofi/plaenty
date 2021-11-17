package io.swagger.model;

import lombok.Data;

import java.util.HashMap;

public @Data class SystemState {
	private Configuration activeConfiguration;
	private HashMap<Sensor, SensorData> sensorstate;
}