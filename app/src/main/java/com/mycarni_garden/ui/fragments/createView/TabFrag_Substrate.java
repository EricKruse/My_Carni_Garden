package com.mycarni_garden.ui.fragments.createView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.mycarni_garden.R;
import com.mycarni_garden.ui.adapters.RV_Adapter_addIngredient;
import com.mycarni_garden.ui.models.column_create_ingredient;

import java.util.ArrayList;
import java.util.List;

public class TabFrag_Substrate extends Fragment{

    private ViewPager2 viewPager;
    private View rootView;
    private ImageButton btn_create;
    private ImageButton btn_cancel;
    private RecyclerView rv_addIngredients;

    private List<column_create_ingredient> Ingredients = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_plant_tab_substrate, container, false);
        viewPager = requireActivity().findViewById(R.id.viewPager_createPlantTabs);

        // Gather elements -------------------------------------------------------------------------
        btn_create = rootView.findViewById(R.id.btn_addSubstrate);
        btn_cancel = rootView.findViewById(R.id.btn_cancelCreateSubstrate);
        rv_addIngredients = rootView.findViewById(R.id.rv_addSubsIngredients);

        // Setup create toggle ---------------------------------------------------------------------
        btn_create.setOnClickListener(clickedView -> toggleCreateSubstrate(false));
        btn_cancel.setOnClickListener(clickedView -> toggleCreateSubstrate(false));
        toggleCreateSubstrate(true);

        // Initialize adapters ---------------------------------------------------------------------
        RV_Adapter_addIngredient ingredients_adapter = new RV_Adapter_addIngredient(rv_addIngredients, Ingredients);
        rv_addIngredients.setAdapter(ingredients_adapter);

        // RV setup --------------------------------------------------------------------------------
        rv_addIngredients.setLayoutManager(new LinearLayoutManager(getContext()));

        // Switch tabs alternative btn -------------------------------------------------------------
        ImageButton btn_goToOrigin = rootView.findViewById(R.id.goToOrigin);
        btn_goToOrigin.setOnClickListener(clickedView -> onGoToOrigin());

        return rootView;
    }

    public void onGoToOrigin() {
        if (viewPager != null) {
            int currentItem = viewPager.getCurrentItem();
            viewPager.setCurrentItem(2, true);
        }
    }

    public void toggleCreateSubstrate(boolean init) {
        LinearLayout header_createSubstrate = rootView.findViewById(R.id.header_createSubstrate);
        ImageButton btn_submit = rootView.findViewById(R.id.btn_submitSubstrate);

        int newVisibility = View.GONE;

        if (!init){
            newVisibility = header_createSubstrate.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        }

        header_createSubstrate.setVisibility(newVisibility);
        rv_addIngredients.setVisibility(newVisibility);
        btn_submit.setVisibility(newVisibility);
        btn_cancel.setVisibility(newVisibility);

        if (newVisibility == View.VISIBLE) {
            btn_create.setVisibility(View.GONE);
        } else {
            btn_create.setVisibility(View.VISIBLE);
        }
    }
}
