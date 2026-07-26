package com.leon1236.reforestry.api.storage;

import java.util.function.Predicate;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IBackpackInterface {
	Item createBackpack(IBackpackDefinition definition, EnumBackpackType type);

	Item createNaturalistBackpack(IBackpackDefinition definition, Identifier speciesTypeId, CreativeModeTab tab);

	Predicate<ItemStack> createNaturalistBackpackFilter(Identifier speciesTypeId);
}
