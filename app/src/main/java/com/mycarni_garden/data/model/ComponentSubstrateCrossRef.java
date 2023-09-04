package com.mycarni_garden.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "componentSubstrateCrossRef",
        primaryKeys = {"component_id", "substrate_id"},
        indices = {
                @Index("component_id"),
                @Index("substrate_id")
        },
        foreignKeys = {
                @ForeignKey(entity = SubstrateComponent.class, parentColumns = "component_id", childColumns = "component_id"),
                @ForeignKey(entity = Substrate.class, parentColumns = "substrate_id", childColumns = "substrate_id")
        }
)
public class ComponentSubstrateCrossRef {
    public long component_id;
    public long substrate_id;

    public ComponentSubstrateCrossRef(long component_id, int substrate_id) {
        this.component_id = component_id;
        this.substrate_id = substrate_id;
    }
}
