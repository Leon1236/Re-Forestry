package com.leon1236.reforestry.api.agriculture;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public interface IFarmingManager {
	default boolean isLoaded() {
		return true;
	}

	default List<IFarmable> getFarmables(Identifier farmTypeId) {
		IFarmType farmType = getFarmType(farmTypeId);
		return farmType == null ? List.of() : farmType.getFarmables();
	}

	int getFertilizeValue(ItemStack stack);

	@Nullable
	IFarmType getFarmType(Identifier id);
}
