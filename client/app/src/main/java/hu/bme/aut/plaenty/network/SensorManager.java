package hu.bme.aut.plaenty.network;

import hu.bme.aut.plaenty.model.Sensor;
import lombok.Getter;

public class SensorManager {

    private static SensorManager instance;
    @Getter private static Sensor ecSensor;
    @Getter private static Sensor phSensor;
    @Getter private static Sensor lightSensor;

    public static SensorManager getInstance() {
        if (instance == null) {
            instance = new SensorManager();
        }
        return instance;
    }

    public static void initializeSensorData() {
        NetworkManager.callApi(
                NetworkManager.getInstance().getSensorsAPI().getSensorList(),
                sensorList -> sensorList.forEach(
                        sensor -> {
                            if (sensor.getName().equals("mock light sensor")) lightSensor = sensor;
                            else if (sensor.getName().equals("mock pH sensor")) phSensor = sensor;
                            else if (sensor.getName().equals("mock EC sensor")) ecSensor = sensor;
                            else throw new RuntimeException("Unexpected sensor received");
                        }
                )
        );
    }

}
