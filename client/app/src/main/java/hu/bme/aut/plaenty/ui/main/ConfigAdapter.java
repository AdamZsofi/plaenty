package hu.bme.aut.plaenty.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.plaenty.R;
import hu.bme.aut.plaenty.model.Configuration;

public class ConfigAdapter
        extends RecyclerView.Adapter<ConfigAdapter.ConfigViewHolder> {

    private final List<Configuration> items;

    private ShoppingItemClickListener listener;

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

    @Override
    public void onBindViewHolder(@NonNull ConfigViewHolder holder, int position) {
        Configuration item = items.get(position);
        holder.configName.setText(item.getName());

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

        TextView configName;
        Configuration item;

        ConfigViewHolder(View itemView) {
            super(itemView);
            configName = itemView.findViewById(R.id.config_name);

            itemView.setOnClickListener(v -> {listener.onItemChanged(item);});
        }
    }

    public void addItem(Configuration item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void update(List<Configuration> configurations) {
        items.clear();
        items.addAll(configurations);
        notifyDataSetChanged();
    }
}