package hu.bme.aut.plaenty.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private LocalDateTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDiagramBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        sensorId = bundle.getLong("id");

        setTime(LocalDate.now().atStartOfDay());

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> setTime(LocalDateTime.of(year, monthOfYear, dayOfMonth, 0, 0));
        binding.date.setOnClickListener(v -> new DatePickerDialog(DiagramActivity.this, date, time.getYear(), time.getMonthValue(), time.getDayOfMonth()).show());

    }

    private void setTime(LocalDateTime time){
        this.time = time;
        binding.date.setText(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        NetworkManager.callApi(NetworkManager.getInstance().getSensorsAPI().sendSensorData(sensorId, time),
                DiagramActivity.this::displaySensorData,
                () -> Snackbar.make(binding.getRoot(), R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
    }

    private void displaySensorData(List<SensorData> sensorData){

        List<Entry> entries = new ArrayList<Entry>();
        for(SensorData data: sensorData){
            LocalDateTime time = LocalDateTime.parse(data.getTime(), DateTimeFormatter.ISO_DATE_TIME);
            entries.add(new Entry(time.atZone(ZoneId.systemDefault()).toEpochSecond(), (float) data.getValue()));
        }

        entries.sort(Comparator.comparingDouble(value -> value.getX()));

        LineDataSet dataSet = new LineDataSet(entries, "Measurements"); // add entries to dataset
        dataSet.setColor(R.color.design_default_color_primary);

        LineData lineData = new LineData(dataSet);
        binding.chart.getXAxis().setValueFormatter(new LineChartXAxisValueFormatter());
        binding.chart.getXAxis().setLabelCount(5);
        binding.chart.setData(lineData);
        binding.chart.getDescription().setEnabled(false);
        binding.chart.getLegend().setEnabled(false);
        binding.chart.invalidate(); // refresh

    }

    private class LineChartXAxisValueFormatter extends IndexAxisValueFormatter {

        @Override
        public String getFormattedValue(float value) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond((long) value),  ZoneId.systemDefault());
            return time.format(formatter);
        }
    }
}