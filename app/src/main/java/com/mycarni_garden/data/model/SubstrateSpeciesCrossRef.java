package com.mycarni_garden.data.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"species_id", "substrate_id"})
public class SubstrateSpeciesCrossRef {
    public long species_id;
    public long substrate_id;
}
