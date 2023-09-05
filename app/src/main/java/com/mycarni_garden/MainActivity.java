package com.mycarni_garden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.ui.fragments.createView.CreatePlantFragment;
import com.mycarni_garden.ui.viewmodels.LightingViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showCreatePlantFragment();
    }

    private void showCreatePlantFragment() {
        Fragment createPlantFragment = new CreatePlantFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, createPlantFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}