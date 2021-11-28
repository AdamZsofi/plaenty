package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.databinding.ActivityLoginBinding;
import hu.bme.aut.plaenty.network.LoginManager;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void login(View v){
        binding.username.clearFocus();
        binding.password.clearFocus();
        LoginManager.attemptLogin(binding.username.getText().toString(), binding.password.getText().toString(),
                () -> finish(),
                () -> Snackbar.make(binding.username, R.string.login_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
    }

    public void signUp(View v){
        binding.username.clearFocus();
        binding.password.clearFocus();
        LoginManager.attemptSignUp(binding.username.getText().toString(), binding.password.getText().toString(),
                () -> finish(),
                () -> Snackbar.make(binding.username, R.string.signup_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
    }
}