package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

@Dao
public interface LightOrigCrossRefDAO {
    @Insert
    void insert(LightingOriginCrossRef crossRef);
}
