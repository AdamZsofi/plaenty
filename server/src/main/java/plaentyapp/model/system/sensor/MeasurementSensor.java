package plaentyapp.model.system.sensor;

public interface MeasurementSensor {
	public SensorData takeMeasurement() throws RuntimeException;
}
