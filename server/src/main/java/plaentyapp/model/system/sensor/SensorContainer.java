package plaentyapp.model.system.sensor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorContainer {
	private final Map<Long, Sensor> sensors = new HashMap<>();

	public void add(Sensor sensor) {
		sensors.put(sensor.getSensorId(), sensor);
	}

	public Sensor getSensorById(long id) {
		return sensors.get(id);
	}

	public List<Sensor> getSensorList() {
		return new ArrayList<>(sensors.values());
	}
}
