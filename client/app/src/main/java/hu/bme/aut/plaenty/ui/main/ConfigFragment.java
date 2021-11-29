package hu.bme.aut.plaenty.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import hu.bme.aut.plaenty.network.LoginManager;
import hu.bme.aut.plaenty.network.NetworkManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment implements ConfigManager.ConfigurationChangeListener, LoginManager.LoginStatusListener, ConfigAdapter.ShoppingItemClickListener {

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

        binding.createFab.setOnClickListener(this::createConfig);

        adapter = new ConfigAdapter(this);
        binding.configRecyclerView.setAdapter(adapter);
        binding.configRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        LoginManager.addListener(this);
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

    public void createConfig(View view) {
        Intent intent = new Intent(getActivity(), ConfigEditorActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", ConfigEditorActivity.CONFIG_CREATION);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void loginStatusChanged(String username, boolean loggedIn) {
        adapter.notifyDataSetChanged();
        binding.createFab.setVisibility(LoginManager.isLoggedIn() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onItemEdit(Configuration item) {
        Intent intent = new Intent(getActivity(), ConfigEditorActivity.class);
        Bundle b = new Bundle();
        b.putLong("id", item.getId());
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onItemDelete(Configuration item) {
        new AlertDialog.Builder(this.getContext()).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting configuration").setMessage("Are you sure you want to delete this confugration?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConfigManager.deleteConfiguration(item,
                                () -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show());
                    }
                }).setNegativeButton("No", null).show();
    }
}