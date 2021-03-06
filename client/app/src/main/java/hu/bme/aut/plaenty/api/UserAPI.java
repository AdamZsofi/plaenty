package hu.bme.aut.plaenty.api;

import java.util.List;

import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.model.TokenResponse;
import hu.bme.aut.plaenty.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("users/sign-up")
    Call<ResponseBody> signUp(@Body User user);


    @POST("login")
    Call<TokenResponse> login(@Body User user);

}
