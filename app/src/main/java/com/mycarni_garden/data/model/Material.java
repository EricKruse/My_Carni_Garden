package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "material")
public class Material {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "material_id")
    private int material_id;

    @ColumnInfo(name = "name")
    private String name;

    //========================================


    public Material(String name) {
        this.name = name;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int id) {
        this.material_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
