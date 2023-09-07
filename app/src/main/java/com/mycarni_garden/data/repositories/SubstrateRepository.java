package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.SubstrateDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Substrate;

import java.util.List;

public class SubstrateRepository {
    private SubstrateDAO substrateDao;
    private LiveData<List<Substrate>> allSubstrates;

    public SubstrateRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        substrateDao = database.substrateDao();
        allSubstrates = substrateDao.getAllSubstrates();
    }

    public void insert(Substrate Substrate) {
        new InsertSubstrate_AT(substrateDao).execute(Substrate);
    }

    public void update(Substrate Substrate) {
        new UpdateSubstrate_AT(substrateDao).execute(Substrate);
    }

    public void delete(Substrate Substrate) {
        new DeleteSubstrate_AT(substrateDao).execute(Substrate);}

    public LiveData<Substrate> getSubstrateById(int origin_id) {
        return substrateDao.getSubstrateById(origin_id);
    }

    public int getSubstrateIdByName(String name) {
        return substrateDao.getSubstrateIdByName(name);
    }
    
    public LiveData<List<Substrate>> getAllSubstrates() {
        return allSubstrates;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertSubstrate_AT extends AsyncTask<Substrate, Void, Void> {
        private SubstrateDAO substrateDao;

        private InsertSubstrate_AT (SubstrateDAO substrateDao){
            this.substrateDao = substrateDao;
        }

        @Override
        protected Void doInBackground(Substrate... substrate) {
            substrateDao.Insert(substrate[0]);
            return null;
        }
    }

    private static class UpdateSubstrate_AT extends AsyncTask<Substrate, Void, Void> {
        private SubstrateDAO substrateDao;

        private UpdateSubstrate_AT (SubstrateDAO substrateDao){
            this.substrateDao = substrateDao;
        }

        @Override
        protected Void doInBackground(Substrate... substrate) {
            substrateDao.Update(substrate[0]);
            return null;
        }
    }

    private static class DeleteSubstrate_AT extends AsyncTask<Substrate, Void, Void> {
        private SubstrateDAO substrateDao;

        private DeleteSubstrate_AT (SubstrateDAO substrateDao){
            this.substrateDao = substrateDao;
        }

        @Override
        protected Void doInBackground(Substrate... substrate) {
            substrateDao.Delete(substrate[0]);
            return null;
        }
    }
}
