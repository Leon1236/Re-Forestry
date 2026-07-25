package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.mutations.MutationBuilder;
import com.leon1236.reforestry.core.genetics.mutations.MutationPair;

public final class ApicultureGenetics {
    private static final Map<Identifier, BeeSpeciesBuilder> builders = new LinkedHashMap<>();
    private static final Map<Identifier, IBeeSpecies> speciesById = new LinkedHashMap<>();
    private static final Map<Identifier, IGenome> defaultGenomes = new LinkedHashMap<>();
    private static final Map<MutationPair, List<Mutation>> mutationsByPair = new LinkedHashMap<>();
    private static boolean finalized = false;

    private ApicultureGenetics() {
    }

    public static BeeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int outlineColor) {
        if (finalized) {
            throw new IllegalStateException("Bee species registration is already finalized");
        }
        BeeSpeciesBuilder builder = new BeeSpeciesBuilder(id, genus, species, dominant, outlineColor);
        builders.put(id, builder);
        return builder;
    }

    public static void finalizeRegistration() {
        if (finalized) {
            throw new IllegalStateException("Bee species registration is already finalized");
        }
        for (Map.Entry<Identifier, BeeSpeciesBuilder> entry : builders.entrySet()) {
            BeeSpeciesBuilder builder = entry.getValue();
            IBeeSpecies species = builder.buildSpecies();
            IRegistryAllele<IBeeSpecies> allele = AlleleManager.INSTANCE.registryAllele(species, builder.dominant());
            IGenome genome = builder.buildGenome(allele);
            speciesById.put(entry.getKey(), species);
            defaultGenomes.put(entry.getKey(), genome);
        }
        BeeChromosomes.SPECIES.populate(ImmutableMap.copyOf(speciesById));
        for (BeeSpeciesBuilder builder : builders.values()) {
            for (MutationBuilder mutationBuilder : builder.mutations().builders()) {
                Mutation mutation = mutationBuilder.build(builder.id());
                mutationsByPair.computeIfAbsent(MutationPair.of(mutation.firstParent(), mutation.secondParent()),
                        pair -> new ArrayList<>()).add(mutation);
            }
        }
        finalized = true;
    }

    public static List<Mutation> getMutations(Identifier firstSpecies, Identifier secondSpecies) {
        return mutationsByPair.getOrDefault(MutationPair.of(firstSpecies, secondSpecies), List.of());
    }

    public static IBeeSpecies getSpecies(Identifier id) {
        IBeeSpecies species = speciesById.get(id);
        if (species == null) {
            throw new IllegalArgumentException("Unknown bee species: " + id);
        }
        return species;
    }

    public static IGenome getDefaultGenome(Identifier id) {
        IGenome genome = defaultGenomes.get(id);
        if (genome == null) {
            throw new IllegalArgumentException("Unknown bee species: " + id);
        }
        return genome;
    }

    public static java.util.Collection<Identifier> getAllSpeciesIds() {
        return speciesById.keySet();
    }
}
