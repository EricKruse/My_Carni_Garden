package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mycarni_garden.R;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.ui.adapters.FS_Adapter_CreatePlant;
import com.mycarni_garden.ui.main.MainFragment;
import com.mycarni_garden.ui.main.MainViewModel;
import com.mycarni_garden.ui.viewmodels.CR_LightOrigViewModel;
import com.mycarni_garden.ui.viewmodels.CR_SubSpecViewModel;
import com.mycarni_garden.ui.viewmodels.FamiliesViewModel;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;
import com.mycarni_garden.ui.viewmodels.OriginsViewModel;
import com.mycarni_garden.ui.viewmodels.SpeciesViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CreatePlantFragment extends Fragment {
    private ViewPager2 viewPager;
    private View rootView;
    private ImageButton btn_back;

    private FamiliesViewModel familiesViewModel;
    private OriginsViewModel originsViewModel;
    private SpeciesViewModel speciesViewModel;
    private CR_LightOrigViewModel cr_lightOrigViewModel;
    private CR_SubSpecViewModel cr_subSpecViewModel;

    MainViewModel mainViewModel;

    public CreatePlantFragment(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }
    public CreatePlantFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        familiesViewModel = new ViewModelProvider(this).get(FamiliesViewModel.class);
        originsViewModel = new ViewModelProvider(this).get(OriginsViewModel.class);
        speciesViewModel = new ViewModelProvider(this).get(SpeciesViewModel.class);
        cr_lightOrigViewModel = new ViewModelProvider(this).get(CR_LightOrigViewModel.class);
        cr_subSpecViewModel = new ViewModelProvider(this).get(CR_SubSpecViewModel.class);

        rootView = inflater.inflate(R.layout.create_plant, container, false);

        btn_back = rootView.findViewById(R.id.btn_goBack);
        btn_back.setOnClickListener(v -> goBack());

        initViews(rootView);

        return rootView;
    }

    private void initViews(View rootView) {
        viewPager = rootView.findViewById(R.id.viewPager_createPlantTabs);
        TabLayout tabLayout = rootView.findViewById(R.id.createPlantTabs);

        FS_Adapter_CreatePlant fragmentAdapter = new FS_Adapter_CreatePlant(requireActivity(), this);
        viewPager.setAdapter(fragmentAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText("");

                    ImageView tabIcon = new ImageView(requireContext());
                    int iconResId = -1;

                    Fragment fragment = fragmentAdapter.createFragment(position);

                    switch (position) {
                        case 0:
                            iconResId = R.drawable.information;
                            break;
                        case 1:
                            iconResId = R.drawable.shovel;
                            break;
                        case 2:
                            iconResId = R.drawable.earth;
                            break;
                    }

                    tabIcon.setImageResource(iconResId);
                    tab.setCustomView(tabIcon);
                }
        ).attach();
    }

    private void goBack(){
        int currentPage = viewPager.getCurrentItem();
        if (currentPage > 0) viewPager.setCurrentItem(currentPage-1,true);
    }

    //----------------------------------------------------------

    private String species_name;
    private String family;
    private String growth_text;
    private int growth;
    private String lifespan;
    private String description;

    private List<Integer> substrate_ids = new ArrayList<>();

    private String continent;
    private String area;
    private boolean isHighlander;
    private int winterLvl;
    private List<Integer> lighting_ids = new ArrayList<>();

    void saveInfo(String species_name, String family, String growth, String lifespan, String description) {
        this.species_name = species_name;
        this.family = family;
        this.growth_text = growth;
        this.lifespan = lifespan;
        this.description = description;
    }

    void saveSubstrate(List<Integer> substrate_ids){
        this.substrate_ids = substrate_ids;
    }

    void saveOrigin(String continent, String area, boolean isHighlander, int winterLvl, List<Integer> lighting_ids){
        this.continent = continent;
        this.area = area;
        this.isHighlander = isHighlander;
        this.winterLvl = winterLvl;
        this.lighting_ids = lighting_ids;
    }
    
    void persistFiles(){
        boolean isComplete = true;
        String[] toCheck = {species_name, String.valueOf(growth), lifespan, description, area};
        for (String item :
                toCheck) {
            if ((item.trim().equals(""))) {
                isComplete = false;
                break;
            }
        }
        if (substrate_ids.size() == 0 || lighting_ids.size() == 0) isComplete = false;
        if (!isComplete){
            Toast.makeText(getContext(), "Please fill all elements!", Toast.LENGTH_SHORT).show();
            return;
        }

        //------- Start persisting

        Origins newOrigin = new Origins(continent, area, isHighlander, winterLvl);
        int newOrigin_id = originsViewModel.insertAndGetId(newOrigin);

        if (newOrigin_id != -1) {
            cr_lightOrigViewModel.insertLightingModesOfOrigin(lighting_ids, newOrigin_id);

            int family_id = mainViewModel.findFamilyIdByName(family);
            growth = Integer.parseInt(growth_text);

            Species newSpecies = new Species(species_name, growth, lifespan, family_id, newOrigin_id, description);
            speciesViewModel.insert(newSpecies);
            int newSpecies_id = newSpecies.getSpecies_id();

            cr_subSpecViewModel.insertPossibleSubstrates(substrate_ids, newSpecies_id);
            requireActivity().finish();
        }
    }
}
