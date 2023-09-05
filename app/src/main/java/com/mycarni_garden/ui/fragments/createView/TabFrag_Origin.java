package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.DAOs.LightingDAO;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_addIngredient;
import com.mycarni_garden.ui.adapters.RV_Adapter_lighting;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;

import java.util.ArrayList;
import java.util.List;

public class TabFrag_Origin extends Fragment{
    private View rootView;

    private RecyclerView rv_lighting;
    private RV_Adapter_lighting rv_adapter_lighting;
    private LightingViewModel lightingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.create_plant_tab_origin, container, false);

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

    private void setupLighting() {

    }
}
