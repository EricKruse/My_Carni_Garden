package com.mycarni_garden.data.model;

import androidx.room.Entity;

@Entity(
        tableName = "componentSubstrateCrossRef",
        primaryKeys = {"component_id", "substrate_id"}
)
public class ComponentSubstrateCrossRef {
    public long component_id;
    public long substrate_id;

    public ComponentSubstrateCrossRef(long component_id, long substrate_id) {
        this.component_id = component_id;
        this.substrate_id = substrate_id;
    }
}
