package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_substrates;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;

import java.util.ArrayList;
import java.util.List;

public class TabFrag_Origin extends Fragment implements AdapterView.OnItemSelectedListener {

    private CreatePlantFragment parentClass;
    private View rootView;

    private RecyclerView rv_lighting;
    private RV_Adapter_lighting rv_adapter_lighting;
    private LightingViewModel lightingViewModel;

    private EditText area_field;
    private SwitchCompat highlands_field;

    private Spinner spinner_continent;
    private ArrayAdapter<CharSequence> spinner_continent_adapter;

    private Spinner spinner_winter;
    private ArrayAdapter<CharSequence> spinner_winter_adapter;

    public TabFrag_Origin(CreatePlantFragment parentClass) {
        this.parentClass = parentClass;
    }
    public TabFrag_Origin(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.create_plant_tab_origin, container, false);

        area_field = rootView.findViewById(R.id.editText_area);
        highlands_field = rootView.findViewById(R.id.switch_highland);

        spinner_continent = rootView.findViewById(R.id.spinner_continent);
        spinner_continent_adapter = ArrayAdapter.createFromResource(getContext(), R.array.continents, android.R.layout.simple_spinner_item);
        spinner_continent_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_continent.setAdapter(spinner_continent_adapter);
        spinner_continent.setOnItemSelectedListener(this);

        spinner_winter = rootView.findViewById(R.id.spinner_winter);
        spinner_winter_adapter = ArrayAdapter.createFromResource(getContext(), R.array.winter, android.R.layout.simple_spinner_item);
        spinner_winter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_winter.setAdapter(spinner_winter_adapter);
        spinner_winter.setOnItemSelectedListener(this);

        rv_lighting = rootView.findViewById(R.id.rv_lighting);
        rv_lighting.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_adapter_lighting = new RV_Adapter_lighting();
        rv_lighting.setAdapter(rv_adapter_lighting);

        lightingViewModel = new ViewModelProvider(this).get(LightingViewModel.class);
        lightingViewModel.getAllLighting().observe(getViewLifecycleOwner(), new Observer<List<Lighting>>() {
            @Override
            public void onChanged(List<Lighting> lightings) {
                rv_adapter_lighting.setLightingList(lightings);
            }
        });

        ImageButton btn_submitPlant = rootView.findViewById(R.id.submitPlant);
        btn_submitPlant.setOnClickListener(clickedView -> submitPlant());

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPause() {
        super.onPause();
        updateData();
    }

    private void updateData() {
        String continent = spinner_continent.getItemAtPosition(spinner_continent.getSelectedItemPosition()).toString();
        String area = area_field.getText().toString();
        boolean isHighlander = highlands_field.isChecked();
        int winterMode = spinner_winter.getSelectedItemPosition();

        List<Integer> selectedLighting = new ArrayList<>();
        for (int i = 0; i < rv_adapter_lighting.getItemCount(); i++) {
            RecyclerView.ViewHolder viewHolder = rv_lighting.findViewHolderForAdapterPosition(i);
            if (viewHolder instanceof RV_Adapter_lighting.LightingHolder){
                RV_Adapter_lighting.LightingHolder column = (RV_Adapter_lighting.LightingHolder) viewHolder;
                SwitchCompat switchCompat = column.getSelectSwitch();
                if (switchCompat.isChecked()) selectedLighting.add(column.getLighting_id());
            }
        }
        parentClass.saveOrigin(continent, area, isHighlander, winterMode, selectedLighting);

    }

    private void submitPlant(){
        updateData();
        parentClass.persistFiles();
    }
}
