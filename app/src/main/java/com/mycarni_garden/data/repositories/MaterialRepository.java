package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.MaterialDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.Material;

import java.util.List;

public class MaterialRepository {
    private MaterialDAO materialDao;
    private LiveData<List<Material>> allMaterials;

    public MaterialRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        materialDao = database.materialDao();
        allMaterials = materialDao.getAllMaterial();
    }

    public void insert(Material Material) {
        new InsertMaterial_AT(materialDao).execute(Material);
    }

    public void update(Material Material) {
        new UpdateMaterial_AT(materialDao).execute(Material);
    }

    public void delete(Material Material) {
        new DeleteMaterial_AT(materialDao).execute(Material);}

    public LiveData<Material> getMaterialById(int Material_id) {
        return materialDao.getMaterialById(Material_id);
    }

    public LiveData<List<Material>> getAllMaterials() {
        return allMaterials;
    }

    //-------------- Async tasks ---------------------------

    private static class InsertMaterial_AT extends AsyncTask<Material, Void, Void> {
        private MaterialDAO materialDao;

        private InsertMaterial_AT (MaterialDAO materialDao){
            this.materialDao = materialDao;
        }

        @Override
        protected Void doInBackground(Material... materials) {
            materialDao.Insert(materials[0]);
            return null;
        }
    }

    private static class UpdateMaterial_AT extends AsyncTask<Material, Void, Void> {
        private MaterialDAO materialDao;

        private UpdateMaterial_AT (MaterialDAO materialDao){
            this.materialDao = materialDao;
        }

        @Override
        protected Void doInBackground(Material... material) {
            materialDao.Update(material[0]);
            return null;
        }
    }

    private static class DeleteMaterial_AT extends AsyncTask<Material, Void, Void> {
        private MaterialDAO materialDao;

        private DeleteMaterial_AT (MaterialDAO materialDao){
            this.materialDao = materialDao;
        }

        @Override
        protected Void doInBackground(Material... material) {
            materialDao.Delete(material[0]);
            return null;
        }
    }
}
