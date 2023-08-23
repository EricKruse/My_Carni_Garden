package com.mycarni_garden.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.SubstrateComponent;

import java.util.List;

@Dao
public interface SubstrateComponentDAO {
    @Insert
    void Insert(SubstrateComponent comp);

    @Update
    void Update(SubstrateComponent comp);

    @Delete
    void Delete(SubstrateComponent comp);

    @Query("SELECT * FROM substrateComponent")
    List<SubstrateComponent> getAllComps();

    @Query("SELECT * FROM substrateComponent WHERE id = :comp_id")
    List<SubstrateComponent> getCompById(int comp_id);
}
