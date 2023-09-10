package com.mycarni_garden.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.ui.adapters.RV_Adapter_families;
import com.mycarni_garden.ui.adapters.RV_Adapter_lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_origins;
import com.mycarni_garden.ui.adapters.RV_Adapter_species;

import java.util.List;

public class MainFragment extends Fragment {
    private View rootView;

    private int current_family_id;
    private int current_origin_id;

    private RecyclerView rv_mainFragment;
    private RV_Adapter_families rv_adapter_families;
    private RV_Adapter_origins rv_adapter_origins;
    private RV_Adapter_species rv_adapter_species;

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        rv_mainFragment = rootView.findViewById(R.id.rv_mainFragment);

        rv_mainFragment.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_adapter_families = new RV_Adapter_families(this);
        rv_adapter_origins = new RV_Adapter_origins(this);
        rv_adapter_species = new RV_Adapter_species(this);

        rv_mainFragment.setAdapter(rv_adapter_families);


        return rootView;
    }
}