package hu.bme.aut.plaenty.api;

import java.time.LocalDateTime;
import java.util.List;

import hu.bme.aut.plaenty.model.Sensor;
import hu.bme.aut.plaenty.model.SensorData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SensorsAPI {

    @GET("sensor/data/{sensorid}")
    Call<List<SensorData>> sendSensorData(@Path("sensorid") Long sensorid, @Query("from") LocalDateTime from);

    @GET("sensor/list")
    Call<List<Sensor>> getSensorList();

}
