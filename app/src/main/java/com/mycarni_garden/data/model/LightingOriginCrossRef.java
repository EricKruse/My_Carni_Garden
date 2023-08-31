package com.mycarni_garden.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "lightingOriginCrossRef",
        primaryKeys = {"lighting_id", "origin_id"},
        indices = {
                @Index("lighting_id"),
                @Index("origin_id")
        },
        foreignKeys = {
                @ForeignKey(entity = Lighting.class, parentColumns = "id", childColumns = "lighting_id"),
                @ForeignKey(entity = Origins.class, parentColumns = "id", childColumns = "origin_id")
        }
)
public class LightingOriginCrossRef {
    public long lighting_id;
    public long origin_id;

    public LightingOriginCrossRef(long lighting_id, long origin_id) {
        this.lighting_id = lighting_id;
        this.origin_id = origin_id;
    }
}
