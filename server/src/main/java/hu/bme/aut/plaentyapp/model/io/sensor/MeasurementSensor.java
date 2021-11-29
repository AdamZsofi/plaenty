package hu.bme.aut.plaentyapp.model.io.sensor;

public interface MeasurementSensor {
	public SensorData takeMeasurement() throws RuntimeException;
}
