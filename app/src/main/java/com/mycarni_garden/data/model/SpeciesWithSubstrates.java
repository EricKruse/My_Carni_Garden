package com.mycarni_garden.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class SpeciesWithSubstrates {
    @Embedded
    public Species species;

    @Relation(
            parentColumn = "species_id",
            entityColumn = "substrate_id",
            associateBy = @Junction(SubstrateSpeciesCrossRef.class)
    )
    public List<Substrate> substrates;
}
