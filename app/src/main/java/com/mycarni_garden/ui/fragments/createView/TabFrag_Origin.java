package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.DAOs.LightingDAO;
import com.mycarni_garden.data.model.Lighting;

import java.util.ArrayList;
import java.util.List;

public class TabFrag_Origin extends Fragment{
    private View rootView;

    private AppDatabase db;
    private RecyclerView rv_lighting;
    private LiveData<List<Lighting>> lightings = new MutableLiveData<>();
    private LightingDAO lightingDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = AppDatabase.getInstance(requireContext());
        if (db==null) System.out.println("Nope!");
        else {lightingDAO = db.lightingDao();

        setupLighting();}

        rootView = inflater.inflate(R.layout.create_plant_tab_substrate, container, false);

        rv_lighting = rootView.findViewById(R.id.rv_lighting);

        return rootView;
    }

    private void setupLighting() {
        lightings = lightingDAO.getAllLighting();
    }
}
