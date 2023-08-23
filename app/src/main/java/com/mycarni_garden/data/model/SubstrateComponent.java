package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "substrateComponent",
        foreignKeys = {
                @ForeignKey(entity = Material.class, parentColumns = "id", childColumns = "material_id")
        })
public class SubstrateComponent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "material_id")
    private int material_id;

    @ColumnInfo(name = "parts")
    private float parts;

    //====================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public float getParts() { return parts; }

    public void setParts(float parts) { this.parts = parts; }
}
