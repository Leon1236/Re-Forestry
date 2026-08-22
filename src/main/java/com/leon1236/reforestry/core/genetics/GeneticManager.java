package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.IGeneticManager;
import com.leon1236.reforestry.api.genetics.IKaryotype;
import com.leon1236.reforestry.api.genetics.IMutationManager;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.TaxonomicRank;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public final class GeneticManager implements IGeneticManager {
	private final Map<Identifier, ISpeciesType<?, ?>> speciesTypes = new LinkedHashMap<>();
	private volatile ImmutableMap<String, ITaxon> taxa = ImmutableMap.of();

	public void registerSpeciesType(ISpeciesType<?, ?> type) {
		speciesTypes.put(type.id(), type);
	}

	public void applyDatapackTaxa(Collection<TaxonDefinition> definitions) {
		if (definitions.isEmpty()) {
			taxa = ImmutableMap.of();
			rebuildDefaultGenomes();
			return;
		}

		Map<String, ITaxon> merged = new LinkedHashMap<>();
		ArrayList<TaxonDefinition> pending = new ArrayList<>(definitions);
		boolean progress = true;
		while (progress && !pending.isEmpty()) {
			progress = false;
			Iterator<TaxonDefinition> it = pending.iterator();
			while (it.hasNext()) {
				TaxonDefinition def = it.next();
				boolean isRoot = def.parent() == null;
				ITaxon parent = null;
				if (!isRoot) {
					parent = merged.get(def.parent());
					if (parent == null) {
						continue;
					}
				}
				it.remove();
				progress = true;
				if (isRoot && def.rank() == null) {
					ReForestry.LOGGER.warn("Datapack taxon '{}' skipped: a root taxon must declare a rank", def.name());
				} else if (!isRoot && parent.rank() == TaxonomicRank.GENUS) {
					ReForestry.LOGGER.warn("Datapack taxon '{}' skipped: parent '{}' is a genus", def.name(), def.parent());
				} else if (merged.containsKey(def.name())) {
					ReForestry.LOGGER.warn("Datapack taxon '{}' skipped: already registered", def.name());
				} else {
					TaxonomicRank rank = def.rank() != null ? def.rank() : parent.rank().next();
					merged.put(def.name(), new Taxon(def.name(), rank, parent, buildTaxonAlleles(def)));
				}
			}
		}
		for (TaxonDefinition def : pending) {
			ReForestry.LOGGER.warn("Datapack taxon '{}' skipped: parent '{}' was never registered", def.name(), def.parent());
		}
		taxa = ImmutableMap.copyOf(merged);
		rebuildDefaultGenomes();
	}

	private Map<IChromosome<?>, ITaxon.TaxonAllele> buildTaxonAlleles(TaxonDefinition def) {
		if (def.alleles().isEmpty()) {
			return Map.of();
		}
		if (def.type() == null) {
			ReForestry.LOGGER.warn("Taxon '{}' declares alleles but species type is unknown; ignoring its defaults",
					def.name());
			return Map.of();
		}
		ISpeciesType<?, ?> type = getSpeciesTypeSafe(def.type());
		if (type == null) {
			ReForestry.LOGGER.warn("Taxon '{}' declares alleles but species type '{}' is unknown; ignoring its defaults",
					def.name(), def.type());
			return Map.of();
		}
		IKaryotype karyotype = type.getKaryotype();
		Map<IChromosome<?>, ITaxon.TaxonAllele> result = new LinkedHashMap<>();
		for (var entry : def.alleles().entrySet()) {
			IChromosome<?> chromosome = entry.getKey();
			if (!karyotype.chromosomes().contains(chromosome)) {
				ReForestry.LOGGER.warn(
						"Taxon '{}' default allele skipped: chromosome '{}' is invalid for species type '{}'",
						def.name(), chromosome.id(), def.type());
				continue;
			}
			result.put(chromosome, entry.getValue());
		}
		return Map.copyOf(result);
	}

	private static void rebuildDefaultGenomes() {
		com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics.rebuildDefaultGenomes();
		com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics.rebuildDefaultGenomes();
		com.leon1236.reforestry.lepidopterology.genetics.LepidopterologyGenetics.rebuildDefaultGenomes();
	}

	@Override
	public ITaxon getTaxon(String name) {
		ITaxon taxon = taxa.get(name);
		if (taxon == null) {
			throw new IllegalStateException("No taxon was registered with name '" + name + "'");
		}
		return taxon;
	}

	@Override
	@Nullable
	public ITaxon getTaxonSafe(String name) {
		return taxa.get(name);
	}

	@Override
	public ITaxon[] getParentTaxa(String name) {
		ITaxon taxon = getTaxon(name);
		int ordinal = taxon.rank().ordinal();
		ITaxon[] parents = new ITaxon[1 + ordinal];
		for (int i = ordinal; i >= 0; i--) {
			parents[i] = taxon;
			taxon = taxon.parent();
		}
		return parents;
	}

	@Override
	public IMutationManager getMutations(ISpeciesType<?, ?> speciesType) {
		return speciesType.getMutations();
	}

	@Override
	public ISpeciesType<?, ?> getSpeciesType(Identifier speciesTypeId) {
		ISpeciesType<?, ?> type = speciesTypes.get(speciesTypeId);
		if (type == null) {
			throw new IllegalStateException("No species type was registered with ID: " + speciesTypeId);
		}
		return type;
	}

	@Override
	@Nullable
	public ISpeciesType<?, ?> getSpeciesTypeSafe(Identifier speciesTypeId) {
		return speciesTypes.get(speciesTypeId);
	}

	@Override
	public Collection<ISpeciesType<?, ?>> getSpeciesTypes() {
		return speciesTypes.values();
	}
}
