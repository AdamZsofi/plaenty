package hu.bme.aut.plaenty;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.model.SystemState;
import hu.bme.aut.plaenty.network.NetworkManager;
import hu.bme.aut.plaenty.ui.main.SectionsPagerAdapter;
import hu.bme.aut.plaenty.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        NetworkManager.getInstance().getDashboardAPI().getDashboardData().enqueue(
                new Callback<SystemState>() {
                    @Override
                    public void onResponse(Call<SystemState> call, Response<SystemState> response) {
                        Snackbar.make(fab, response.body().getActiveConfiguration().getName(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    @Override
                    public void onFailure(Call<SystemState> call, Throwable t) {
                        Snackbar.make(fab, t.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

//        NetworkManager.getInstance().getActiveConfigurationAPI().getActiveConfiguration().enqueue(
//                new Callback<Configuration>() {
//                    @Override
//                    public void onResponse(Call<Configuration> call, Response<Configuration> response) {
//                        Snackbar.make(fab, response.body().getName(), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Configuration> call, Throwable t) {
//                        Snackbar.make(fab, t.getMessage(), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                    }
//                }
//        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Asd", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}