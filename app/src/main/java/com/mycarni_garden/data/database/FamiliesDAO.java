package com.mycarni_garden.data.database;

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
    List<Families> getAllFamilies();

    @Query("SELECT * FROM families WHERE id = :family_id")
    List<Families> getFamilyById(int family_id);
}

