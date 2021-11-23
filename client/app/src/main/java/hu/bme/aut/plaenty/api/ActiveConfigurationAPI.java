package hu.bme.aut.plaenty.api;

import hu.bme.aut.plaenty.model.Configuration;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ActiveConfigurationAPI {

    @GET("active-configuration")
    Call<Configuration> getActiveConfiguration();

    @Headers({"Content-Type: application/json"})
    @PUT("active-configuration/{id}")
    Call<Configuration> activeConfigurationIdPut(@Path("id") Integer id, @Body Configuration config);
}
