package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.Material;

import java.util.List;

@Dao
public interface MaterialDAO {
    @Insert
    void Insert(Material material);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Material> materials);

    @Update
    void Update(Material material);

    @Delete
    void Delete(Material material);

    @Query("SELECT * FROM material")
    LiveData<List<Material>> getAllMaterial();

    @Query("SELECT * FROM material WHERE material_id = :material_id")
    LiveData<Material> getMaterialById(int material_id);

    @Query("SELECT material_id FROM material WHERE name = :material_name")
    int getMaterialIdByName(String material_name);
}
