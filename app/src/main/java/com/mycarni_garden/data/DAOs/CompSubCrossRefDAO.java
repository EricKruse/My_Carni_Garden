package com.mycarni_garden.data.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;

import java.util.List;

@Dao
public interface CompSubCrossRefDAO {
    @Insert
    void insert(ComponentSubstrateCrossRef crossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<ComponentSubstrateCrossRef> crossRefs);
}
