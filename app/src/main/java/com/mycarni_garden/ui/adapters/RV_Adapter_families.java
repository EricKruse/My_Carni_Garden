package com.mycarni_garden.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.ui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_families extends RecyclerView.Adapter<RV_Adapter_families.FamiliesHolder> {
    private ArrayList<Families> families = new ArrayList<>();
    private MainFragment mainFragment;

    public RV_Adapter_families(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }
    public RV_Adapter_families() {}

    public class FamiliesHolder extends RecyclerView.ViewHolder {
        private TextView family_name;
        private TextView family_engName;
        private ImageView family_icon;
        private int family_id;

        FamiliesHolder(View itemView) {
            super(itemView);
            family_name = itemView.findViewById(R.id.textView_familyName);
            family_engName = itemView.findViewById(R.id.textView_familyEngName);
            family_icon = itemView.findViewById(R.id.imageView_familyIcon);
        }

        public int getFamily_id(){ return family_id; }
    }

    @NonNull
    @Override
    public RV_Adapter_families.FamiliesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_row_families, parent, false);
        return new FamiliesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FamiliesHolder holder, int position) {
        Families currentFamily = families.get(position);
        holder.family_name.setText(currentFamily.getName());
        holder.family_engName.setText(currentFamily.getEng_name());
    }

    @Override
    public int getItemCount() {
        return families.size();
    }

    public void setFamiliesList(List<Families> families) {
        this.families = (ArrayList<Families>) families;
        notifyDataSetChanged();
    }
}