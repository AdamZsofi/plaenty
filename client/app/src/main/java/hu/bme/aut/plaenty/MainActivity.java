package hu.bme.aut.plaenty;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.SensorManager;
import hu.bme.aut.plaenty.ui.main.SectionsPagerAdapter;
import hu.bme.aut.plaenty.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SensorManager.initializeSensorData(binding.getRoot());

        ConfigManager.updateConfigurations(() -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

    }

}