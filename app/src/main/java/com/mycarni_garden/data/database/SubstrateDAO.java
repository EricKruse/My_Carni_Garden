package com.mycarni_garden.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.model.SubstrateComponent;
import com.mycarni_garden.data.model.SubstrateWithComponents;

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
    List<Substrate> getAllSubstrates();

    @Query("SELECT * FROM substrates WHERE substrate_id = :substrates_id")
    List<Substrate> getSubstrateById(int substrates_id);

    @Query("SELECT substrate_id FROM substrates WHERE name = :name")
    int getSubstrateIdByName(String name);

    @Transaction
    @Query("SELECT * FROM substrates WHERE substrate_id = :substrate_id")
    LiveData<SubstrateWithComponents> getSubstrateWithComponentsById(long substrate_id);
}
