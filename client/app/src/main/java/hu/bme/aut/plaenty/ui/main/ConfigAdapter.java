package hu.bme.aut.plaenty.ui.main;

import static hu.bme.aut.plaenty.util.SensorUtil.formatSensorData;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.model.Configuration;
import hu.bme.aut.plaenty.network.ConfigManager;
import hu.bme.aut.plaenty.network.LoginManager;

public class ConfigAdapter
        extends RecyclerView.Adapter<ConfigAdapter.ConfigViewHolder> {

    private final List<Configuration> items;
    private Configuration activeConfig = null;

    private ShoppingItemClickListener listener;
    private Map<Configuration, Boolean> openedMap = new HashMap<>();

    public ConfigAdapter(ShoppingItemClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public ConfigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_configuration, parent, false);
        return new ConfigViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ConfigViewHolder holder, int position) {
        Configuration item = items.get(position);
        holder.configName.setText(item.getName());

        holder.dropdownAuthor.setText(item.getAuthor());
        holder.dropdownEc.setText(formatSensorData(item.getEcmin())+" - "+formatSensorData(item.getEcmax()));
        holder.dropdownpH.setText(formatSensorData(item.getPhmin())+" - "+formatSensorData(item.getPhmax()));
        holder.dropdownLight.setText(item.getLightRequired().toString());
        holder.dropdownPump.setText(item.getPumpon()+"/"+item.getPumpoff());

        holder.configName.setTypeface(null, activeConfig!=null && activeConfig.getId().equals(item.getId()) ? Typeface.BOLD: Typeface.NORMAL);
        holder.cardView.setOnClickListener(v -> {
            openedMap.put(item, !openedMap.getOrDefault(item, true));
            notifyItemChanged(position);
        });
        holder.detailsView.setVisibility(openedMap.getOrDefault(item, false).booleanValue()? View.VISIBLE : View.GONE);
        holder.editButton.setVisibility(LoginManager.isLoggedIn() && LoginManager.getUsername().equals(item.getAuthor())? View.VISIBLE : View.GONE);
        holder.deleteButton.setVisibility(LoginManager.isLoggedIn() && LoginManager.getUsername().equals(item.getAuthor()) && !ConfigManager.getActiveConfiguration().getId().equals(item.getId()) ? View.VISIBLE : View.GONE);
        holder.activateButton.setVisibility(LoginManager.isLoggedIn() && !item.getId().equals(ConfigManager.getActiveConfiguration().getId())? View.VISIBLE : View.GONE);
        holder.editButton.setOnClickListener(v -> listener.onItemEdit(item));
        holder.deleteButton.setOnClickListener(v -> listener.onItemDelete(item));
        holder.activateButton.setOnClickListener(v -> listener.onItemActivate(item));

        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ShoppingItemClickListener{
        void onItemEdit(Configuration item);
        void onItemDelete(Configuration item);
        void onItemActivate(Configuration item);
    }

    class ConfigViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView configName;
        ImageView editButton;
        ImageView deleteButton;
        ImageView activateButton;
        CoordinatorLayout detailsView;

        TextView dropdownAuthor;
        TextView dropdownEc;
        TextView dropdownpH;
        TextView dropdownLight;
        TextView dropdownPump;

        Configuration item;

        ConfigViewHolder(View itemView) {
            super(itemView);
            configName = itemView.findViewById(R.id.config_name);
            cardView = itemView.findViewById(R.id.config_card);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
            activateButton = itemView.findViewById(R.id.activate_button);
            detailsView = itemView.findViewById(R.id.config_details);

            dropdownAuthor = itemView.findViewById(R.id.dropdown_author);
            dropdownEc = itemView.findViewById(R.id.dropdown_ec);
            dropdownpH = itemView.findViewById(R.id.dropdown_ph);
            dropdownLight = itemView.findViewById(R.id.dropdown_light);
            dropdownPump = itemView.findViewById(R.id.dropdown_pump);
        }
    }

    public void update(List<Configuration> configurations) {
        configurations.forEach(c -> openedMap.put(c, openedMap.getOrDefault(c, false)));
        items.clear();
        items.addAll(configurations);
        notifyDataSetChanged();
    }

    public void setActiveConfig(Configuration activeConfig){
        this.activeConfig = activeConfig;
        notifyDataSetChanged();
    }
}