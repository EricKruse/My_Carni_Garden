package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lighting")
public class Lighting {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lighting_id")
    private int lighting_id;

    @ColumnInfo(name = "name")
    private String name;

    //===================================

    public Lighting(String name) {
        this.name = name;
    }

    public int getLighting_id() {
        return lighting_id;
    }

    public void setLighting_id(int id) {
        this.lighting_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
