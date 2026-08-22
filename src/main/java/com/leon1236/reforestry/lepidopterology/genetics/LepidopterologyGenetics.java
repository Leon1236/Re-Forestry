package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.api.plugin.IButterflySpeciesBuilder;
import com.leon1236.reforestry.core.ForestryApiImpl;
import com.leon1236.reforestry.core.genetics.IdentifierMutationManager;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.mutations.MutationBuilder;
import com.leon1236.reforestry.core.genetics.mutations.MutationPair;

public final class LepidopterologyGenetics {
	private static final Map<Identifier, ButterflySpeciesBuilder> builders = new LinkedHashMap<>();
	private static final Map<Identifier, IButterflySpecies> speciesById = new LinkedHashMap<>();
	private static final Map<Identifier, IGenome> defaultGenomes = new LinkedHashMap<>();
	private static final Map<MutationPair, List<Mutation>> mutationsByPair = new LinkedHashMap<>();
	private static boolean finalized = false;

	private LepidopterologyGenetics() {
	}

	public static ButterflySpeciesBuilder registerSpecies(Identifier id, String genus, String species, boolean dominant,
			int serumColor, float rarity) {
		if (finalized) {
			throw new IllegalStateException("Butterfly species registration is already finalized");
		}
		ButterflySpeciesBuilder builder = new ButterflySpeciesBuilder(id, genus, species, dominant, serumColor, rarity);
		builders.put(id, builder);
		return builder;
	}

	public static void modifySpecies(Identifier id, Consumer<IButterflySpeciesBuilder> action) {
		if (finalized) {
			throw new IllegalStateException("Butterfly species registration is already finalized");
		}
		ButterflySpeciesBuilder builder = builders.get(id);
		if (builder == null) {
			throw new IllegalArgumentException("Unknown butterfly species: " + id);
		}
		action.accept(builder);
	}

	public static void finalizeRegistration() {
		if (finalized) {
			throw new IllegalStateException("Butterfly species registration is already finalized");
		}
		for (Map.Entry<Identifier, ButterflySpeciesBuilder> entry : builders.entrySet()) {
			ButterflySpeciesBuilder builder = entry.getValue();
			IButterflySpecies species = builder.buildSpecies();
			IRegistryAllele<IButterflySpecies> allele = AlleleManager.INSTANCE.registryAllele(species, builder.dominant());
			IGenome genome = builder.buildGenome(allele);
			speciesById.put(entry.getKey(), species);
			defaultGenomes.put(entry.getKey(), genome);
		}
		ButterflyChromosomes.SPECIES.populate(ImmutableMap.copyOf(speciesById));
		ButterflySpeciesType.INSTANCE.onSpeciesRegistered(ImmutableMap.copyOf(speciesById));
		for (ButterflySpeciesBuilder builder : builders.values()) {
			for (MutationBuilder mutationBuilder : builder.mutations().builders()) {
				Mutation mutation = mutationBuilder.build(ForestrySpeciesTypes.BUTTERFLY, builder.id());
				mutationsByPair.computeIfAbsent(MutationPair.of(mutation.firstParent(), mutation.secondParent()),
						pair -> new ArrayList<>()).add(mutation);
			}
		}
		ButterflySpeciesType.INSTANCE.setMutations(new IdentifierMutationManager(getAllMutations()));
		ForestryApiImpl.get().getMutableGeneticManager().registerSpeciesType(ButterflySpeciesType.INSTANCE);
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

	public static IButterflySpecies getSpecies(Identifier id) {
		IButterflySpecies species = speciesById.get(id);
		if (species == null) {
			throw new IllegalArgumentException("Unknown butterfly species: " + id);
		}
		return species;
	}

	@Nullable
	public static IButterflySpecies getSpeciesSafe(Identifier id) {
		return speciesById.get(id);
	}

	public static IGenome getDefaultGenome(Identifier id) {
		IGenome genome = defaultGenomes.get(id);
		if (genome == null) {
			throw new IllegalArgumentException("Unknown butterfly species: " + id);
		}
		return genome;
	}

	public static Collection<Identifier> getAllSpeciesIds() {
		return speciesById.keySet();
	}

	public static Collection<IButterflySpecies> getAllSpecies() {
		return speciesById.values();
	}

	public static Map<Identifier, IButterflySpecies> getSpeciesById() {
		return speciesById;
	}

	@Nullable
	public static Mutation findMutation(Identifier parent0, Identifier parent1, @Nullable Identifier result) {
		for (List<Mutation> mutations : mutationsByPair.values()) {
			for (Mutation mutation : mutations) {
				if (mutation.isPartner(parent0) && mutation.isPartner(parent1)
						&& (result == null || mutation.result().equals(result))) {
					return mutation;
				}
			}
		}
		return null;
	}

	public static void rebuildDefaultGenomes() {
		if (!finalized) {
			return;
		}
		for (Map.Entry<Identifier, ButterflySpeciesBuilder> entry : builders.entrySet()) {
			ButterflySpeciesBuilder builder = entry.getValue();
			IButterflySpecies species = speciesById.get(entry.getKey());
			if (species == null) {
				continue;
			}
			IRegistryAllele<IButterflySpecies> allele = AlleleManager.INSTANCE.registryAllele(species,
					builder.dominant());
			defaultGenomes.put(entry.getKey(), builder.buildGenome(allele));
		}
	}
}
