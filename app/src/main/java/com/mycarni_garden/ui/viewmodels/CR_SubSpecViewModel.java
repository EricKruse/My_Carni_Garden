package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;
import com.mycarni_garden.data.repositories.CR_SubSpecRepository;
import com.mycarni_garden.data.repositories.FamiliesRepository;

import java.util.List;

public class CR_SubSpecViewModel extends AndroidViewModel {
    private CR_SubSpecRepository repository;
    private LiveData<List<SubstrateSpeciesCrossRef>> allFamilies;

    public CR_SubSpecViewModel(@NonNull Application application) {
        super(application);
        repository = new CR_SubSpecRepository(application);
    }

    public void insert(SubstrateSpeciesCrossRef cr_subSpec) {
        repository.insert(cr_subSpec);
    }

    public void insertList(LiveData<List<SubstrateSpeciesCrossRef>> cr_list) { repository.insertList(cr_list); }

    /*public void update(Families cr_subSpec) {
        repository.update(cr_subSpec);
    }*/
}
