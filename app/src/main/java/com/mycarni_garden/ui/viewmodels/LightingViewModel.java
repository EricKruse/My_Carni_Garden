package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.data.repositories.LightingRepository;

import java.util.List;

public class LightingViewModel extends AndroidViewModel {
    private LightingRepository repository;
    private LiveData<List<Lighting>> allLighting;

    public LightingViewModel(@NonNull Application application) {
        super(application);
        repository = new LightingRepository(application);
        allLighting = repository.getAllLighting();
    }

    public void insert(Lighting lighting) {
        repository.insert(lighting);
    }

    public void update(Lighting lighting) {
        repository.update(lighting);
    }

    public LiveData<List<Lighting>> getAllLighting() {
        return allLighting;
    }
}
