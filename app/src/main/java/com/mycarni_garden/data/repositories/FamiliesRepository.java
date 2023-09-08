package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.FamiliesDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Families;

import java.util.List;

public class FamiliesRepository {
    private FamiliesDAO familiesDao;
    private LiveData<List<Families>> allFamilies;

    public FamiliesRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        familiesDao = database.familiesDao();
        allFamilies = familiesDao.getAllFamilies();
    }

    public void insert(Families family) {
        new InsertFamily_AT(familiesDao).execute(family);
    }

    public void update(Families family) {
        new UpdateFamily_AT(familiesDao).execute(family);
    }

    public void delete(Families family) {
        new DeleteFamily_AT(familiesDao).execute(family);}

    public LiveData<Families> getFamilyById(int family_id) {
        return familiesDao.getFamilyById(family_id);
    }

    public LiveData<Integer> getFamilyIdByName(String name){
        return familiesDao.getFamilyIdByName(name);
    }

    public LiveData<List<Families>> getAllFamilies() {
        return allFamilies;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertFamily_AT extends AsyncTask<Families, Void, Void> {
        private FamiliesDAO familiesDAO;

        private InsertFamily_AT (FamiliesDAO familiesDAO){
            this.familiesDAO = familiesDAO;
        }

        @Override
        protected Void doInBackground(Families... families) {
            familiesDAO.Insert(families[0]);
            return null;
        }
    }

    private static class UpdateFamily_AT extends AsyncTask<Families, Void, Void> {
        private FamiliesDAO familiesDAO;

        private UpdateFamily_AT (FamiliesDAO familiesDAO){
            this.familiesDAO = familiesDAO;
        }

        @Override
        protected Void doInBackground(Families... families) {
            familiesDAO.Update(families[0]);
            return null;
        }
    }

    private static class DeleteFamily_AT extends AsyncTask<Families, Void, Void> {
        private FamiliesDAO familiesDAO;

        private DeleteFamily_AT (FamiliesDAO familiesDAO){
            this.familiesDAO = familiesDAO;
        }

        @Override
        protected Void doInBackground(Families... families) {
            familiesDAO.Delete(families[0]);
            return null;
        }
    }
}
