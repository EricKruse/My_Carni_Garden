package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;

@Dao
public interface CompSubCrossRefDAO {
    @Insert
    void insert(ComponentSubstrateCrossRef crossRef);
}
