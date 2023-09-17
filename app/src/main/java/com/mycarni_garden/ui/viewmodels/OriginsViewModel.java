package com.mycarni_garden.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.repositories.OriginsRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public int insertAndGetId(Origins origin) {
        try {
            return repository.insertAndGetId(origin);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void update(Origins origin) {
        repository.update(origin);
    }

    public LiveData<List<Origins>> getAllOrigins() {
        return allOrigins;
    }

    public int getOriginIdByArea (String area, boolean highlands) {
        return repository.getOriginIdByArea(area, highlands);
    }

    //public LiveData<Origins> getOriginByArea(String area, boolean highlands) { return repository.getOriginByArea(area, highlands); }

    public LiveData<List<Origins>> getOriginsByListOfIds(List<Integer> origin_ids) { return repository.getOriginsByListOfId(origin_ids); }

    public LiveData<List<Origins>> getOriginsByFamilyIdInSpecies(int family_id) { return repository.getOriginsByFamilyIdInSpecies(family_id); }
}
