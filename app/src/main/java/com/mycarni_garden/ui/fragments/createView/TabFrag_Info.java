package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mycarni_garden.R;

public class TabFrag_Info extends Fragment implements AdapterView.OnItemSelectedListener{

    private ViewPager2 viewPager;

    private Spinner spinner_families;
    private ArrayAdapter<CharSequence> spinner_families_adapter;

    //---------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_plant_tab_info, container, false);

        viewPager = requireActivity().findViewById(R.id.viewPager_createPlantTabs);

        ImageButton btn_goToSubstrate = rootView.findViewById(R.id.goToSubstrate);

        spinner_families = rootView.findViewById(R.id.spinner_family);
        spinner_families_adapter = ArrayAdapter.createFromResource(getContext(), R.array.families, android.R.layout.simple_spinner_item);
        spinner_families_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_families.setAdapter(spinner_families_adapter);
        spinner_families.setOnItemSelectedListener(this);

        btn_goToSubstrate.setOnClickListener(clickedView -> goToSubstrate());

        return rootView;
    }

    public void goToSubstrate() {
        if (viewPager != null) {
            viewPager.setCurrentItem(1, true);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
