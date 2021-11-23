package hu.bme.aut.plaenty.network;

import hu.bme.aut.plaenty.api.ActiveConfigurationAPI;
import hu.bme.aut.plaenty.api.DashboardAPI;
import lombok.Getter;
import okhttp3.OkHttpClient;
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

    @Getter
    private ActiveConfigurationAPI activeConfigurationAPI;
    @Getter
    private DashboardAPI dashboardAPI;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL + "/")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        activeConfigurationAPI = retrofit.create(ActiveConfigurationAPI.class);
        dashboardAPI = retrofit.create(DashboardAPI.class);
    }



}
