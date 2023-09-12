package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.repositories.SpeciesRepository;

import java.util.List;

public class SpeciesViewModel extends AndroidViewModel {
    private SpeciesRepository repository;
    private LiveData<List<Species>> allSpecies;
    MutableLiveData<List<Integer>> originIdsLiveData = new MutableLiveData<>();

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

    public LiveData<List<Species>> getSpeciesOfFamilyFromOrigin(int family_id, int origin_id) { return repository.getSpeciesOfFamilyFromOrigin(family_id, origin_id); }

    public LiveData<List<Species>> getAllSpecies() {
        return allSpecies;
    }

    //public LiveData<List<Integer>> getOriginIdsOfFamilyId(int family_id) { return repository.getOriginIdsOfFamilyId(family_id);}
    public void loadOriginIds(int familyId) {
        repository.getOriginIdsOfFamilyIdAsync(familyId, new SpeciesRepository.OriginIdsCallback() {
            @Override
            public void onOriginIdsLoaded(List<Integer> originIds) {
                originIdsLiveData.setValue(originIds);
            }
        });
    }

    public LiveData<List<Integer>> getOriginIdsLiveData() {
        return originIdsLiveData;
    }

    public void onOriginIdsLoaded(List<Integer> originIds) {
        originIdsLiveData.setValue(originIds);
    }
}
