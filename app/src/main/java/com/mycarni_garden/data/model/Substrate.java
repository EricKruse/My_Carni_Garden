package com.mycarni_garden.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "substrates")
public class Substrate {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "substrate_id")
    private int substrate_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "wateringInDays")
    private int wateringInDays;

    //=================================================

    public Substrate(String name, int wateringInDays) {
        this.name = name;
        this.wateringInDays = wateringInDays;
    }


    //=================================================

    public int getSubstrate_id() {
        return substrate_id;
    }

    public void setSubstrate_id(int id) {
        this.substrate_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWateringInDays() {
        return wateringInDays;
    }

    public void setWateringInDays(int wateringInDays) {
        this.wateringInDays = wateringInDays;
    }
}

