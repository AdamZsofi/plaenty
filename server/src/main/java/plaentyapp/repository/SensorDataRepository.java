package plaentyapp.repository;

import plaentyapp.model.system.sensor.Sensor;
import plaentyapp.model.system.sensor.SensorData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

// TODO delete really old measurements?
@Repository
public class SensorDataRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public SensorData saveMeasurement(SensorData data) {
		return em.merge(data);
	}

	public List<SensorData> getSensorData(Sensor sensor, LocalDateTime from) throws RuntimeException {
		LocalDateTime now = LocalDateTime.now();
		if(from.isAfter(now)) {
			throw new RuntimeException("Interval for sensor data did not pass yet");
		}
		return em.createQuery("SELECT data FROM SensorData WHERE data.time >= ?1 AND data.sensorId = ?2", SensorData.class)
				.setParameter(1, from)
				.setParameter(2, sensor.getSensorId())
				.getResultList();
	}

	public List<SensorData> getSensorData(Sensor sensor) {
		return em.createQuery("SELECT data FROM SensorData WHERE data.sensorId = ?1", SensorData.class)
				.setParameter(1, sensor.getSensorId())
				.getResultList();
	}

	public SensorData getLastMeasurement(Sensor sensor) {
		return em.createQuery("SELECT data from SensorData WHERE data.sensorId = ?1 AND ROWNUM = 1 ORDER BY data.time", SensorData.class)
				.setParameter(1, sensor.getSensorId())
				.getSingleResult();
	}
}
