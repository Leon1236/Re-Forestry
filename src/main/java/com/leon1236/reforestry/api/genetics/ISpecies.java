package com.leon1236.reforestry.api.genetics;

import java.util.List;
import java.util.Map;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;

public interface ISpecies<I extends IIndividual> {
	String getTranslationKey();

	default MutableComponent getDisplayName() {
		return Component.translatable(getTranslationKey());
	}

	default String getDescriptionTranslationKey() {
		return getTranslationKey() + ".desc";
	}

	IGenome getDefaultGenome();

	Identifier id();

	String getBinomial();

	String getSpeciesName();

	ITaxon getGenus();

	default String getGenusName() {
		return getGenus().name();
	}

	ISpeciesType<? extends ISpecies<I>, I> getType();

	boolean isSecret();

	int getComplexity();

	default ItemStack createStack(I individual, ILifeStage stage) {
		return getType().createStack(individual, stage);
	}

	default ItemStack createStack(ILifeStage stage) {
		return createStack(createIndividual(), stage);
	}

	I createIndividual(Map<IChromosome<?>, IAllele> alleles);

	I createIndividualFromPairs(Map<IChromosome<?>, AllelePair<?>> allelePairs);

	I createIndividual(IGenome genome);

	default I createIndividual() {
		return createIndividual(getDefaultGenome());
	}

	default IKaryotype getKaryotype() {
		return getDefaultGenome().karyotype();
	}

	boolean hasGlint();

	boolean isDominant();

	String getAuthority();

	int getEscritoireColor();

	@SuppressWarnings("unchecked")
	default <S extends ISpecies<?>> S cast() {
		return (S) this;
	}

	void addTooltip(I individual, List<Component> tooltip);
}
