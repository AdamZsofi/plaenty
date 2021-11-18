package io.swagger.model.system.sensor;

public interface MeasurementSensor {
	public SensorData takeMeasurement() throws RuntimeException;
}
