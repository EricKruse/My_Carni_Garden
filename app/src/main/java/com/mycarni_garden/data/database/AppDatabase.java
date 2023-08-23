package com.mycarni_garden.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.Material;
import com.mycarni_garden.data.model.Origins;
import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.model.SubstrateComponent;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;


@Database(entities = {
            Families.class,
            Material.class,
            Origins.class,
            Species.class,
            Substrate.class,
            SubstrateComponent.class,
            Lighting.class
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

    public abstract ComponentSubstrateCrossRef compSubCross();
    public abstract LightingOriginCrossRef lightOrigCross();
    public abstract SubstrateSpeciesCrossRef subSpecCross();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            "myCarniGarden.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
