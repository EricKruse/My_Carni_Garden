package com.mycarni_garden.data.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class OriginWithLighting {
    @Embedded
    public Origins origin;

    @Relation(
            parentColumn = "id",
            entityColumn = "lighting_id",
            associateBy = @Junction(LightingOriginCrossRef.class)
    )
    public List<Lighting> lighting;
}
