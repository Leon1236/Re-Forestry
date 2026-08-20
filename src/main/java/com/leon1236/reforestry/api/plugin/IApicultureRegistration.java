package com.leon1236.reforestry.api.plugin;

import java.util.Map;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.genetics.IBeeEffect;
import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface IApicultureRegistration {
    IBeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor);

    IHiveBuilder registerHive(Identifier id, IHiveDefinition definition);

    void registerBeeEffect(Identifier id, IBeeEffect effect);

    void addVillageBee(Identifier speciesId, boolean rare, Map<IChromosome<?>, IAllele> alleles);

    default void addVillageBee(Identifier speciesId, boolean rare) {
        addVillageBee(speciesId, rare, Map.of());
    }
}
