package com.mycarni_garden.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
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
                @ForeignKey(entity = Lighting.class, parentColumns = "lighting_id", childColumns = "lighting_id"),
                @ForeignKey(entity = Origins.class, parentColumns = "origin_id", childColumns = "origin_id")
        }
)
public class LightingOriginCrossRef {
    @ColumnInfo(name = "lighting_id")
    public int lighting_id;

    @ColumnInfo(name = "origin_id")
    public int origin_id;

    public LightingOriginCrossRef(int lighting_id, int origin_id) {
        this.lighting_id = lighting_id;
        this.origin_id = origin_id;
    }
}
