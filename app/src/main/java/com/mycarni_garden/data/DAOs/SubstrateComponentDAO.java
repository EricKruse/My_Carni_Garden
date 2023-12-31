package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
    LiveData<List<SubstrateComponent>> getAllComps();

    @Query("SELECT * FROM substrateComponent WHERE component_id = :component_id")
    LiveData<SubstrateComponent> getCompById(int component_id);

    @Query("SELECT material_id FROM substrateComponent WHERE material_id = :material_id AND parts = :parts")
    int getCompIdByMaterialAndParts(int material_id, double parts);
}
