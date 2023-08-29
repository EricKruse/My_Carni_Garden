package com.mycarni_garden.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.data.database.LightingDAO;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.ui.models.column_create_ingredient;

import java.util.ArrayList;
import java.util.List;

class RV_Adapter_lighting extends RecyclerView.Adapter<RV_Adapter_lighting.ViewHolder> {

    private RecyclerView rv_lighting;
    private ArrayList<Lighting> lightings;
    private View view;
    private LightingDAO lightingDAO;

    public RV_Adapter_lighting(RecyclerView rv_lighting, ArrayList<Lighting> lightings) {
        this.lightings = lightings;
        this.rv_lighting = rv_lighting;
        ViewGroup parent = (ViewGroup) rv_lighting.getParent();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        SwitchCompat switchLighting;

        ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView;
            switchLighting = new SwitchCompat(itemView.getContext());
            switchLighting.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            layout.addView(switchLighting);
        }
    }

    @NonNull
    @Override
    public RV_Adapter_lighting.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout layout = new LinearLayout(parent.getContext());
        layout.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new RV_Adapter_lighting.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Adapter_lighting.ViewHolder holder, int position) {
        Lighting lighting_data = lightings.get(position);
    }

    @Override
    public int getItemCount() {
        return lightings.size();
    }
}