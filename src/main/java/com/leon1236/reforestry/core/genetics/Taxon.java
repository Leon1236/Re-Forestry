package com.leon1236.reforestry.core.genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.TaxonomicRank;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public final class Taxon implements ITaxon {
	private static final Map<String, Taxon> NAME_ONLY = new ConcurrentHashMap<>();

	private final String name;
	private final TaxonomicRank rank;
	@Nullable
	private final ITaxon parent;
	private final Map<IChromosome<?>, TaxonAllele> alleles;
	private final List<ITaxon> children = new ArrayList<>();
	private final List<ISpecies<?>> members = new ArrayList<>();

	public Taxon(String name, TaxonomicRank rank, @Nullable ITaxon parent, Map<IChromosome<?>, TaxonAllele> alleles) {
		this.name = name;
		this.rank = rank;
		this.parent = parent;
		this.alleles = Map.copyOf(alleles);
		if (parent instanceof Taxon taxon) {
			taxon.children.add(this);
		}
	}

	public static ITaxon nameOnly(String name) {
		return NAME_ONLY.computeIfAbsent(name, key -> new Taxon(key, TaxonomicRank.GENUS, null, Map.of()));
	}

	public void addSpecies(ISpecies<?> species) {
		if (!members.contains(species)) {
			members.add(species);
		}
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public TaxonomicRank rank() {
		return rank;
	}

	@Override
	public List<ITaxon> children() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public List<ISpecies<?>> species() {
		return Collections.unmodifiableList(members);
	}

	@Override
	@Nullable
	public ITaxon parent() {
		return parent;
	}

	@Override
	public Map<IChromosome<?>, TaxonAllele> alleles() {
		return alleles;
	}
}
