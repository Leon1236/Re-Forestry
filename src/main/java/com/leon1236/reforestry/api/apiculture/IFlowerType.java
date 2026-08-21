package com.leon1236.reforestry.api.apiculture;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IFlowerType extends IRegistryAlleleValue {
	boolean isAcceptableFlower(Level level, BlockPos pos);

	default boolean plantRandomFlower(Level level, BlockPos pos, List<BlockState> nearbyFlowers) {
		return false;
	}

	default List<ItemStack> affectProducts(Level level, BlockPos pos, IIndividual individual, List<ItemStack> products) {
		return products;
	}
}
