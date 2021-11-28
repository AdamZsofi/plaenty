package hu.bme.aut.plaenty.network;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.model.Sensor;
import lombok.Getter;

public class SensorManager {

    @Getter private static Sensor ecSensor;
    @Getter private static Sensor phSensor;
    @Getter private static Sensor lightSensor;

    public static void initializeSensorData(View errorView) {
        NetworkManager.callApi(
                NetworkManager.getInstance().getSensorsAPI().getSensorList(),
                sensorList -> sensorList.forEach(
                        sensor -> {
                            if (sensor.getName().equals("mock light sensor")) lightSensor = sensor;
                            else if (sensor.getName().equals("mock pH sensor")) phSensor = sensor;
                            else if (sensor.getName().equals("mock EC sensor")) ecSensor = sensor;
                            else throw new RuntimeException("Unexpected sensor received");
                        }
                ),
                () -> Snackbar.make(errorView, R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );
    }

}
