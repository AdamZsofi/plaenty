package hu.bme.aut.plaenty.network;

import java.util.function.Consumer;

import hu.bme.aut.plaenty.api.ActiveConfigurationAPI;
import hu.bme.aut.plaenty.api.ConfigAPI;
import hu.bme.aut.plaenty.api.DashboardAPI;
import hu.bme.aut.plaenty.api.UserAPI;
import hu.bme.aut.plaenty.api.SensorsAPI;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String SERVICE_URL = "http://mondokm.sch.bme.hu:9000";

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
    @Getter private UserAPI userAPI;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL + "/")
                .client(new OkHttpClient.Builder().addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder().header("Authorization", "Bearer "+LoginManager.getToken()).build();
                    return chain.proceed(request);
                }).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        activeConfigurationAPI = retrofit.create(ActiveConfigurationAPI.class);
        dashboardAPI = retrofit.create(DashboardAPI.class);
        sensorsAPI = retrofit.create(SensorsAPI.class);
        configAPI = retrofit.create(ConfigAPI.class);
        userAPI = retrofit.create(UserAPI.class);
    }

    public static <T> void callApi(Call<T> call, Consumer<T> consumer, Runnable error){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(!response.isSuccessful()){
                    error.run();
                } else {
                    consumer.accept(response.body());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                error.run();
            }
        });
    }



}
