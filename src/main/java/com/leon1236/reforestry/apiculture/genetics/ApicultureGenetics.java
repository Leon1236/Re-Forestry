package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
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
                Mutation mutation = mutationBuilder.build(ForestrySpeciesTypes.BEE, builder.id());
                mutationsByPair.computeIfAbsent(MutationPair.of(mutation.firstParent(), mutation.secondParent()),
                        pair -> new ArrayList<>()).add(mutation);
            }
        }
        finalized = true;
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
