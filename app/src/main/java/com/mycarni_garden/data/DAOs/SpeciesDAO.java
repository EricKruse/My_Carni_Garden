package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.operations.SpeciesWithSubstrates;

import java.util.List;

@Dao
public interface SpeciesDAO {
    @Insert
    void Insert(Species species);

    @Update
    void Update(Species species);

    @Delete
    void Delete(Species species);

    @Query("SELECT * FROM species")
    LiveData<List<Species>> getAllSpecies();

    @Query("SELECT * FROM species WHERE species_id = :species_id")
    LiveData<Species> getSpeciesById(int species_id);

    @Query("SELECT origin_id FROM species WHERE family_id = :family_id")
    LiveData<List<Integer>> getOriginIdsOfFamilyIds(int family_id);

    @Transaction
    @Query("SELECT * FROM species WHERE species_id = :species_id")
    LiveData<SpeciesWithSubstrates> getSpeciesWithSubstratesById(long species_id);
}
