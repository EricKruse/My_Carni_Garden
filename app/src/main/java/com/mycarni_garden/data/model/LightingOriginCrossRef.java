package com.mycarni_garden.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"lighting_id", "origin_id"})
public class LightingOriginCrossRef {
    public long lighting_id;
    public long origin_id;
}
