package hu.bme.aut.plaenty.api;

import hu.bme.aut.plaenty.model.SystemState;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DashboardAPI {

    @GET("dashboard")
    Call<SystemState> getDashboardData();

}
