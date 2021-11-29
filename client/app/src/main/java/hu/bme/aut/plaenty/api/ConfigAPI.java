package hu.bme.aut.plaenty.api;

import java.util.List;

import hu.bme.aut.plaenty.model.Configuration;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConfigAPI {

    @GET("configuration/{id}")
    Call<Configuration> configurationIdGet(@Path("id") Long id);

    @Headers({"Content-Type: application/json"})
    @PUT("configuration")
    Call<Configuration> configurationIdPut(@Body Configuration body);

    @GET("configuration/list")
    Call<List<Configuration>> configurationListGet();

    @Headers({"Content-Type: application/json"})
    @POST("configuration")
    Call<Configuration> configurationPost(@Body Configuration body);

    @DELETE("configuration/{id}")
    Call<ResponseBody> configurationDelete(@Path("id") Long id);

}
