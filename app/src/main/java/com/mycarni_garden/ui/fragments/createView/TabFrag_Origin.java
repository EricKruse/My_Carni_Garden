package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mycarni_garden.R;

public class TabFrag_Origin extends Fragment{
    private View rootView;
    private RecyclerView rv_lighting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_plant_tab_substrate, container, false);

        rv_lighting = rootView.findViewById(R.id.rv_lighting);

        return rootView;
    }
}
