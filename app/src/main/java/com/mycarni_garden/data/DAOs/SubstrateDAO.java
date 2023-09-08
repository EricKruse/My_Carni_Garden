package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.operations.SubstrateWithComponents;

import java.util.List;

@Dao
public interface SubstrateDAO {
    @Insert
    void Insert(Substrate substrates);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Substrate> substrates);

    @Update
    void Update(Substrate substrates);

    @Delete
    void Delete(Substrate substrates);

    @Query("SELECT * FROM substrates")
    LiveData<List<Substrate>> getAllSubstrates();

    @Query("SELECT * FROM substrates WHERE substrate_id = :substrate_id")
    LiveData<Substrate> getSubstrateById(int substrate_id);

    @Query("SELECT substrate_id FROM substrates WHERE name = :name")
    int getSubstrateIdByName(String name);

    @Transaction
    @Query("SELECT * FROM substrates WHERE substrate_id = :substrate_id")
    LiveData<SubstrateWithComponents> getSubstrateWithComponentsById(long substrate_id);
}
