package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.databinding.ActivityConfigEditorBinding;
import hu.bme.aut.plaenty.databinding.ActivityDiagramBinding;
import hu.bme.aut.plaenty.model.Sensor;
import hu.bme.aut.plaenty.model.SensorData;
import hu.bme.aut.plaenty.network.NetworkManager;
import hu.bme.aut.plaenty.network.SensorManager;

public class DiagramActivity extends AppCompatActivity {

    private ActivityDiagramBinding binding;
    private Long sensorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiagramBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        sensorId = bundle.getLong("id");

        NetworkManager.callApi(NetworkManager.getInstance().getSensorsAPI().sendSensorData(sensorId, null),
                this::displaySensorData,
                () -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

    }

    private void displaySensorData(List<SensorData> sensorData){

        List<Entry> entries = new ArrayList<Entry>();
        for(SensorData data: sensorData){
            entries.add(new Entry(data.getMeasurementId(), (float) data.getValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Measurements"); // add entries to dataset
        dataSet.setColor(R.color.design_default_color_primary);
//        dataSet.setValueTextColor(...); // styling, ...

        LineData lineData = new LineData(dataSet);
        binding.chart.setData(lineData);
        binding.chart.invalidate(); // refresh

    }
}