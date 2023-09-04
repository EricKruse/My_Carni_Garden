package com.mycarni_garden.data.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;

import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

@Dao
public interface SubSpecCrossRefDAO {
    @Insert
    void insert(SubstrateSpeciesCrossRef crossRef);
}
