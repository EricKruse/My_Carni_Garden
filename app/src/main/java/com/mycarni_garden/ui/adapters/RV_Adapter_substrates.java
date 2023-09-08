package com.mycarni_garden.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Substrate;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_substrates extends RecyclerView.Adapter<RV_Adapter_substrates.SubstrateHolder> {
    private ArrayList<Substrate> substrates = new ArrayList<>();

    public class SubstrateHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView watering;
        private SwitchCompat selectSwitch;
        private RecyclerView rv_components;
        private int substrate_id;

        SubstrateHolder(View itemView) {
            super(itemView);
            selectSwitch = itemView.findViewById(R.id.switch_selectSubstrate);
            name = itemView.findViewById(R.id.textView_substrateName);
            watering = itemView.findViewById(R.id.textView_watering);
            rv_components = itemView.findViewById(R.id.rv_components);
        }

        public int getSubstrate_id(){ return substrate_id; }
        public SwitchCompat getSelectSwitch(){ return selectSwitch; }
    }

    @NonNull
    @Override
    public RV_Adapter_substrates.SubstrateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_column_substrate, parent, false);
        return new SubstrateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubstrateHolder holder, int position) {
        Substrate currentSubstrate = substrates.get(position);
        holder.name.setText(currentSubstrate.getName());
        holder.watering.setText(String.valueOf(currentSubstrate.getWateringInDays()));
        holder.rv_components.setVisibility(View.GONE);
        holder.substrate_id = currentSubstrate.getSubstrate_id();
    }

    @Override
    public int getItemCount() {
        return substrates.size();
    }

    public void setSubstrateList(List<Substrate> substrates) {
        this.substrates = (ArrayList<Substrate>) substrates;
        notifyDataSetChanged();
    }
}