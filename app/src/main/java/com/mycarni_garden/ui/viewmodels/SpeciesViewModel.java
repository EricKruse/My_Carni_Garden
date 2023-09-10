package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.repositories.SpeciesRepository;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private SpeciesRepository repository;
    private LiveData<List<Species>> allSpecies;

    public SpeciesViewModel(@NonNull Application application) {
        super(application);
        repository = new SpeciesRepository(application);
        allSpecies = repository.getAllSpecies();
    }

    public void insert(Species species) {
        repository.insert(species);
    }

    public void update(Species species) {
        repository.update(species);
    }

    public LiveData<List<Integer>> getOriginIdsOfFamilyIds(int family_id) { return repository.getOriginIdsOfFamilyIds(family_id);}

    public LiveData<List<Species>> getAllSpecies() {
        return allSpecies;
    }
}
