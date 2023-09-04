package com.mycarni_garden.data.operations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.mycarni_garden.data.model.Species;
import com.mycarni_garden.data.model.Substrate;
import com.mycarni_garden.data.model.SubstrateSpeciesCrossRef;

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
