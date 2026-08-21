package com.leon1236.reforestry.api.apiculture.genetics;

import net.minecraft.world.item.ItemStack;

public interface IBeeSpeciesType {
	boolean isDrone(ItemStack stack);

	boolean isMated(ItemStack stack);
}
