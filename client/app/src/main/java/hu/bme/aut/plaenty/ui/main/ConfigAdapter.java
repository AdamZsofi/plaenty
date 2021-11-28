package hu.bme.aut.plaenty.ui.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

        holder.configName.setTypeface(null, activeConfig!=null && activeConfig.getId().equals(item.getId()) ? Typeface.BOLD: Typeface.NORMAL);
        holder.cardView.setOnClickListener(v -> {
            openedMap.put(item, !openedMap.getOrDefault(item, true));
            notifyItemChanged(position);
        });
        holder.detailsView.setVisibility(openedMap.getOrDefault(item, false).booleanValue()? View.VISIBLE : View.GONE);
        holder.editButton.setOnClickListener(v -> listener.onItemChanged(item));

        holder.item = item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ShoppingItemClickListener{
        void onItemChanged(Configuration item);
    }

    class ConfigViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView configName;
        ImageView editButton;
        CoordinatorLayout detailsView;

        Configuration item;

        ConfigViewHolder(View itemView) {
            super(itemView);
            configName = itemView.findViewById(R.id.config_name);
            cardView = itemView.findViewById(R.id.config_card);
            editButton = itemView.findViewById(R.id.edit_button);
            detailsView = itemView.findViewById(R.id.config_details);


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