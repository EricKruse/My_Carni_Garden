package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.SubstrateWithComponents;

import java.util.List;

@Dao
public interface CompSubCrossRefDAO {
    @Insert
    void insert(ComponentSubstrateCrossRef crossRef);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<ComponentSubstrateCrossRef> crossRefs);
}
