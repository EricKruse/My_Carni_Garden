package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.mycarni_garden.data.operations.OriginWithLighting;
import com.mycarni_garden.data.model.Origins;

import java.util.List;

@Dao
public interface OriginsDAO {
    @Insert
    void Insert(Origins origins);

    @Insert
    long insertAndGetId(Origins origins);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Origins> origins);

    @Update
    void Update(Origins origins);

    @Delete
    void Delete(Origins origins);

    @Query("SELECT * FROM origins")
    LiveData<List<Origins>> getAllOrigins();

    @Query("SELECT * FROM origins WHERE origin_id = :origin_id")
    LiveData<Origins> getOriginById(int origin_id);

    @Query("SELECT origin_id FROM origins WHERE area = :area AND isHighland = :isHighland")
    int getOriginIdByArea(String area, boolean isHighland);

    @Query("SELECT * FROM origins WHERE origin_id IN (:origin_ids)")
    LiveData<List<Origins>> getOriginsByListOfIds(List<Integer> origin_ids);

    @Query("SELECT o.* FROM origins AS o JOIN species AS s ON o.origin_id = s.origin_id WHERE s.family_id = :family_id")
    LiveData<List<Origins>> getOriginsByFamilyIdInSpecies(int family_id);
}
