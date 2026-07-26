package com.leon1236.reforestry.arboriculture.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
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
    private static final IdentityHashMap<BlockState, IGenome> vanillaIndividuals = new IdentityHashMap<>();
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
                Mutation mutation = mutationBuilder.build(ForestrySpeciesTypes.TREE, builder.id());
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
        vanillaIndividuals.clear();
        for (ITreeSpecies species : speciesById.values()) {
            IGenome genome = defaultGenomes.get(species.id());
            for (BlockState state : species.getVanillaLeafStates()) {
                vanillaIndividuals.put(state, genome);
            }
        }
        finalized = true;
    }

    @Nullable
    public static IGenome getVanillaIndividual(BlockState state) {
        return vanillaIndividuals.get(state);
    }

    public static List<Mutation> getMutations(Identifier firstSpecies, Identifier secondSpecies) {
        return mutationsByPair.getOrDefault(MutationPair.of(firstSpecies, secondSpecies), List.of());
    }

    public static List<Mutation> getAllMutations() {
        List<Mutation> all = new ArrayList<>();
        for (List<Mutation> mutations : mutationsByPair.values()) {
            all.addAll(mutations);
        }
        return all;
    }

    public static List<Mutation> getMutationsFrom(Identifier species) {
        List<Mutation> found = new ArrayList<>();
        for (List<Mutation> mutations : mutationsByPair.values()) {
            for (Mutation mutation : mutations) {
                if (mutation.isPartner(species)) {
                    found.add(mutation);
                }
            }
        }
        return found;
    }

    @Nullable
    public static Mutation findMutation(Identifier parent0, Identifier parent1, @Nullable Identifier result) {
        for (Mutation mutation : getMutationsFrom(parent0)) {
            if (mutation.isPartner(parent1) && (result == null || mutation.result().equals(result))) {
                return mutation;
            }
        }
        return null;
    }

    @Nullable
    public static ITreeSpecies getSpeciesSafe(Identifier id) {
        return speciesById.get(id);
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

    public static Collection<ITreeSpecies> getAllSpecies() {
        return speciesById.values();
    }
}
