package hu.bme.aut.plaenty.network;

import android.accounts.NetworkErrorException;

import java.util.function.Consumer;

import hu.bme.aut.plaenty.api.ActiveConfigurationAPI;
import hu.bme.aut.plaenty.api.ConfigAPI;
import hu.bme.aut.plaenty.api.DashboardAPI;
import hu.bme.aut.plaenty.api.SensorsAPI;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String SERVICE_URL = "http://mondokm.sch.bme.hu:8080";
//    private static final String APP_ID = "f3d694bc3e1d44c1ed5a97bd1120e8fe";

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private Retrofit retrofit;

    @Getter private ActiveConfigurationAPI activeConfigurationAPI;
    @Getter private DashboardAPI dashboardAPI;
    @Getter private SensorsAPI sensorsAPI;
    @Getter private ConfigAPI configAPI;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL + "/")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        activeConfigurationAPI = retrofit.create(ActiveConfigurationAPI.class);
        dashboardAPI = retrofit.create(DashboardAPI.class);
        sensorsAPI = retrofit.create(SensorsAPI.class);
        configAPI = retrofit.create(ConfigAPI.class);
    }

    public static <T> void callApi(Call<T> call, Consumer<T> consumer){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                consumer.accept(response.body());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

            }
        });
    }



}
