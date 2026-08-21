package com.leon1236.reforestry.api.arboriculture.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IIndividual;

public interface ITreeSpeciesType {
	@Nullable
	IIndividual getVanillaIndividual(BlockState state);

	@Nullable
	IIndividual getVanillaIndividual(Item item);
}
