package com.mycarni_garden.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mycarni_garden.data.DAOs.LightOrigCrossRefDAO;
import com.mycarni_garden.data.DAOs.LightingDAO;
import com.mycarni_garden.data.DAOs.OriginsDAO;
import com.mycarni_garden.data.DAOs.SpeciesDAO;
import com.mycarni_garden.data.DAOs.SubSpecCrossRefDAO;
import com.mycarni_garden.data.DAOs.SubstrateDAO;
import com.mycarni_garden.data.database.AppDatabase;
import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;
import com.mycarni_garden.ui.viewmodels.SpeciesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpeciesRepository {
    private SpeciesDAO speciesDao;
    private OriginsDAO originsDAO;
    private LightingDAO lightingDao;
    private SubstrateDAO substrateDao;
    private LightOrigCrossRefDAO lightOrig_cr_Dao;
    private SubSpecCrossRefDAO subSpec_cr_Dao;
    private LiveData<List<Species>> allSpeciess;

    public SpeciesRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);

        speciesDao = database.speciesDao();
        originsDAO = database.originsDao();
        lightingDao = database.lightingDao();
        substrateDao = database.substrateDao();
        lightOrig_cr_Dao = database.lightOrigCrossDao();
        subSpec_cr_Dao = database.subSpecCrossDao();
        allSpeciess = speciesDao.getAllSpecies();
    }

    public void insert(SpeciesViewModel.FileSet fileSet) {
        new InsertSpecies_AT(speciesDao, originsDAO, lightingDao, substrateDao, lightOrig_cr_Dao, subSpec_cr_Dao).execute(fileSet);
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

    private static class InsertSpecies_AT extends AsyncTask<SpeciesViewModel.FileSet, Void, Void> {
        private SpeciesDAO speciesDao;
        private OriginsDAO originsDAO;
        private LightingDAO lightingDao;
        private SubstrateDAO substrateDao;
        private LightOrigCrossRefDAO lightOrig_cr_Dao;
        private SubSpecCrossRefDAO subSpec_cr_Dao;

        private InsertSpecies_AT (SpeciesDAO speciesDao, OriginsDAO originsDAO, LightingDAO lightingDao, SubstrateDAO substrateDao, LightOrigCrossRefDAO lightOrig_cr_Dao, SubSpecCrossRefDAO subSpec_cr_Dao){
            this.speciesDao = speciesDao;
            this.originsDAO = originsDAO;
            this.lightingDao = lightingDao;
            this.substrateDao = substrateDao;
            this.lightOrig_cr_Dao = lightOrig_cr_Dao;
            this.subSpec_cr_Dao = subSpec_cr_Dao;
        }

        public void insertLightOrigCrossRefs(List<Integer> lighting_ids, int origin_id) {
            List<LightingOriginCrossRef> newCRs = new ArrayList<>();
            for (int i = 0; i < lighting_ids.size(); i++) {
                int lighting_id  = lighting_ids.get(i);
                if (lightingDao.getLightingById(lighting_id) != null) {
                    newCRs.add(new LightingOriginCrossRef(lighting_id, origin_id));
                } else System.out.println("Lighting with ID '" + lighting_id + "' doesn't exist");
            }
            lightOrig_cr_Dao.insertList(newCRs);
        }

        public void insertSubSpecCrossRefs(List<Integer> substrate_ids, int species_id) {
            List<SubstrateSpeciesCrossRef> newCRs = new ArrayList<>();
            for (int i = 0; i < substrate_ids.size(); i++) {
                int substrate_id = substrate_ids.get(i);
                if (substrateDao.getSubstrateById(substrate_id) != null) {
                    newCRs.add(new SubstrateSpeciesCrossRef(substrate_ids.get(i), species_id));
                } else System.out.println("Substrate with ID '" + substrate_id + "' doesn't exist");
            }
            subSpec_cr_Dao.insertList(newCRs);
        }

        @Override
        protected Void doInBackground(SpeciesViewModel.FileSet... fileSets) {
            SpeciesViewModel.FileSet fileSet = fileSets[0];
            Origins newOrigin = new Origins(fileSet.continent, fileSet.area, fileSet.isHighlander, fileSet.winterLvl);
            int newOrigin_id = (int) originsDAO.insertAndGetId(newOrigin);

            if (newOrigin_id != -1) {
                insertLightOrigCrossRefs(fileSet.lighting_ids, newOrigin_id);

                System.out.println("Family ID: " + fileSet.family_id);
                System.out.println("Origin ID: " + newOrigin_id);
                Species newSpecies = new Species(fileSet.species_name, fileSet.growth, fileSet.lifespan, fileSet.family_id, newOrigin_id, fileSet.description);
                speciesDao.Insert(newSpecies);
                int newSpecies_id = newSpecies.getSpecies_id();

                insertSubSpecCrossRefs(fileSet.substrate_ids, newSpecies_id);
            }

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
