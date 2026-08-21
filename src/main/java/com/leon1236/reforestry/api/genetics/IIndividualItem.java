package com.leon1236.reforestry.api.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;

public interface IIndividualItem {
	@Nullable
	IIndividual getIndividualFromComponent(ItemStack stack);

	ILifeStage getLifeStage();

	ISpeciesType<?, ?> getSpeciesType();
}
