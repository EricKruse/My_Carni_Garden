package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.LightOrigCrossRefDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.LightingOriginCrossRef;

import java.util.List;

public class CR_LightOrigRepository {
    private LightOrigCrossRefDAO lightOrigCRDao;
    private LiveData<List<LightingOriginCrossRef>> allLightingOriginCrossRefs;

    public CR_LightOrigRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        lightOrigCRDao = database.lightOrigCrossDao();
    }

    public void insert(LightingOriginCrossRef LightingOriginCrossRef) {
        new InsertLightingOrigin_CR_AT(lightOrigCRDao).execute(LightingOriginCrossRef);
    }

    public void insertList(List<LightingOriginCrossRef> LightingOriginCrossRef) {
        new InsertListLightingOrigin_CR_AT(lightOrigCRDao).execute(LightingOriginCrossRef);
    }

    //-------------- Async tasks ---------------------------

    private static class InsertLightingOrigin_CR_AT extends AsyncTask<LightingOriginCrossRef, Void, Void> {
        private LightOrigCrossRefDAO lightOrigCRDao;

        private InsertLightingOrigin_CR_AT (LightOrigCrossRefDAO lightOrigCRDao){
            this.lightOrigCRDao = lightOrigCRDao;
        }

        @Override
        protected Void doInBackground(LightingOriginCrossRef... lightOriginCR) {
            lightOrigCRDao.insert(lightOriginCR[0]);
            return null;
        }
    }

    private static class InsertListLightingOrigin_CR_AT extends AsyncTask<List<LightingOriginCrossRef>, Void, Void> {
        private LightOrigCrossRefDAO lightOrigCRDao;

        private InsertListLightingOrigin_CR_AT (LightOrigCrossRefDAO lightOrigCRDao){
            this.lightOrigCRDao = lightOrigCRDao;
        }

        @Override
        protected Void doInBackground(List<LightingOriginCrossRef>... lightOriginCR_list) {
            lightOrigCRDao.insertList(lightOriginCR_list[0]);
            return null;
        }
    }
}
