package hu.bme.aut.plaenty.network;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretBasic;
import net.openid.appauth.ResponseTypeValues;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import hu.bme.aut.plaenty.MainActivity;
import hu.bme.aut.plaenty.R;

public class LoginManager {

    ClientAuthentication clientAuthentication;
    AuthorizationRequest authRequest;

    public static final int LOGIN_REQUEST_CODE = 123;
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwbGFlbnR5IiwiZXhwIjoxNjM4MjAyNDU2fQ.rXQZyY5a-30vP2Sb_QTD08cx7X6t2iXLuPzfoCeOfXIPhHN-QU-P_-fbwHNAI8NdAmFCRUQPV93_StvMbDl6YQ";

    public static String getToken(){
        return token;
    };

    public static void setToken(String token){
        LoginManager.token = token;
    }

    public static AuthorizationRequest createAuthenticationRequest(){
        AuthorizationServiceConfiguration mServiceConfiguration =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://github.com/login/oauth/authorize"), // Authorization endpoint
                        Uri.parse("https://github.com/login/oauth/access_token")); // Token endpoint

        return new AuthorizationRequest.Builder(
                mServiceConfiguration,
                "93f3e7a2d4bc6d9ca235", // Client ID
                ResponseTypeValues.CODE,
                Uri.parse("plaenty://oauth-callback") // Redirect URI
        ).build();
    }

    public static ClientAuthentication createClientAuthentication() {
        return new ClientSecretBasic("1517318d7463af81b8abe763701274896e56317e"); // Client secret
    }

    public static void retrieveTokens(AuthorizationResponse authResponse, AuthState authState, Context context) {
        TokenRequest tokenRequest = authResponse.createTokenExchangeRequest();

        AuthorizationService authorizationService = new AuthorizationService(context);

        authorizationService.performTokenRequest(tokenRequest, LoginManager.createClientAuthentication(),
                (tokenResponse, tokenException) -> {
                    authState.update(tokenResponse, tokenException);

                    if(tokenResponse!=null){
                        if(tokenResponse.accessToken!=null) setToken(authState.getAccessToken());
                    }

//                    NetworkManager.callApi(NetworkManager.getInstance().getLoginAPI().getUser(), s -> ((TextView) MainActivity.this.findViewById(R.id.lightTextView)).setText(s.getMessage()), () -> ((TextView) MainActivity.this.findViewById(R.id.lightTextView)).setText("Error"));
//                        Snackbar.make(MainActivity.this.binding.fab, tokenRequest.authorizationCode, Snackbar.LENGTH_LONG);
//                        Snackbar.make(MainActivity.this.binding.getRoot(), tokenException.error, Snackbar.LENGTH_LONG);
//                        .setAction("Action", null).show();

                    // Handle token response error here

//                        persistAuthState(mAuthState);
                });
    }


}
