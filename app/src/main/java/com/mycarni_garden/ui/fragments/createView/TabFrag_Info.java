package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.mycarni_garden.R;

import java.util.List;

public class TabFrag_Info extends Fragment implements AdapterView.OnItemSelectedListener {

    private CreatePlantFragment parentClass;
    private ViewPager2 viewPager;

    private EditText species_name_field;
    private EditText growth_field;
    private EditText lifespan_field;
    private EditText description_field;

    private Spinner spinner_families;
    private ArrayAdapter<CharSequence> spinner_families_adapter;

    //---------------------------------------------------------

    public TabFrag_Info(CreatePlantFragment parentClass) {
        this.parentClass = parentClass;
    }

    public TabFrag_Info(){

    }

    //---------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_plant_tab_info, container, false);

        species_name_field = rootView.findViewById(R.id.editText_speciesName);
        growth_field = rootView.findViewById(R.id.editText_maxHeight);
        lifespan_field = rootView.findViewById(R.id.editText_lifeSpan);
        description_field = rootView.findViewById(R.id.editText_description);
        ImageButton btn_goToSubstrate = rootView.findViewById(R.id.goToSubstrate);

        viewPager = requireActivity().findViewById(R.id.viewPager_createPlantTabs);

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
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPause() {
        super.onPause();

        parentClass.saveInfo(
                species_name_field.getText().toString(),
                spinner_families.getItemAtPosition(spinner_families.getSelectedItemPosition()).toString(),
                growth_field.getText().toString(),
                lifespan_field.getText().toString(),
                description_field.getText().toString()
        );
    }
}
