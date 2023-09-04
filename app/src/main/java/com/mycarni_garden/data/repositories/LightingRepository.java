package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.LightingDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Lighting;

import java.util.List;

public class LightingRepository {
    private LightingDAO lightingDao;
    private LiveData<List<Lighting>> allLighting;

    public LightingRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        lightingDao = database.lightingDao();
        allLighting = lightingDao.getAllLighting();
    }

    public void insert(Lighting lighting) {
        new InsertLighting_AT(lightingDao).execute(lighting);
    }

    public void update(Lighting lighting) {
        new UpdateLighting_AT(lightingDao).execute(lighting);
    }

    public void delete(Lighting lighting) {
        new DeleteLighting_AT(lightingDao).execute(lighting);}

    public LiveData<Lighting> getLightingById(int lighting_id) {
        return lightingDao.getLightingById(lighting_id);
    }

    public LiveData<List<Lighting>> getAllFamilies() {
        return allLighting;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertLighting_AT extends AsyncTask<Lighting, Void, Void> {
        private LightingDAO lightingDao;

        private InsertLighting_AT (LightingDAO lightingDao){
            this.lightingDao = lightingDao;
        }

        @Override
        protected Void doInBackground(Lighting... lightings) {
            lightingDao.Insert(lightings[0]);
            return null;
        }
    }

    private static class UpdateLighting_AT extends AsyncTask<Lighting, Void, Void> {
        private LightingDAO lightingDao;

        private UpdateLighting_AT (LightingDAO lightingDao){
            this.lightingDao = lightingDao;
        }

        @Override
        protected Void doInBackground(Lighting... lightings) {
            lightingDao.Update(lightings[0]);
            return null;
        }
    }

    private static class DeleteLighting_AT extends AsyncTask<Lighting, Void, Void> {
        private LightingDAO lightingDao;

        private DeleteLighting_AT (LightingDAO lightingDao){
            this.lightingDao = lightingDao;
        }

        @Override
        protected Void doInBackground(Lighting... lightings) {
            lightingDao.Delete(lightings[0]);
            return null;
        }
    }
}
