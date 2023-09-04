package com.mycarni_garden.data.operations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.mycarni_garden.data.model.Lighting;
import com.mycarni_garden.data.model.LightingOriginCrossRef;
import com.mycarni_garden.data.model.Origins;

import java.util.List;

public class OriginWithLighting {
    @Embedded
    public Origins origin;

    @Relation(
            parentColumn = "origin_id",
            entityColumn = "lighting_id",
            associateBy = @Junction(LightingOriginCrossRef.class)
    )
    public List<Lighting> lighting;
}
