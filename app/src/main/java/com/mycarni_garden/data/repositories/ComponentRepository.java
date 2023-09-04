package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.SubstrateComponentDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.SubstrateComponent;

import java.util.List;

public class ComponentRepository {
    private SubstrateComponentDAO subCompDao;
    private LiveData<List<SubstrateComponent>> allSubstrateComponents;

    public ComponentRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        subCompDao = database.subCompDao();
        allSubstrateComponents = subCompDao.getAllComps();
    }

    public void insert(SubstrateComponent substrateComponent) {
        new InsertSubstrateComponent_AT(subCompDao).execute(substrateComponent);
    }

    public void update(SubstrateComponent substrateComponent) {
        new UpdateSubstrateComponent_AT(subCompDao).execute(substrateComponent);
    }

    public void delete(SubstrateComponent substrateComponent) {
        new DeleteSubstrateComponent_AT(subCompDao).execute(substrateComponent);}

    public LiveData<SubstrateComponent> getSubstrateComponentById(int substrateComponent_id) {
        return subCompDao.getCompById(substrateComponent_id);
    }

    public LiveData<List<SubstrateComponent>> getAllSubstrateComponent() {
        return allSubstrateComponents;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertSubstrateComponent_AT extends AsyncTask<SubstrateComponent, Void, Void> {
        private SubstrateComponentDAO subCompDao;

        private InsertSubstrateComponent_AT (SubstrateComponentDAO subCompDao){
            this.subCompDao = subCompDao;
        }

        @Override
        protected Void doInBackground(SubstrateComponent... substrateComponents) {
            subCompDao.Insert(substrateComponents[0]);
            return null;
        }
    }

    private static class UpdateSubstrateComponent_AT extends AsyncTask<SubstrateComponent, Void, Void> {
        private SubstrateComponentDAO subCompDao;

        private UpdateSubstrateComponent_AT (SubstrateComponentDAO subCompDao){
            this.subCompDao = subCompDao;
        }

        @Override
        protected Void doInBackground(SubstrateComponent... substrateComponent) {
            subCompDao.Update(substrateComponent[0]);
            return null;
        }
    }

    private static class DeleteSubstrateComponent_AT extends AsyncTask<SubstrateComponent, Void, Void> {
        private SubstrateComponentDAO subCompDao;

        private DeleteSubstrateComponent_AT (SubstrateComponentDAO subCompDao){
            this.subCompDao = subCompDao;
        }

        @Override
        protected Void doInBackground(SubstrateComponent... substrateComponent) {
            subCompDao.Delete(substrateComponent[0]);
            return null;
        }
    }
}
