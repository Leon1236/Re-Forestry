package com.leon1236.reforestry.arboriculture.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.mutations.MutationBuilder;
import com.leon1236.reforestry.core.genetics.mutations.MutationPair;

public final class ArboricultureGenetics {
    private static final Map<Identifier, TreeSpeciesBuilder> builders = new LinkedHashMap<>();
    private static final Map<Identifier, ITreeSpecies> speciesById = new LinkedHashMap<>();
    private static final Map<Identifier, IGenome> defaultGenomes = new LinkedHashMap<>();
    private static final Map<MutationPair, List<Mutation>> mutationsByPair = new LinkedHashMap<>();
    private static boolean finalized = false;

    private ArboricultureGenetics() {
    }

    public static TreeSpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType) {
        if (finalized) {
            throw new IllegalStateException("Tree species registration is already finalized");
        }
        TreeSpeciesBuilder builder = new TreeSpeciesBuilder(id, genus, species, dominant, escritoireColor, woodType);
        builders.put(id, builder);
        return builder;
    }

    public static void finalizeRegistration() {
        if (finalized) {
            throw new IllegalStateException("Tree species registration is already finalized");
        }
        for (Map.Entry<Identifier, TreeSpeciesBuilder> entry : builders.entrySet()) {
            TreeSpeciesBuilder builder = entry.getValue();
            ITreeSpecies species = builder.buildSpecies();
            IRegistryAllele<ITreeSpecies> allele = AlleleManager.INSTANCE.registryAllele(species, builder.dominant());
            IGenome genome = builder.buildGenome(allele);
            speciesById.put(entry.getKey(), species);
            defaultGenomes.put(entry.getKey(), genome);
        }
        TreeChromosomes.SPECIES.populate(ImmutableMap.copyOf(speciesById));
        for (TreeSpeciesBuilder builder : builders.values()) {
            for (MutationBuilder mutationBuilder : builder.mutations().builders()) {
                Mutation mutation = mutationBuilder.build(builder.id());
                mutationsByPair.computeIfAbsent(MutationPair.of(mutation.firstParent(), mutation.secondParent()),
                        pair -> new ArrayList<>()).add(mutation);
            }
        }
        for (ForestryLeafType leafType : ForestryLeafType.allValues()) {
            ITreeSpecies species = speciesById.get(leafType.getSpeciesId());
            if (species == null) {
                throw new IllegalStateException("Invalid ForestryLeafType " + leafType.getSerializedName()
                        + ": no tree species found with ID: " + leafType.getSpeciesId());
            }
            leafType.setSpecies(species);
        }
        finalized = true;
    }

    public static List<Mutation> getMutations(Identifier firstSpecies, Identifier secondSpecies) {
        return mutationsByPair.getOrDefault(MutationPair.of(firstSpecies, secondSpecies), List.of());
    }

    public static ITreeSpecies getSpecies(Identifier id) {
        ITreeSpecies species = speciesById.get(id);
        if (species == null) {
            throw new IllegalArgumentException("Unknown tree species: " + id);
        }
        return species;
    }

    public static IGenome getDefaultGenome(Identifier id) {
        IGenome genome = defaultGenomes.get(id);
        if (genome == null) {
            throw new IllegalArgumentException("Unknown tree species: " + id);
        }
        return genome;
    }

    public static Collection<Identifier> getAllSpeciesIds() {
        return speciesById.keySet();
    }
}
