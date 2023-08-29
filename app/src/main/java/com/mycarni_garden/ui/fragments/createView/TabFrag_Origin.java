package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.database.LightingDAO;
import com.mycarni_garden.data.model.Lighting;

import java.util.ArrayList;
import java.util.List;

public class TabFrag_Origin extends Fragment{
    private View rootView;

    private RecyclerView rv_lighting;
    private List<Lighting> lightings = new ArrayList<>();
    private LightingDAO lightingDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setupLighting();

        rootView = inflater.inflate(R.layout.create_plant_tab_substrate, container, false);

        rv_lighting = rootView.findViewById(R.id.rv_lighting);

        return rootView;
    }

    private void setupLighting() {
        lightings = lightingDAO.getAllLighting();

    }
}
