package hu.bme.aut.plaenty.ui.main;

import static hu.bme.aut.plaenty.util.SensorUtil.formatSensorData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.model.LightLevel;
import hu.bme.aut.plaenty.network.ConfigManager;

public class ConfigEditorActivity extends AppCompatActivity implements ConfigManager.ConfigurationChangeListener {

    private Long configId = -1L;
    Configuration item = null;
    ActivityConfigEditorBinding binding;

    public static Long CONFIG_CREATION = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lightrequired_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.phSlider.setValues(5f, 7f);
        binding.ecSlider.setValues(0f, 5f);

        binding.ecSlider.addOnChangeListener(
                (slider, value, fromUser) -> {
                    binding.ecRangeText.setText(formatSensorData(binding.ecSlider.getValues().get(0)) + " - " + formatSensorData(binding.ecSlider.getValues().get(1)));
                });

        binding.phSlider.addOnChangeListener(
                (slider, value, fromUser) -> {
                    binding.phRangeText.setText(formatSensorData(binding.phSlider.getValues().get(0)) + " - " + formatSensorData(binding.phSlider.getValues().get(1)));
                });

        // To trigger listener
        binding.phSlider.setValues(binding.phSlider.getValues());
        binding.ecSlider.setValues(binding.ecSlider.getValues());

        Bundle bundle = getIntent().getExtras();
        configId = bundle.getLong("id");

        refreshDisplay();

    }

    private void refreshDisplay() {

        if (configId != CONFIG_CREATION) {
            Optional<Configuration> itemOptional = ConfigManager.getConfigWithId(configId);
            if (!itemOptional.isPresent()) {
                Snackbar.make(binding.getRoot(), R.string.config_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                this.item = itemOptional.get();

                binding.configEditorName.setText(item.getName());
                binding.spinner.setSelection(item.getLightRequired().ordinal());
                binding.phSlider.setValues((float) round(item.getPhmin(), 1), (float) round(item.getPhmax(), 1));
                binding.ecSlider.setValues((float) round(item.getEcmin(), 1), (float) round(item.getEcmax(), 1));
                binding.onMinutes.setText(item.getPumpon() + "");
                binding.offMinutes.setText(item.getPumpoff() + "");
            }
        }

    }

    public void setAsActive(View view) {
        if (item == null) return;
        ConfigManager.setActiveConfiguration(item,
                () -> Snackbar.make(binding.getRoot(), R.string.set_active_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

    }

    public void saveConfig(View view) {
        try {
            Configuration newConfiguration = new Configuration();

            newConfiguration.setName(binding.configEditorName.getText().toString());
            newConfiguration.setEcmin(binding.ecSlider.getValues().get(0));
            newConfiguration.setEcmax(binding.ecSlider.getValues().get(1));
            newConfiguration.setPhmin(binding.phSlider.getValues().get(0));
            newConfiguration.setPhmax(binding.phSlider.getValues().get(1));
            newConfiguration.setLightRequired(LightLevel.values()[binding.spinner.getSelectedItemPosition()]);
            newConfiguration.setPumpon(Integer.parseInt(binding.onMinutes.getText().toString()));
            newConfiguration.setPumpoff(Integer.parseInt(binding.offMinutes.getText().toString()));

            if (configId != CONFIG_CREATION) {
                newConfiguration.setAuthor(item.getAuthor());
                newConfiguration.setId(item.getId());

                ConfigManager.saveConfiguration(newConfiguration,
                        () -> Snackbar.make(binding.getRoot(), "Couldn't save configuration", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show());
            } else {
                newConfiguration.setAuthor("");
                newConfiguration.setId(0L);

                ConfigManager.uploadNewConfiguration(newConfiguration,
                        (newId) -> configId = newId,
                        () -> Snackbar.make(binding.getRoot(), "Couldn't save configuration", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show());
            }


        } catch (NumberFormatException e) {
            Snackbar.make(binding.getRoot(), "Invalid pump setting, only integers allowed", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void configurationsChanged(List<Configuration> configurations) {
        refreshDisplay();
    }

    @Override
    public void activeConfigurationChanged(Configuration activeConfiguration) {

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}