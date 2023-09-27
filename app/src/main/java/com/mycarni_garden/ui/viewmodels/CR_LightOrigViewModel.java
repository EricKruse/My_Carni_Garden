package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;
import com.mycarni_garden.data.repositories.CR_LightOrigRepository;
import com.mycarni_garden.data.repositories.CR_SubSpecRepository;

import java.util.ArrayList;
import java.util.List;

public class CR_LightOrigViewModel extends AndroidViewModel {
    private CR_LightOrigRepository repository;
    private LiveData<List<LightingOriginCrossRef>> allCrossRefs;

    public CR_LightOrigViewModel(@NonNull Application application) {
        super(application);
        repository = new CR_LightOrigRepository(application);
    }

    public void insert(LightingOriginCrossRef crossRef) {
        repository.insert(crossRef);
    }

    public void insertList(List<LightingOriginCrossRef> cr_list) { repository.insertList(cr_list); }
}
