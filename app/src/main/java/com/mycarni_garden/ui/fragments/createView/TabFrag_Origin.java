package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_lighting;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;

import java.util.List;

public class TabFrag_Origin extends Fragment implements AdapterView.OnItemSelectedListener {
    private View rootView;

    private RecyclerView rv_lighting;
    private RV_Adapter_lighting rv_adapter_lighting;
    private LightingViewModel lightingViewModel;

    private Spinner spinner_continent;
    private ArrayAdapter<CharSequence> spinner_continent_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.create_plant_tab_origin, container, false);

        spinner_continent = rootView.findViewById(R.id.spinner_continent);
        spinner_continent_adapter = ArrayAdapter.createFromResource(getContext(), R.array.continents, android.R.layout.simple_spinner_item);
        spinner_continent_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_continent.setAdapter(spinner_continent_adapter);
        spinner_continent.setOnItemSelectedListener(this);

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

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
