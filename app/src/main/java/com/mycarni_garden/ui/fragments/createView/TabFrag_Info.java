package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mycarni_garden.R;

public class TabFrag_Info extends Fragment{

    private ViewPager2 viewPager;

    //---------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_plant_tab_info, container, false);

        viewPager = requireActivity().findViewById(R.id.viewPager_createPlantTabs);

        ImageButton btn_goToSubstrate = rootView.findViewById(R.id.goToSubstrate);

        btn_goToSubstrate.setOnClickListener(clickedView -> goToSubstrate());

        return rootView;
    }

    public void goToSubstrate() {
        if (viewPager != null) {
            viewPager.setCurrentItem(1, true);
        }
    }
}
