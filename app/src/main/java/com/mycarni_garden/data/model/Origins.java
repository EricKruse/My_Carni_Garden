package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "origins")
public class Origins {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "area")
    private String area;

    @ColumnInfo(name = "continent")
    private String continent;

    @ColumnInfo(name = "isHighland")
    private boolean isHighland;

    @Ignore
    private List<Lighting> lightings;

    //===========================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public boolean isHighland() {
        return isHighland;
    }

    public void setHighland(boolean mode) {
        isHighland = mode;
    }

    //===========================================

    @Override
    public String toString() {
        return "Origins{" +
                "continent='" + continent + '\'' +
                '}';
    }
}
