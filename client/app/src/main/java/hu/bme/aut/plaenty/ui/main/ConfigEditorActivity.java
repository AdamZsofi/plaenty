package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.databinding.ActivityMainBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.network.NetworkManager;

public class ConfigEditorActivity extends AppCompatActivity {

    Configuration item;
    ActivityConfigEditorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        Long configId = bundle.getLong("id");

        NetworkManager.callApi(NetworkManager.getInstance().getConfigAPI().configurationIdGet(configId.intValue()),
                configuration -> {
                    item = configuration;
                    refreshDisplay();
                });

    }

    private void refreshDisplay() {
        binding.configEditorName.setText(item.getName());
        binding.configEditorAuthor.setText(item.getAuthor());
        binding.configEditorLight.setText(item.getLightRequired().toString());
    }
}