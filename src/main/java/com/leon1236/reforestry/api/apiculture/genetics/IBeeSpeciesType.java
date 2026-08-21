package com.leon1236.reforestry.api.apiculture.genetics;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.apiculture.IActivityType;
import com.leon1236.reforestry.api.apiculture.IBeeJubilance;

public interface IBeeSpeciesType {
	IBeeEffect getBeeEffect(Identifier id);

	IActivityType getActivityType(Identifier id);

	IBeeJubilance getJubilance(Identifier id);

	@Nullable
	IBeeJubilance getJubilanceSafe(Identifier id);

	boolean isDrone(ItemStack stack);

	boolean isMated(ItemStack stack);
}
