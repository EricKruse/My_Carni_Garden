package com.mycarni_garden.data.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

import java.util.List;

@Dao
public interface SubSpecCrossRefDAO {
    @Insert
    void insert(SubstrateSpeciesCrossRef crossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<SubstrateSpeciesCrossRef> crossRefs);
}
