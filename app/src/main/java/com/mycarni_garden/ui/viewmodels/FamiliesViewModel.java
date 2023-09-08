package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.repositories.FamiliesRepository;

import java.util.List;

public class FamiliesViewModel extends AndroidViewModel {
    private FamiliesRepository repository;
    private LiveData<List<Families>> allFamilies;

    public FamiliesViewModel(@NonNull Application application) {
        super(application);
        repository = new FamiliesRepository(application);
        allFamilies = repository.getAllFamilies();
    }

    public void insert(Families family) {
        repository.insert(family);
    }

    public void update(Families family) {
        repository.update(family);
    }

    public LiveData<List<Families>> getAllFamilies() {
        return allFamilies;
    }

    public LiveData<Integer> getFamilyIdByName(String name) { return repository.getFamilyIdByName(name); }
}
