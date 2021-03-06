package hu.bme.aut.plaenty.ui.main;

import static hu.bme.aut.plaenty.util.SensorUtil.formatSensorData;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private CoordinatorLayout configDetails;
    private TextView author;
    private TextView ecRange;
    private TextView phRange;
    private TextView lightReq;
    private TextView pump;
    private ImageView editButton;

    private SensorData ecData = null;
    private SensorData phData = null;

    private Configuration activeConfiguration = null;

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

        binding.activeConfigTextView.setOnClickListener((view) -> {
            configDetails.setVisibility(configDetails.getVisibility() == View.VISIBLE? View.GONE: View.VISIBLE);
        });

        configDetails = root.findViewById(R.id.config_details);
        author = root.findViewById(R.id.dropdown_author);
        ecRange = root.findViewById(R.id.dropdown_ec);
        phRange = root.findViewById(R.id.dropdown_ph);
        lightReq = root.findViewById(R.id.dropdown_light);
        pump = root.findViewById(R.id.dropdown_pump);
        editButton = root.findViewById(R.id.edit_button);
        ImageView deleteButton = root.findViewById(R.id.delete_button);
        ImageView activateButton = root.findViewById(R.id.activate_button);

        deleteButton.setVisibility(View.GONE);
        activateButton.setVisibility(View.GONE);

        updateData();

        binding.dashboardSwipe.setOnRefreshListener(this::updateData);
        binding.loginStatus.setOnClickListener(this::startLoginActivity);

        ConfigManager.addListener(this);
        LoginManager.addListener(this);

        return root;
    }

    private void startDiagramActivity(Sensor sensor){
        Intent intent = new Intent(getActivity(), DiagramActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", sensor.getSensorId());
        intent.putExtras(b);
        startActivity(intent);
    }

    private void updateData(){
        binding.dashboardSwipe.setRefreshing(true);
        ConfigManager.updateConfigurations(() -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        NetworkManager.callApi(
                NetworkManager.getInstance().getDashboardAPI().getDashboardData(),
                systemState -> {
                    Map<Long, SensorData> sensorState = systemState.getSensorState();
                    ecData = sensorState.get(SensorManager.getEcSensor().getSensorId());
                    phData = sensorState.get(SensorManager.getPhSensor().getSensorId());

                    binding.lightTextView.setText(formatSensorData(sensorState.get(SensorManager.getLightSensor().getSensorId()).getValue()));
                    binding.dashboardSwipe.setRefreshing(false);

                    binding.pumpTextView.setText(systemState.isPumpOn()? "On" : "Off");
                    binding.lightActTextView.setText(systemState.isGrowlightOn()? "On" : "Off");

                    binding.phCard.setOnClickListener(v -> startDiagramActivity(SensorManager.getPhSensor()));
                    binding.ecCard.setOnClickListener(v -> startDiagramActivity(SensorManager.getEcSensor()));
                    binding.lightCard.setOnClickListener(v -> startDiagramActivity(SensorManager.getLightSensor()));

                    updateSensorDisplay();
                },
                () -> {
                    Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    binding.dashboardSwipe.setRefreshing(false);
                }
        );
    }

    private void updateSensorDisplay(){
        if(ecData != null && phData != null){
            binding.ecTextView.setText(formatSensorData(ecData.getValue()));
            binding.phTextView.setText(formatSensorData(phData.getValue()));
            if(activeConfiguration != null){
                if(!inRange(ecData.getValue(),activeConfiguration.getEcmin(),activeConfiguration.getEcmax())){
                    binding.ecCard.setBackgroundColor(Color.RED);
                    binding.ecTextView.setTextColor(Color.WHITE);
                } else {
                    binding.ecCard.setBackgroundColor(Color.WHITE);
                    binding.ecTextView.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                }
                if(!inRange(phData.getValue(),activeConfiguration.getPhmin(),activeConfiguration.getPhmax())){
                    binding.phCard.setBackgroundColor(Color.RED);
                    binding.phTextView.setTextColor(Color.WHITE);
                } else {
                    binding.phCard.setBackgroundColor(Color.WHITE);
                    binding.phTextView.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                }
                return;
            }
        }
        binding.ecCard.setBackgroundColor(Color.WHITE);
        binding.ecTextView.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
        binding.phCard.setBackgroundColor(Color.WHITE);
        binding.phTextView.setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
    }

    private boolean inRange(double value, double min, double max){
        return value >= min && value <= max;
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
        this.activeConfiguration = activeConfiguration;
        bindActiveConfiguration();
        updateSensorDisplay();
    }

    private void bindActiveConfiguration(){
        if(activeConfiguration != null){
            binding.activeConfigTextView.setText(activeConfiguration.getName());
            ecRange.setText(formatSensorData(activeConfiguration.getEcmin())+" - "+formatSensorData(activeConfiguration.getEcmax()));
            phRange.setText(formatSensorData(activeConfiguration.getPhmin())+" - "+formatSensorData(activeConfiguration.getPhmax()));
            author.setText(activeConfiguration.getAuthor());
            pump.setText(activeConfiguration.getPumpon()+"/"+ activeConfiguration.getPumpoff());
            lightReq.setText(activeConfiguration.getLightRequired().toString());
            editButton.setVisibility(LoginManager.isLoggedIn() && LoginManager.getUsername().equals(activeConfiguration.getAuthor()) ? View.VISIBLE : View.GONE);
            editButton.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), ConfigEditorActivity.class);
                Bundle b = new Bundle();
                b.putLong("id", activeConfiguration.getId());
                intent.putExtras(b);
                startActivity(intent);
            });
        }
    }

    @Override
    public void loginStatusChanged(String username, boolean loggedIn) {
        if(loggedIn) {
            binding.currentUserTextView.setText(username);
            bindActiveConfiguration();
        } else {
            binding.currentUserTextView.setText(R.string.not_logged_in);
            bindActiveConfiguration();
        }
    }
}