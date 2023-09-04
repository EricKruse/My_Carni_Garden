package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.SubSpecCrossRefDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

import java.util.List;

public class CR_SubSpecRepository {
    private SubSpecCrossRefDAO subSpecCRDao;
    private LiveData<List<SubstrateSpeciesCrossRef>> allSubstrateSpeciesCrossRefs;

    public CR_SubSpecRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        subSpecCRDao = database.subSpecCrossDao();
    }

    public void insert(SubstrateSpeciesCrossRef SubstrateSpeciesCrossRef) {
        new InsertSubstrateSpecies_CR_AT(subSpecCRDao).execute(SubstrateSpeciesCrossRef);
    }

    //-------------- Async tasks ---------------------------

    private static class InsertSubstrateSpecies_CR_AT extends AsyncTask<SubstrateSpeciesCrossRef, Void, Void> {
        private SubSpecCrossRefDAO subSpecCRDao;

        private InsertSubstrateSpecies_CR_AT (SubSpecCrossRefDAO subSpecCRDao){
            this.subSpecCRDao = subSpecCRDao;
        }

        @Override
        protected Void doInBackground(SubstrateSpeciesCrossRef... subSpecCR) {
            subSpecCRDao.insert(subSpecCR[0]);
            return null;
        }
    }
}
