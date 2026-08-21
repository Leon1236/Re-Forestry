package com.leon1236.reforestry.api.arboriculture.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;

public interface ITreeSpeciesType {
	IFruit getFruit(Identifier id);

	@Nullable
	IFruit getFruitSafe(Identifier id);

	ITreeEffect getTreeEffect(Identifier id);

	ITree getTree(IGenome genome);

	@Nullable
	IIndividual getVanillaIndividual(BlockState state);

	@Nullable
	IIndividual getVanillaIndividual(Item item);
}
