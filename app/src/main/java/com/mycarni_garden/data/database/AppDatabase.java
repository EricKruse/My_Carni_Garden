package com.mycarni_garden.data.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.Material;
import com.mycarni_garden.data.model.OriginWithLighting;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.model.SpeciesWithSubstrates;
import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.model.SubstrateComponent;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;
import com.mycarni_garden.data.model.SubstrateWithComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


@Database(entities = {
            Families.class,
            Material.class,
            Origins.class,
            Species.class,
            Substrate.class,
            SubstrateComponent.class,
            Lighting.class,
            ComponentSubstrateCrossRef.class,
            LightingOriginCrossRef.class,
            SubstrateSpeciesCrossRef.class
        },
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SpeciesDAO speciesDao();
    public abstract FamiliesDAO familiesDao();
    public abstract OriginsDAO originsDao();

    public abstract SubstrateDAO substrateDao();
    public abstract SubstrateComponentDAO subCompDao();
    public abstract LightingDAO lightingDao();
    public abstract MaterialDAO materialDao();

    public abstract CompSubCrossRefDAO compSubCrossDao();
    public abstract LightOrigCrossRefDAO lightOrigCrossDao();
    public abstract SubSpecCrossRefDAO subSpecCrossDao();

    private static volatile AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        Log.d("AppDatabase", "Getting database instance...");
        if (INSTANCE == null) {
            try {
                Log.d("AppDatabase", "Attempting to create database...");
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class,
                                "myCarniGarden.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            } catch (Exception e) {
                Log.e("AppDatabase", "Error creating database: " + e.getMessage());
            }
        }
        Log.d("AppDatabase", "Returning database");
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                INSTANCE.populateInitialData();
            });
        }
    };

    private void populateInitialData() {
        FamiliesDAO familiesDAO = familiesDao();
        OriginsDAO originsDAO = originsDao();
        LightingDAO lightingDAO = lightingDao();
        SubstrateDAO substrateDAO = substrateDao();
        MaterialDAO materialDAO = materialDao();

        String standardSub = "Standard";
        String highlandSub = "Highlanders";
        String mineralSub = "Mineral Nep.";
        String cocoSub = "Coco Nep.";
        String borneo = "Borneo";
        String sulawesi = "Sulawesi";
        String eastCoastUSA = "East coast USA";
        String nwUSA = "Northwest USA";
        String capensis = "Capensis";
        String sunny = "Sunny";
        String bright = "Bright";
        String hShade = "Half shade";

        List<Families> families = new ArrayList<>();
        families.add(new Families("Aldrovanda", "Waterwheel plant"));
        families.add(new Families("Byblis", "Rainbow plant"));
        families.add(new Families("Cephalotus", "Albany pitcher plant"));
        families.add(new Families("Darlingtonia Californica", "Cobra lily"));
        families.add(new Families("Dionaea Muscipula", "Venus flytrap"));
        families.add(new Families("Drosera", "Sundew"));
        families.add(new Families("Drosophyllum", "Dewy pine"));
        families.add(new Families("Genlisea", "Corkscrew plant"));
        families.add(new Families("Heliamphora", "Sun pitcher"));
        families.add(new Families("Nepenthes", "Pitcher plant"));
        families.add(new Families("Pinguicula", "Butterwort"));
        families.add(new Families("Roridula", "Dewstick / Fly bush"));
        families.add(new Families("Sarracenia", "Trumpet pitcher"));
        families.add(new Families("Utricularia", "Bladderwort"));
        familiesDAO.insertList(families);

        List<Lighting> lightings = new ArrayList<>();
        lightings.add(new Lighting(sunny));
        lightings.add(new Lighting(bright));
        lightings.add(new Lighting(hShade));
        lightings.add(new Lighting("Shady"));
        lightingDAO.insertList(lightings);

        List<Origins> origins = new ArrayList<>();
        origins.add(new Origins("Asia", borneo, true, 0));
        origins.add(new Origins("Asia", borneo, false, 0));
        origins.add(new Origins("Asia", sulawesi, true, 0));
        origins.add(new Origins("Asia", sulawesi, false, 0));
        origins.add(new Origins("North America", eastCoastUSA, false, 0));
        origins.add(new Origins("North America", nwUSA, false, 0));
        origins.add(new Origins("Africa", capensis, false, 0));
        originsDAO.insertList(origins);

        List<Material> materials = new ArrayList<>();
        materials.add(new Material("Peat"));
        materials.add(new Material("Perlite"));
        materials.add(new Material("Sand"));
        materials.add(new Material("Quartz sand"));
        materials.add(new Material("Sphagnum (dead)"));
        materials.add(new Material("Sphagnum"));
        materials.add(new Material("Akadama"));
        materials.add(new Material("Kanuma"));
        materials.add(new Material("Coco chips"));
        materialDAO.insertList(materials);

        List<Substrate> substrates = new ArrayList<>();
        substrates.add(new Substrate(standardSub, 3));
        substrates.add(new Substrate(highlandSub, 3));
        substrates.add(new Substrate(mineralSub, 2));
        substrates.add(new Substrate(cocoSub, 2));
        substrateDAO.insertList(substrates);

        //--------------------- Cross References -----------------------------------
        // Variables

        SubstrateComponentDAO subCompDAO = subCompDao();

        int peat_id = materialDAO.getMaterialIdByName("Peat");
        int perlite_id = materialDAO.getMaterialIdByName("Perlite");
        int sand_id = materialDAO.getMaterialIdByName("Sand");
        int quartz_id = materialDAO.getMaterialIdByName("Quartz sand");
        int moss_id = materialDAO.getMaterialIdByName("Sphagnum (dead)");
        int akadama_id = materialDAO.getMaterialIdByName("Akadama");
        int kanuma_id = materialDAO.getMaterialIdByName("Kanuma");
        int coco_id = materialDAO.getMaterialIdByName("Coco chips");

        double half = 0.5;
        double whole = 1;
        double twice = 2;

        // Substrate components

        List<SubstrateComponent> components = new ArrayList<>();
        components.add(new SubstrateComponent(peat_id, twice));
        components.add(new SubstrateComponent(perlite_id, whole));
        components.add(new SubstrateComponent(sand_id, half));
        components.add(new SubstrateComponent(quartz_id, half));
        components.add(new SubstrateComponent(moss_id, whole));
        components.add(new SubstrateComponent(akadama_id, whole));
        components.add(new SubstrateComponent(kanuma_id, whole));
        components.add(new SubstrateComponent(coco_id, whole));
        subCompDAO.insertList(components);

        // Combine Substrates

        int standardSub_id = substrateDAO.getSubstrateIdByName(standardSub);
        int highlandSub_id = substrateDAO.getSubstrateIdByName(highlandSub);
        int mineralSub_id = substrateDAO.getSubstrateIdByName(mineralSub);
        int cocoSub_id = substrateDAO.getSubstrateIdByName(cocoSub);

        List<ComponentSubstrateCrossRef> compSubCrossList = new ArrayList<>();
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(peat_id, twice), standardSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(perlite_id, whole), standardSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(sand_id, half), standardSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(quartz_id, half), standardSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(moss_id, whole), highlandSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(perlite_id, whole), highlandSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(kanuma_id, whole), mineralSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(akadama_id, whole), mineralSub_id));
        compSubCrossList.add(new ComponentSubstrateCrossRef(subCompDAO.getCompIdByMaterialAndParts(cocoSub_id, whole), coco_id));
        INSTANCE.compSubCrossDao().insertList(compSubCrossList);

        // Combine Origins and Lighting

        int borneoUp_id = originsDAO.getOriginIdByArea(borneo, true);
        int borneoDown_id = originsDAO.getOriginIdByArea(borneo, false);
        int sulawesiUp_id = originsDAO.getOriginIdByArea(sulawesi, true);
        int sulawesiDown_id = originsDAO.getOriginIdByArea(sulawesi, false);
        int eastCoastUSA_id = originsDAO.getOriginIdByArea(eastCoastUSA, false);
        int nwUSA_id = originsDAO.getOriginIdByArea(nwUSA, false);
        int capensis_id = originsDAO.getOriginIdByArea(capensis, false);
        int sunny_id = lightingDAO.getLightingIdByName(sunny);
        int bright_id = lightingDAO.getLightingIdByName(bright);
        int halfShade_id = lightingDAO.getLightingIdByName(hShade);

        List<LightingOriginCrossRef> lightingOriginCrossList = new ArrayList<>();
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, borneoUp_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, borneoUp_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, borneoDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, borneoDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(halfShade_id, borneoDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, sulawesiUp_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, sulawesiUp_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, sulawesiDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, sulawesiDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(halfShade_id, sulawesiDown_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, eastCoastUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, eastCoastUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(halfShade_id, eastCoastUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, nwUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, nwUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(halfShade_id, nwUSA_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(sunny_id, capensis_id));
        lightingOriginCrossList.add(new LightingOriginCrossRef(bright_id, capensis_id));
        INSTANCE.lightOrigCrossDao().insertList(lightingOriginCrossList);
    }
}
