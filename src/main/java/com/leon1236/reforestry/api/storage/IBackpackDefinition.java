package com.leon1236.reforestry.api.storage;

import java.util.function.Predicate;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public interface IBackpackDefinition {
	Component getName(ItemStack backpack);

	int getPrimaryColour();

	int getSecondaryColour();

	Predicate<ItemStack> getFilter();
}
