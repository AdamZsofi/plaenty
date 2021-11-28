package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.Optional;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.databinding.ActivityMainBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.model.SensorData;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.NetworkManager;

public class ConfigEditorActivity extends AppCompatActivity {

    Configuration item = null;
    ActivityConfigEditorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lightrequired_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.ecSlider.addOnChangeListener(
                (slider, value, fromUser) -> {
                    binding.ecRangeText.setText(formatSensorData(binding.ecSlider.getValues().get(0)) + " - " + formatSensorData(binding.ecSlider.getValues().get(1)));
                });

        binding.phSlider.addOnChangeListener(
                (slider, value, fromUser) -> {
                    binding.phRangeText.setText(formatSensorData(binding.phSlider.getValues().get(0)) + " - " + formatSensorData(binding.phSlider.getValues().get(1)));
                });

        Bundle bundle = getIntent().getExtras();
        Long configId = bundle.getLong("id");

        Optional<Configuration> itemOptional = ConfigManager.getConfigWithId(configId);
        if (!itemOptional.isPresent()) {
            Snackbar.make(binding.getRoot(), R.string.config_error, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            item = itemOptional.get();
            refreshDisplay();
        }

    }

    private String formatSensorData(double value){
        return String.format("%.2f",value);
    }

    private void refreshDisplay() {
        binding.configEditorName.setText(item.getName());
        binding.spinner.setSelection(item.getLightRequired().index);
        binding.phSlider.setValues((float) item.getPhmin(), (float) item.getPhmax());
        binding.ecSlider.setValues((float) item.getEcmin(), (float) item.getEcmax());
        binding.onMinutes.setText(item.getPumpon()+"");
        binding.offMinutes.setText(item.getPumpoff()+"");
    }

    public void setAsActive(View view) {
        if (item == null) return;
        ConfigManager.setActiveConfiguration(item,
                () -> Snackbar.make(binding.getRoot(), R.string.set_active_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

    }
}