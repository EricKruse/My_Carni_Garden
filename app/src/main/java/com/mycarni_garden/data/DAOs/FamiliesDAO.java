package com.mycarni_garden.data.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mycarni_garden.data.model.Families;

import java.util.List;

@Dao
public interface FamiliesDAO {
    @Insert
    void Insert(Families families);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Families> families);

    @Update
    void Update(Families families);

    @Delete
    void Delete(Families families);

    @Query("SELECT * FROM families")
    LiveData<List<Families>> getAllFamilies();

    @Query("SELECT * FROM families WHERE family_id = :family_id")
    LiveData<Families> getFamilyById(int family_id);

    @Query("SELECT family_id FROM families WHERE name = :name")
    LiveData<Integer> getFamilyIdByName(String name);
}

