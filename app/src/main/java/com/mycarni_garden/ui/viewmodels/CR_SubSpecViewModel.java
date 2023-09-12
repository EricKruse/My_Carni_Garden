package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;
import com.mycarni_garden.data.repositories.CR_SubSpecRepository;

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

    public void insertPossibleSubstrates(List<Integer> substrate_ids, int species_id) {
        for (int i = 0; i < substrate_ids.size(); i++) {
            SubstrateSpeciesCrossRef newCrossRef = new SubstrateSpeciesCrossRef(substrate_ids.get(i), species_id);
            repository.insert(newCrossRef);
        }
    }
}
