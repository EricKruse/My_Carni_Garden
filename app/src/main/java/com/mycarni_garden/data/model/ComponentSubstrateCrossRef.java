package com.mycarni_garden.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"component_id", "substrate_id"})
public class ComponentSubstrateCrossRef {
    public long component_id;
    public long substrate_id;
}
