package hu.bme.aut.plaenty.ui.main;

import static hu.bme.aut.plaenty.util.SensorUtil.formatSensorData;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Map;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.FragmentDashboardBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.model.Sensor;
import hu.bme.aut.plaenty.model.SensorData;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.LoginManager;
import hu.bme.aut.plaenty.network.NetworkManager;
import hu.bme.aut.plaenty.network.SensorManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment implements ConfigManager.ConfigurationChangeListener, LoginManager.LoginStatusListener {

    private FragmentDashboardBinding binding;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        updateData();

        binding.dashboardSwipe.setOnRefreshListener(this::updateData);
        binding.loginStatus.setOnClickListener(this::startLoginActivity);

        ConfigManager.addListener(this);
        LoginManager.addListener(this);

        return root;
    }

    private void updateData(){
        binding.dashboardSwipe.setRefreshing(true);
        ConfigManager.updateConfigurations(() -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        NetworkManager.callApi(
                NetworkManager.getInstance().getDashboardAPI().getDashboardData(),
                systemState -> {
                    Map<Long, SensorData> sensorState = systemState.getSensorState();
                    binding.ecTextView.setText(formatSensorData(sensorState.get(SensorManager.getEcSensor().getSensorId()).getValue()));
                    binding.phTextView.setText(formatSensorData(sensorState.get(SensorManager.getPhSensor().getSensorId()).getValue()));
                    binding.lightTextView.setText(formatSensorData(sensorState.get(SensorManager.getLightSensor().getSensorId()).getValue()));
                    binding.dashboardSwipe.setRefreshing(false);
                },
                () -> {
                    Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    binding.dashboardSwipe.setRefreshing(false);
                }
        );
    }

    public void startLoginActivity(View v){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void configurationsChanged(List<Configuration> configurations) {
        
    }

    @Override
    public void activeConfigurationChanged(Configuration activeConfiguration) {
        binding.activeConfigTextView.setText(activeConfiguration.getName());
        binding.activeConfigTextView.setOnClickListener((view) -> {
            if(LoginManager.isLoggedIn() && LoginManager.getUsername().equals(activeConfiguration.getAuthor())){
                Intent intent = new Intent(getActivity(), ConfigEditorActivity.class);
                Bundle b = new Bundle();
                b.putLong("id", activeConfiguration.getId());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginStatusChanged(String username, boolean loggedIn) {
        if(loggedIn) {
            binding.currentUserTextView.setText(username);
        } else {
            binding.currentUserTextView.setText(R.string.not_logged_in);
        }
    }
}