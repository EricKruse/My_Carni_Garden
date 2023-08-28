package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.Families;
import com.mycarni_garden.data.model.SubstrateComponent;

import java.util.List;

@Dao
public interface SubstrateComponentDAO {
    @Insert
    void Insert(SubstrateComponent comp);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<SubstrateComponent> substrateComponents);

    @Update
    void Update(SubstrateComponent comp);

    @Delete
    void Delete(SubstrateComponent comp);

    @Query("SELECT * FROM substrateComponent")
    List<SubstrateComponent> getAllComps();

    @Query("SELECT * FROM substrateComponent WHERE id = :comp_id")
    List<SubstrateComponent> getCompById(int comp_id);

    @Query("SELECT id FROM substrateComponent WHERE material_id = :material_id AND parts = :parts")
    int getCompIdByMaterialAndParts(int material_id, double parts);
}
