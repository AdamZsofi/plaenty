package hu.bme.aut.plaenty.network;


import hu.bme.aut.plaenty.model.User;

public class LoginManager {


    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwbGFlbnR5IiwiZXhwIjoxNjM4MjAyNDU2fQ.rXQZyY5a-30vP2Sb_QTD08cx7X6t2iXLuPzfoCeOfXIPhHN-QU-P_-fbwHNAI8NdAmFCRUQPV93_StvMbDl6YQ";
    private static String username = "";
    private static boolean loggedIn = false;

    public static String getToken(){
        return token;
    };

    public static void setToken(String token){
        LoginManager.token = token;
    }

    public static void attemptLogin(String username, String password, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getUserAPI().login(new User(username, password)),
                newToken -> {
                    token = newToken;
                    loggedIn = true;
                    LoginManager.username = username;
                },
                error);
    }

    public static void attemptSignUp(String username, String password, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getUserAPI().signUp(new User(username, password)),
                newToken -> {},
                error);
    }

}
