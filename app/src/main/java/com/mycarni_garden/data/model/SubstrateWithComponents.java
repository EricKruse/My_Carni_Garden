package com.mycarni_garden.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class SubstrateWithComponents {
    @Embedded
    public Substrate substrate;

    @Relation(
            parentColumn = "id",
            entityColumn = "component_id",
            associateBy = @Junction(ComponentSubstrateCrossRef.class)
    )
    public List<SubstrateComponent> components;
}
