package com.mycarni_garden.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.ui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_species extends RecyclerView.Adapter<RV_Adapter_species.SpeciesHolder> {
    private ArrayList<Species> species = new ArrayList<>();
    private MainFragment mainFragment;

    public RV_Adapter_species(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }
    public RV_Adapter_species() {}

    public class SpeciesHolder extends RecyclerView.ViewHolder {
        private TextView species_name;
        private ImageView species_icon;
        private int species_id;

        SpeciesHolder(View itemView) {
            super(itemView);
            species_name = itemView.findViewById(R.id.textView_speciesName);
            species_icon = itemView.findViewById(R.id.imageView_speciesIcon);
        }

        public int getSpecies_id(){ return species_id; }
    }

    @NonNull
    @Override
    public RV_Adapter_species.SpeciesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_row_species, parent, false);
        return new SpeciesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesHolder holder, int position) {
        Species currentSpecies = species.get(position);
        holder.species_name.setText(currentSpecies.getName());
    }

    @Override
    public int getItemCount() {
        return species.size();
    }

    public void setSpeciesList(List<Species> species) {
        this.species = (ArrayList<Species>) species;
        notifyDataSetChanged();
    }
}