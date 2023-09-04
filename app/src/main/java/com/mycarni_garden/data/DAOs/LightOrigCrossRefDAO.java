package com.mycarni_garden.data.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

import java.util.List;

@Dao
public interface LightOrigCrossRefDAO {
    @Insert
    void insert(LightingOriginCrossRef crossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<LightingOriginCrossRef> crossRefs);
}
