package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.repositories.SubstrateRepository;

import java.util.List;

public class SubstrateViewModel extends AndroidViewModel {
    private SubstrateRepository repository;
    private LiveData<List<Substrate>> allSubstrates;

    public SubstrateViewModel(@NonNull Application application) {
        super(application);
        repository = new SubstrateRepository(application);
        allSubstrates = repository.getAllSubstrates();
    }

    public void insert(Substrate substrate) {
        repository.insert(substrate);
    }

    public void update(Substrate substrate) {
        repository.update(substrate);
    }

    public LiveData<List<Substrate>> getAllSubstrates() {
        return allSubstrates;
    }
}
