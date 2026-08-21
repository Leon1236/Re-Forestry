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
import com.leon1236.reforestry.api.genetics.IMutationManager;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.TaxonomicRank;

public final class GeneticManager implements IGeneticManager {
	private final Map<Identifier, ISpeciesType<?, ?>> speciesTypes = new LinkedHashMap<>();
	private volatile ImmutableMap<String, ITaxon> taxa = ImmutableMap.of();

	public void registerSpeciesType(ISpeciesType<?, ?> type) {
		speciesTypes.put(type.id(), type);
	}

	public void applyDatapackTaxa(Collection<TaxonDefinition> definitions) {
		if (definitions.isEmpty()) {
			taxa = ImmutableMap.of();
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
					merged.put(def.name(), new Taxon(def.name(), rank, parent, Map.of()));
				}
			}
		}
		for (TaxonDefinition def : pending) {
			ReForestry.LOGGER.warn("Datapack taxon '{}' skipped: parent '{}' was never registered", def.name(), def.parent());
		}
		taxa = ImmutableMap.copyOf(merged);
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
