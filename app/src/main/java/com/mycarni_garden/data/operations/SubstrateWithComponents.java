package com.mycarni_garden.data.operations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.mycarni_garden.data.model.ComponentSubstrateCrossRef;
import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.model.SubstrateComponent;

import java.util.List;

public class SubstrateWithComponents {
    @Embedded
    public Substrate substrate;

    @Relation(
            parentColumn = "substrate_id",
            entityColumn = "component_id",
            associateBy = @Junction(ComponentSubstrateCrossRef.class)
    )
    public List<SubstrateComponent> components;
}
