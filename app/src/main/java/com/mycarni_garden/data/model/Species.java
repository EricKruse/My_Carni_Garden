package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity(tableName = "species",
        indices = {
                @Index("species_id")
        },
        foreignKeys = {
                @ForeignKey(entity = Families.class, parentColumns = "family_id", childColumns = "family_id"),
                @ForeignKey(entity = Origins.class, parentColumns = "origin_id", childColumns = "origin_id")
        })
public class Species {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "species_id")
    private int species_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "maxHeightInCm")
    private int maxHeightInCm;

    @ColumnInfo(name = "lifeSpan")
    private String lifeSpan;

    @ColumnInfo(name = "origin_id")
    private int origin_id;

    @ColumnInfo(name = "family_id")
    private int family_id;

    @ColumnInfo(name = "description")
    private String description;

    public Species(String name, int maxHeightInCm, String lifeSpan, int origin_id, int family_id, String description) {
        this.name = name;
        this.maxHeightInCm = maxHeightInCm;
        this.lifeSpan = lifeSpan;
        this.origin_id = origin_id;
        this.family_id = family_id;
        this.description = description;
    }

    //===================================================

    public int getSpecies_id() {
        return species_id;
    }

    public String getName() {
        return name;
    }

    public int getMaxHeightInCm() {
        return maxHeightInCm;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public int getOrigin_id() {
        return origin_id;
    }

    public int getFamily_id() {
        return family_id;
    }

    public String getDescription() {
        return description;
    }

    //==================================================


    public void setSpecies_id(int id) {
        this.species_id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHeightInCm(int maxHeightInCm) {
        this.maxHeightInCm = maxHeightInCm;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public void setOrigin_id(int origin_id) {
        this.origin_id = origin_id;
    }

    public void setFamily_id(int family_id) {
        this.family_id = family_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

