package hu.bme.aut.plaenty.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.FragmentDashboardBinding;
import hu.bme.aut.plaenty.model.SensorData;
import hu.bme.aut.plaenty.network.NetworkManager;
import hu.bme.aut.plaenty.network.SensorManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

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

        NetworkManager.callApi(
                NetworkManager.getInstance().getDashboardAPI().getDashboardData(),
                systemState -> {
                    Map<Long, SensorData> sensorState = systemState.getSensorState();
                    binding.ecTextView.setText(String.format("%.2f",sensorState.get(SensorManager.getEcSensor().getSensorId()).getValue()));
                    binding.phTextView.setText(String.format("%.2f",sensorState.get(SensorManager.getPhSensor().getSensorId()).getValue()));
                    binding.lightTextView.setText(String.format("%.2f",sensorState.get(SensorManager.getLightSensor().getSensorId()).getValue()));
                },
                () -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        return root;
    }
}