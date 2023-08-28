package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.Lighting;

import java.util.List;

@Dao
public interface LightingDAO {
    @Insert
    void Insert(Lighting lighting);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Lighting> lightings);

    @Update
    void Update(Lighting lighting);

    @Delete
    void Delete(Lighting lighting);

    @Query("SELECT * FROM lighting")
    List<Lighting> getAllLighting();

    @Query("SELECT * FROM lighting WHERE id = :lighting_id")
    List<Lighting> getLightingById(int lighting_id);

    @Query("SELECT id FROM lighting WHERE name = :name")
    int getLightingIdByName(String name);
}
