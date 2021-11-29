package hu.bme.aut.plaenty.network;


import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.plaenty.model.User;
import lombok.Getter;

public class LoginManager {

    private static List<LoginStatusListener> listeners = new ArrayList<>();

    @Getter
    private static String token = "";
    @Getter
    private static String username = "";
    @Getter
    private static boolean loggedIn = false;

    public static void attemptLogin(String username, String password, Runnable success, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getUserAPI().login(new User(username, password)),
                newToken -> {
                    token = newToken.getToken();
                    loggedIn = true;
                    LoginManager.username = username;
                    listeners.forEach(listener -> listener.loginStatusChanged(username, loggedIn));
                    success.run();
                },
                error);
    }

    public static void attemptSignUp(String username, String password, Runnable success, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getUserAPI().signUp(new User(username, password)),
                newToken -> attemptLogin(username, password, success, error),
                error);
    }

    public static void addListener(LoginStatusListener listener){
        listeners.add(listener);
        listener.loginStatusChanged(username, loggedIn);
    }

    public interface LoginStatusListener{
        void loginStatusChanged(String username, boolean loggedIn);
    }

}
