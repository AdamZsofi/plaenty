package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.Optional;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.databinding.ActivityMainBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.NetworkManager;

public class ConfigEditorActivity extends AppCompatActivity {

    Configuration item;
    ActivityConfigEditorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lightrequired_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        Long configId = bundle.getLong("id");

        Optional<Configuration> itemOptional = ConfigManager.getConfigWithId(configId);
        if(!itemOptional.isPresent()){
            Snackbar.make(binding.getRoot(), R.string.config_error, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            item = itemOptional.get();
            refreshDisplay();
        }

    }

    private void refreshDisplay() {
        binding.configEditorName.setText(item.getName());
        binding.spinner.setSelection(item.getLightRequired().index);
        binding.phSlider.setValues((float)item.getPhmin(), (float)item.getPhmax());
        binding.ecSlider.setValues((float)item.getEcmin(), (float)item.getEcmax());
    }
}