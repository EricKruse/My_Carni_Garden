package com.mycarni_garden.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        tableName = "substrateSpeciesCrossRef",
        primaryKeys = {"species_id", "substrate_id"},
        indices = {
                @Index("species_id"),
                @Index("substrate_id")
        },
        foreignKeys = {
                @ForeignKey(entity = Species.class, parentColumns = "id", childColumns = "species_id"),
                @ForeignKey(entity = Substrate.class, parentColumns = "id", childColumns = "substrate_id")
        }
)
public class SubstrateSpeciesCrossRef {
    public long species_id;
    public long substrate_id;

    public SubstrateSpeciesCrossRef(long species_id, long substrate_id) {
        this.species_id = species_id;
        this.substrate_id = substrate_id;
    }
}
