package com.mycarni_garden.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Lighting;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_lighting extends RecyclerView.Adapter<RV_Adapter_lighting.LightingHolder> {
    private ArrayList<Lighting> lightings = new ArrayList<>();

    public class LightingHolder extends RecyclerView.ViewHolder {
        private SwitchCompat switchCompat;
        private int lighting_id;

        LightingHolder(View itemView) {
            super(itemView);
            switchCompat = itemView.findViewById(R.id.row_lighting);
        }

        public int getLighting_id(){ return lighting_id; }
        public SwitchCompat getSelectSwitch(){ return switchCompat; }
    }

    @NonNull
    @Override
    public RV_Adapter_lighting.LightingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_row_lighting, parent, false);
        return new LightingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LightingHolder holder, int position) {
        Lighting currentLighting = lightings.get(position);
        holder.switchCompat.setText(currentLighting.getName());
    }

    @Override
    public int getItemCount() {
        return lightings.size();
    }

    public void setLightingList(List<Lighting> lightings) {
        this.lightings = (ArrayList<Lighting>) lightings;
        notifyDataSetChanged();
    }
}