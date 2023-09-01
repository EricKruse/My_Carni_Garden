package com.mycarni_garden.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.model.SpeciesWithSubstrates;

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
    List<Species> getAllSpecies();

    @Query("SELECT * FROM species WHERE species_id = :species_id")
    List<Species> getSpeciesById(int species_id);

    @Transaction
    @Query("SELECT * FROM species WHERE species_id = :species_id")
    LiveData<SpeciesWithSubstrates> getSpeciesWithSubstratesById(long species_id);
}
