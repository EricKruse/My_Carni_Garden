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
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mycarni_garden.R;
import com.mycarni_garden.ui.adapters.FS_Adapter_CreatePlant;

import java.util.ArrayList;
import java.util.List;

public class CreatePlantFragment extends Fragment {

    private ViewPager2 viewPager;
    private View rootView;
    private ImageButton btn_back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    private String growth;
    private String lifespan;
    private String description;

    private List<Integer> substrate_ids = new ArrayList<>();

    private String continent;
    private String area;
    private boolean isHighlander;
    private List<Integer> lighting_ids = new ArrayList<>();

    void saveInfo(String species_name, String family, String growth, String lifespan, String description) {
        this.species_name = species_name;
        this.family = family;
        this.growth = growth;
        this.lifespan = lifespan;
        this.description = description;
    }

    void saveSubstrate(List<Integer> substrate_ids){
        this.substrate_ids = substrate_ids;
    }

    void saveOrigin(String continent, String area, boolean isHighlander, List<Integer> lighting_ids){
        this.continent = continent;
        this.area = area;
        this.isHighlander = isHighlander;
        this.lighting_ids = lighting_ids;
    }
    
    void persistFile(){
        boolean isComplete = true;
        String[] toCheck = {species_name, growth, lifespan, description, area};
        for (String item :
                toCheck) {
            if (item.trim().equals("")) {
                isComplete = false;
                break;
            }
        }
        if (substrate_ids.size() == 0 || lighting_ids.size() == 0) isComplete = false;
        if (!isComplete){
            Toast.makeText(getContext(), "Please fill all elements!", Toast.LENGTH_SHORT).show();
            //return;
        }

    }
}
