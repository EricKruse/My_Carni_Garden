package com.mycarni_garden.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"lighting_id", "origin_id"})
public class LightingOriginCrossRef {
    public long lighting_id;
    public long origin_id;

    public LightingOriginCrossRef(long lighting_id, long origin_id) {
        this.lighting_id = lighting_id;
        this.origin_id = origin_id;
    }
}
