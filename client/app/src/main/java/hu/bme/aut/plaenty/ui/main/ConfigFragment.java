package hu.bme.aut.plaenty.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import hu.bme.aut.plaenty.MainActivity;
import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.FragmentConfigBinding;
import hu.bme.aut.plaenty.databinding.FragmentDashboardBinding;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.NetworkManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment implements ConfigManager.ConfigurationChangeListener {

    private FragmentConfigBinding binding;
    private ConfigAdapter adapter;

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance() {
        ConfigFragment fragment = new ConfigFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentConfigBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        adapter = new ConfigAdapter(item -> {
            Intent intent = new Intent(getActivity(), ConfigEditorActivity.class);
            Bundle b = new Bundle();
            b.putLong("id", item.getId());
            intent.putExtras(b);
            startActivity(intent);
        });
        binding.configRecyclerView.setAdapter(adapter);
        binding.configRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ConfigManager.addListener(this);

        updateConfigList();
        binding.swipeContainer.setOnRefreshListener(this::updateConfigList);

        return root;
    }

    private void updateConfigList() {
        binding.swipeContainer.setRefreshing(true);
        ConfigManager.updateConfigurations(() -> {
            Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            binding.swipeContainer.setRefreshing(false);
        });
    }

    @Override
    public void configurationsChanged(List<Configuration> configurations) {
        binding.swipeContainer.setRefreshing(false);
        adapter.update(configurations);
    }

    @Override
    public void activeConfigurationChanged(Configuration activeConfiguration) {
        adapter.setActiveConfig(activeConfiguration);
    }
}