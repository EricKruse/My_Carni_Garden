package com.mycarni_garden.data.model;

import java.util.ArrayList;
import java.util.List;

import androidx.core.view.KeyEventDispatcher;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "substrates")
public class Substrate {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

