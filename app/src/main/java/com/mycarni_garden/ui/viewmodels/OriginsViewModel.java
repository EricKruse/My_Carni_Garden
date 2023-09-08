package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.repositories.OriginsRepository;

import java.util.List;

public class OriginsViewModel extends AndroidViewModel {
    private OriginsRepository repository;
    private LiveData<List<Origins>> allOrigins;

    public OriginsViewModel(@NonNull Application application) {
        super(application);
        repository = new OriginsRepository(application);
        allOrigins = repository.getAllOrigins();
    }

    public void insert(Origins origin) {
        repository.insert(origin);
    }

    public void update(Origins origin) {
        repository.update(origin);
    }

    public LiveData<List<Origins>> getAllOrigins() {
        return allOrigins;
    }

    public LiveData<Integer> getOriginIdByArea (String area, boolean highlands) { return repository.getOriginIdByArea(area, highlands); }
}
