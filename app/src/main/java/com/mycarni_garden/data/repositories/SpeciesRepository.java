package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.SpeciesDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Species;

import java.util.List;

public class SpeciesRepository {
    private SpeciesDAO speciesDao;
    private LiveData<List<Species>> allSpeciess;

    public SpeciesRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        speciesDao = database.speciesDao();
        allSpeciess = speciesDao.getAllSpecies();
    }

    public void insert(Species Species) {
        new InsertSpecies_AT(speciesDao).execute(Species);
    }

    public void update(Species Species) {
        new UpdateSpecies_AT(speciesDao).execute(Species);
    }

    public void delete(Species Species) {
        new DeleteSpecies_AT(speciesDao).execute(Species);}

    public LiveData<Species> getSpeciesById(int Species_id) {
        return speciesDao.getSpeciesById(Species_id);
    }

    public LiveData<List<Species>> getAllSpecies() {
        return allSpeciess;
    }

    /* public LiveData<List<Integer>> getOriginIdsOfFamilyId(int family_id){
        return speciesDao.getOriginIdsOfFamilyId(family_id);
    }*/
    public void getOriginIdsOfFamilyIdAsync(int familyId, OriginIdsCallback callback) {
        new GetOriginIdsAsyncTask(speciesDao, callback).execute(familyId);
    }

    public LiveData<List<Species>> getSpeciesOfFamilyFromOrigin(int family_id, int origin_id) { return speciesDao.getSpeciesOfFamilyFromOrigin(family_id, origin_id); }

    //-------------- Async tasks ---------------------------

    private static class InsertSpecies_AT extends AsyncTask<Species, Void, Void> {
        private SpeciesDAO speciesDao;

        private InsertSpecies_AT (SpeciesDAO speciesDao){
            this.speciesDao = speciesDao;
        }

        @Override
        protected Void doInBackground(Species... species) {
            speciesDao.Insert(species[0]);
            return null;
        }
    }

    private static class UpdateSpecies_AT extends AsyncTask<Species, Void, Void> {
        private SpeciesDAO speciesDao;

        private UpdateSpecies_AT (SpeciesDAO speciesDao){
            this.speciesDao = speciesDao;
        }

        @Override
        protected Void doInBackground(Species... species) {
            speciesDao.Update(species[0]);
            return null;
        }
    }

    private static class DeleteSpecies_AT extends AsyncTask<Species, Void, Void> {
        private SpeciesDAO speciesDao;

        private DeleteSpecies_AT (SpeciesDAO speciesDao){
            this.speciesDao = speciesDao;
        }

        @Override
        protected Void doInBackground(Species... species) {
            speciesDao.Delete(species[0]);
            return null;
        }
    }

    public interface OriginIdsCallback {
        void onOriginIdsLoaded(List<Integer> originIds);
    }

    private static class GetOriginIdsAsyncTask extends AsyncTask<Integer, Void, List<Integer>> {
        private SpeciesDAO speciesDao;
        private OriginIdsCallback callback;

        private GetOriginIdsAsyncTask(SpeciesDAO speciesDao, OriginIdsCallback callback) {
            this.speciesDao = speciesDao;
            this.callback = callback;
        }

        @Override
        protected List<Integer> doInBackground(Integer... params) {
            int familyId = params[0];
            return speciesDao.getOriginIdsOfFamilyId(familyId);
        }

        @Override
        protected void onPostExecute(List<Integer> originIds) {
            super.onPostExecute(originIds);
            callback.onOriginIdsLoaded(originIds);
        }
    }
}
