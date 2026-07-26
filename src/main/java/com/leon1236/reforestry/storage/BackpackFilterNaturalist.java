package com.leon1236.reforestry.storage;

import java.util.function.Predicate;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.capability.IndividualItems;

public class BackpackFilterNaturalist implements Predicate<ItemStack> {
	private final Identifier speciesTypeId;

	public BackpackFilterNaturalist(Identifier speciesTypeId) {
		this.speciesTypeId = speciesTypeId;
	}

	@Override
	public boolean test(ItemStack stack) {
		Identifier typeId = IndividualItems.getSpeciesTypeId(stack);
		return typeId != null && this.speciesTypeId.equals(typeId);
	}
}
