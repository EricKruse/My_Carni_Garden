package com.mycarni_garden.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.mycarni_garden.ui.fragments.createView.CreatePlantFragment;
import com.mycarni_garden.ui.fragments.createView.TabFrag_Substrate;
import com.mycarni_garden.ui.fragments.createView.TabFrag_Info;
import com.mycarni_garden.ui.fragments.createView.TabFrag_Origin;

public class FS_Adapter_CreatePlant extends androidx.viewpager2.adapter.FragmentStateAdapter {
    private static final int NUM_TABS = 3;
    CreatePlantFragment fragmentClass;

    public FS_Adapter_CreatePlant(@NonNull FragmentActivity fragmentActivity, CreatePlantFragment fragmentClass) {
        super(fragmentActivity);
        this.fragmentClass = fragmentClass;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return fragment based on position
        switch (position) {
            case 0:
                return new TabFrag_Info(fragmentClass);
            case 1:
                return new TabFrag_Substrate(fragmentClass);
            case 2:
                return new TabFrag_Origin(fragmentClass);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }


}
