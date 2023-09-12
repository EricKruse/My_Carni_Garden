package com.mycarni_garden.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import android.widget.ImageButton;

import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.ui.adapters.RV_Adapter_families;
import com.mycarni_garden.ui.adapters.RV_Adapter_lighting;
import com.mycarni_garden.ui.adapters.RV_Adapter_origins;
import com.mycarni_garden.ui.adapters.RV_Adapter_species;
import com.mycarni_garden.ui.viewmodels.FamiliesViewModel;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;
import com.mycarni_garden.ui.viewmodels.OriginsViewModel;
import com.mycarni_garden.ui.viewmodels.SpeciesViewModel;

import java.util.List;

public class MainFragment extends Fragment {
    private View rootView;
    private MainActivity mainActivity;

    private int current_family_id = -1;
    private int current_origin_id = -1;
    private int current_species_id = -1;

    private RecyclerView rv_mainFragment;
    private RV_Adapter_families rv_adapter_families;
    private RV_Adapter_origins rv_adapter_origins;
    private RV_Adapter_species rv_adapter_species;

    private MainViewModel mViewModel;
    private FamiliesViewModel familiesViewModel;
    private OriginsViewModel originsViewModel;
    private SpeciesViewModel speciesViewModel;

    private ImageButton btn_createNew;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    //-------------------------------------------------------

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        btn_createNew = rootView.findViewById(R.id.btn_createNew);
        btn_createNew.setVisibility(View.GONE);
        btn_createNew.setOnClickListener(clickedView -> mainActivity.showCreatePlantFragment(mViewModel));

        rv_mainFragment = rootView.findViewById(R.id.rv_mainFragment);
        rv_mainFragment.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_adapter_families = new RV_Adapter_families(this, mViewModel);
        rv_adapter_origins = new RV_Adapter_origins(this);
        rv_adapter_species = new RV_Adapter_species(this);

        rv_mainFragment.setAdapter(rv_adapter_families);

        familiesViewModel = new ViewModelProvider(this).get(FamiliesViewModel.class);
        originsViewModel = new ViewModelProvider(this).get(OriginsViewModel.class);
        speciesViewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);

        getAndPrepareFamiliesList();

        return rootView;
    }

    private void getAndPrepareFamiliesList (){
        btn_createNew.setVisibility(View.GONE);
        familiesViewModel.getAllFamilies().observe(getViewLifecycleOwner(), new Observer<List<Families>>() {
            @Override
            public void onChanged(List<Families> families) {
                rv_adapter_families.setFamiliesList(families);
            }
        });
    }

    private void getAndPrepareOriginsList (){
        if (current_family_id > -1) {
            LiveData<List<Origins>> fitting_origins = originsViewModel.getOriginsByFamilyIdInSpecies(current_family_id);
            if (fitting_origins == null) {
                rv_mainFragment.setVisibility(View.GONE);
            } else {
                rv_mainFragment.setVisibility(View.VISIBLE);
                fitting_origins.observe(getViewLifecycleOwner(), new Observer<List<Origins>>() {
                    @Override
                    public void onChanged(List<Origins> origins) {
                        rv_adapter_origins.setOriginsList(origins);
                    }
                });
            }
            btn_createNew.setVisibility(View.VISIBLE);
        }
    }

    private void getAndPrepareSpeciesList (){
        if (current_origin_id > -1) {
            LiveData<List<Species>> speciesList = speciesViewModel.getSpeciesOfFamilyFromOrigin(current_family_id, current_origin_id);
            if (speciesList == null){
                rv_mainFragment.setVisibility(View.GONE);
            } else {
                rv_mainFragment.setVisibility(View.VISIBLE);
                speciesList.observe(getViewLifecycleOwner(), new Observer<List<Species>>() {
                    @Override
                    public void onChanged(List<Species> species) {
                        rv_adapter_species.setSpeciesList(species);
                    }
                });
            }
        }
    }

    public void switchBackwards() {
        if (current_origin_id != -1){
            current_origin_id = -1;
            rv_mainFragment.setAdapter(rv_adapter_origins);
            rv_adapter_species.clearSpeciesList();
            getAndPrepareOriginsList();
        } else if (current_family_id != -1) {
            current_family_id = -1;
            rv_mainFragment.setAdapter(rv_adapter_families);
            rv_adapter_origins.clearOriginsList();
            getAndPrepareFamiliesList();
        }
    }

    public void switchForwards(int new_current_id) {
        if (current_family_id == -1) {
            System.out.println("Go forwards to origins");
            current_family_id = new_current_id;
            rv_mainFragment.setAdapter(rv_adapter_origins);
            getAndPrepareOriginsList();
        } else if (current_origin_id == -1) {
            current_origin_id = new_current_id;
            rv_mainFragment.setAdapter(rv_adapter_species);
            rv_adapter_origins.clearOriginsList();
            getAndPrepareSpeciesList();
        }
    }
}