package com.leon1236.reforestry.api.genetics;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;

public interface IIndividual {
	IGenome getGenome();

	ISpeciesType<?, ?> getType();

	ISpecies<?> getSpecies();

	ISpecies<?> getInactiveSpecies();

	void setMate(@Nullable IGenome mate);

	@Nullable
	IGenome getMate();

	Optional<IGenome> getMateOptional();

	boolean isAnalyzed();

	boolean analyze();

	ItemStack createStack(ILifeStage stage);

	void saveToStack(ItemStack stack);

	default boolean hasGlint() {
		return getSpecies().hasGlint();
	}

	default boolean isSecret() {
		return getSpecies().isSecret();
	}

	IIndividual copy();

	IIndividual copyWithGenome(IGenome newGenome);

	@SuppressWarnings("unchecked")
	default <I extends IIndividual> I cast() {
		return (I) this;
	}
}
