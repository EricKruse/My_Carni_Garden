package com.mycarni_garden.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "families")
public class Families {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "family_id")
    private int family_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "eng_name")
    private String eng_name;

    //=============================================

    public Families (String name, String eng_name){
        this.name = name;
        this.eng_name = eng_name;
    }

    public int getFamily_id() {
        return family_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily_id(int id) {
        this.family_id = id;
    }

    public String getEng_name() { return eng_name; }

    public void setEng_name(String eng_name) {  this.eng_name = eng_name; }

    @Override
    public String toString(){
        return name;
    }
}
