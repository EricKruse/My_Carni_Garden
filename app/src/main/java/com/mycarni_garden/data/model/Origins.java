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

    @ColumnInfo(name = "winterStrength")
    private int winterStrength;

    //===========================================

    public Origins (String continent, String area, boolean isHighland, int winterStrength){
        this.continent = continent;
        this.area = area;
        this.isHighland = isHighland;
        this.winterStrength = winterStrength;
    }

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

    public int getWinterStrength() { return winterStrength; }

    public void setWinterStrength(int winterStrength) { this.winterStrength = winterStrength; }

    //===========================================

    @Override
    public String toString() {
        return "Origins{" +
                "continent='" + continent + '\'' +
                '}';
    }
}
