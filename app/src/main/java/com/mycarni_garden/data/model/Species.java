package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity(tableName = "species",
        foreignKeys = {
                @ForeignKey(entity = Families.class, parentColumns = "id", childColumns = "family_Id")
        })
public class Species {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "maxHeightInCm")
    private Double maxHeightInCm;

    @ColumnInfo(name = "needsAirflow")
    private Boolean needsAirflow;

    @ColumnInfo(name = "lifeSpan")
    private Double lifeSpan;

    @Ignore
    private List<Substrate> substrate_ids;

    @ColumnInfo(name = "origin_Id")
    private int origin_Id;

    @ColumnInfo(name = "family_Id")
    private int family_Id;

    @ColumnInfo(name = "description")
    private String description;

    //===================================================

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMaxHeightInCm() {
        return maxHeightInCm;
    }

    public Boolean getNeedsAirflow() {
        return needsAirflow;
    }

    public Double getLifeSpan() {
        return lifeSpan;
    }

    public int getOrigin_Id() {
        return origin_Id;
    }

    public int getFamily_Id() {
        return family_Id;
    }

    public String getDescription() {
        return description;
    }

    //==================================================


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHeightInCm(Double maxHeightInCm) {
        this.maxHeightInCm = maxHeightInCm;
    }

    public void setNeedsAirflow(Boolean needsAirflow) {
        this.needsAirflow = needsAirflow;
    }

    public void setLifeSpan(Double lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setOrigin_Id(int origin_Id) {
        this.origin_Id = origin_Id;
    }

    public void setFamily_Id(int family_Id) {
        this.family_Id = family_Id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
