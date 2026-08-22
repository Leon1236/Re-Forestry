package com.leon1236.reforestry.api.genetics.filter;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public interface IFilterRule {
	boolean isValid(ItemStack stack, FilterData data);

	@Nullable
	default Identifier getSpeciesTypeId() {
		return null;
	}
}
