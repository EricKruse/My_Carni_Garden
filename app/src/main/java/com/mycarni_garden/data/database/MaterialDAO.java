package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.Material;

import java.util.List;

@Dao
public interface MaterialDAO {
    @Insert
    void Insert(Material material);

    @Update
    void Update(Material material);

    @Delete
    void Delete(Material material);

    @Query("SELECT * FROM material")
    List<Material> getAllMaterial();

    @Query("SELECT * FROM material WHERE id = :material_id")
    List<Material> getMaterialById(int material_id);
}
