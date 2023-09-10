package com.mycarni_garden.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.ui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class RV_Adapter_origins extends RecyclerView.Adapter<RV_Adapter_origins.OriginsHolder> {
    private ArrayList<Origins> origins = new ArrayList<>();
    private MainFragment mainFragment;

    public RV_Adapter_origins(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }
    public RV_Adapter_origins() {}

    public class OriginsHolder extends RecyclerView.ViewHolder {
        private TextView area;
        private TextView continent;
        private TextView highlands;
        private int origin_id;

        OriginsHolder(View itemView) {
            super(itemView);
            area = itemView.findViewById(R.id.textView_originArea);
            continent = itemView.findViewById(R.id.textView_continent);
            highlands = itemView.findViewById(R.id.textView_highlands);
        }

        public int getOrigin_id(){ return origin_id; }
    }

    @NonNull
    @Override
    public RV_Adapter_origins.OriginsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_row_origins, parent, false);
        return new OriginsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OriginsHolder holder, int position) {
        Origins currentOrigins = origins.get(position);
        holder.area.setText(currentOrigins.getArea());
        holder.continent.setText(currentOrigins.getContinent());
        String highlandText = "Highlands";
        if (!currentOrigins.isHighland()) highlandText = "Lowlands";
        holder.highlands.setText(highlandText);
    }

    @Override
    public int getItemCount() {
        return origins.size();
    }

    public void setOriginsList(List<Origins> origins) {
        this.origins = (ArrayList<Origins>) origins;
        notifyDataSetChanged();
    }
}