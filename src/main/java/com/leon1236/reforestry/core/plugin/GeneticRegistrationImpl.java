package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.apiculture.IFlowerType;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.plugin.IGeneticRegistration;
import com.leon1236.reforestry.api.plugin.ISpeciesTypeBuilder;
import com.leon1236.reforestry.api.plugin.ISpeciesTypeFactory;
import com.leon1236.reforestry.api.plugin.ITaxonBuilder;
import com.leon1236.reforestry.core.genetics.TaxonDefinition;

public final class GeneticRegistrationImpl implements IGeneticRegistration {
	private final Map<Identifier, TypeEntry> types = new LinkedHashMap<>();
	private final Map<Identifier, List<Consumer<ISpeciesTypeBuilder>>> modifiers = new LinkedHashMap<>();
	private final Map<Identifier, IFlowerType> flowerTypes = new LinkedHashMap<>();
	private final List<TaxonDefinition> definedTaxa = new ArrayList<>();

	@Override
	public void defineTaxon(String parent, String name) {
		defineTaxon(parent, name, builder -> {
		});
	}

	@Override
	public void defineTaxon(String parent, String name, Consumer<ITaxonBuilder> action) {
		definedTaxa.add(new TaxonDefinition(parent, name, null));
		action.accept(new TaxonBuilderImpl(name, definedTaxa));
	}

	@Override
	public void registerFlowerType(Identifier id, IFlowerType type) {
		flowerTypes.put(id, type);
	}

	@Override
	public ISpeciesTypeBuilder registerSpeciesType(Identifier id, ISpeciesTypeFactory typeFactory) {
		SpeciesTypeBuilderImpl builder = new SpeciesTypeBuilderImpl();
		types.put(id, new TypeEntry(typeFactory, builder));
		return builder;
	}

	@Override
	public void modifySpeciesType(Identifier id, Consumer<ISpeciesTypeBuilder> action) {
		modifiers.computeIfAbsent(id, key -> new ArrayList<>()).add(action);
	}

	public Map<Identifier, IFlowerType> flowerTypes() {
		return flowerTypes;
	}

	public List<TaxonDefinition> definedTaxa() {
		return definedTaxa;
	}

	public Map<Identifier, ISpeciesType<?, ?>> buildSpeciesTypes() {
		Map<Identifier, ISpeciesType<?, ?>> built = new LinkedHashMap<>();
		for (Map.Entry<Identifier, TypeEntry> entry : types.entrySet()) {
			SpeciesTypeBuilderImpl builder = entry.getValue().builder();
			List<Consumer<ISpeciesTypeBuilder>> typeModifiers = modifiers.getOrDefault(entry.getKey(), List.of());
			for (Consumer<ISpeciesTypeBuilder> modifier : typeModifiers) {
				modifier.accept(builder);
			}
			ISpeciesType<?, ?> type = entry.getValue().factory().create(builder.buildKaryotype(entry.getKey()), builder);
			built.put(entry.getKey(), type);
		}
		return built;
	}

	private record TypeEntry(ISpeciesTypeFactory factory, SpeciesTypeBuilderImpl builder) {
	}

	private static final class TaxonBuilderImpl implements ITaxonBuilder {
		private final String name;
		private final List<TaxonDefinition> definedTaxa;

		private TaxonBuilderImpl(String name, List<TaxonDefinition> definedTaxa) {
			this.name = name;
			this.definedTaxa = definedTaxa;
		}

		@Override
		public void defineSubTaxon(String name) {
			defineSubTaxon(name, builder -> {
			});
		}

		@Override
		public void defineSubTaxon(String name, Consumer<ITaxonBuilder> action) {
			definedTaxa.add(new TaxonDefinition(this.name, name, null));
			action.accept(new TaxonBuilderImpl(name, definedTaxa));
		}

		@Override
		public <A extends IAllele> void setDefaultChromosome(IChromosome<A> chromosome, A allele, boolean required) {
		}

		@Override
		public void setDefaultChromosome(IChromosome<?> chromosome, Identifier id, boolean required) {
		}
	}
}
