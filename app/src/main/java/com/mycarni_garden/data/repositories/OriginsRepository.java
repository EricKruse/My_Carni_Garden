package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.OriginsDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Origins;

import java.util.List;

public class OriginsRepository {
    private OriginsDAO originsDao;
    private LiveData<List<Origins>> allOriginss;

    public OriginsRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        originsDao = database.originsDao();
        allOriginss = originsDao.getAllOrigins();
    }

    public void insert(Origins Origins) {
        new InsertOrigins_AT(originsDao).execute(Origins);
    }

    public void update(Origins Origins) {
        new UpdateOrigins_AT(originsDao).execute(Origins);
    }

    public void delete(Origins Origins) {
        new DeleteOrigins_AT(originsDao).execute(Origins);}

    public LiveData<Origins> getOriginById(int origin_id) {
        return originsDao.getOriginById(origin_id);
    }

    public LiveData<Integer> getOriginIdByArea(String area, boolean isHighland) {
        return originsDao.getOriginIdByArea(area, isHighland);
    }

    public LiveData<List<Origins>> getOriginsByListOfIds(List<Integer> origin_ids) { return originsDao.getOriginsByListOfIds(origin_ids); }

    public LiveData<List<Origins>> getAllOrigins() {
        return allOriginss;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertOrigins_AT extends AsyncTask<Origins, Void, Void> {
        private OriginsDAO originsDao;

        private InsertOrigins_AT (OriginsDAO originsDao){
            this.originsDao = originsDao;
        }

        @Override
        protected Void doInBackground(Origins... origins) {
            originsDao.Insert(origins[0]);
            return null;
        }
    }

    private static class UpdateOrigins_AT extends AsyncTask<Origins, Void, Void> {
        private OriginsDAO originsDao;

        private UpdateOrigins_AT (OriginsDAO originsDao){
            this.originsDao = originsDao;
        }

        @Override
        protected Void doInBackground(Origins... origins) {
            originsDao.Update(origins[0]);
            return null;
        }
    }

    private static class DeleteOrigins_AT extends AsyncTask<Origins, Void, Void> {
        private OriginsDAO originsDao;

        private DeleteOrigins_AT (OriginsDAO originsDao){
            this.originsDao = originsDao;
        }

        @Override
        protected Void doInBackground(Origins... origins) {
            originsDao.Delete(origins[0]);
            return null;
        }
    }
}
