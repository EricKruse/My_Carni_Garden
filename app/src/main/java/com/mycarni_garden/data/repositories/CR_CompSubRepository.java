package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.CompSubCrossRefDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;

import java.util.List;

public class CR_CompSubRepository {
    private CompSubCrossRefDAO compSubCFDao;
    private LiveData<List<ComponentSubstrateCrossRef>> allComponentSubstrateCrossRefs;

    public CR_CompSubRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        compSubCFDao = database.compSubCrossDao();
    }

    public void insert(ComponentSubstrateCrossRef ComponentSubstrateCrossRef) {
        new InsertComponentSubstrate_CF_AT(compSubCFDao).execute(ComponentSubstrateCrossRef);
    }

    public void insertList(List<ComponentSubstrateCrossRef> ComponentSubstrateCrossRef) {
        new insertListComponentSubstrate_CF_AT(compSubCFDao).execute(ComponentSubstrateCrossRef);
    }

    //-------------- Async tasks ---------------------------

    private static class InsertComponentSubstrate_CF_AT extends AsyncTask<ComponentSubstrateCrossRef, Void, Void> {
        private CompSubCrossRefDAO compSubCFDao;

        private InsertComponentSubstrate_CF_AT (CompSubCrossRefDAO compSubCFDao){
            this.compSubCFDao = compSubCFDao;
        }

        @Override
        protected Void doInBackground(ComponentSubstrateCrossRef... compSubCF) {
            compSubCFDao.insert(compSubCF[0]);
            return null;
        }
    }

    private static class insertListComponentSubstrate_CF_AT extends AsyncTask<List<ComponentSubstrateCrossRef>, Void, Void> {
        private CompSubCrossRefDAO compSubCFDao;

        private insertListComponentSubstrate_CF_AT (CompSubCrossRefDAO compSubCFDao){
            this.compSubCFDao = compSubCFDao;
        }

        @Override
        protected Void doInBackground(List<ComponentSubstrateCrossRef>... compSubCF_list) {
            compSubCFDao.insertList(compSubCF_list[0]);
            return null;
        }
    }
}
