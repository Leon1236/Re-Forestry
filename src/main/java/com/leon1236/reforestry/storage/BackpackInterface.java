package com.leon1236.reforestry.storage;

import java.util.Objects;
import java.util.function.Predicate;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.storage.EnumBackpackType;
import com.leon1236.reforestry.api.storage.IBackpackDefinition;
import com.leon1236.reforestry.api.storage.IBackpackInterface;

public class BackpackInterface implements IBackpackInterface {
	@Override
	public Item createBackpack(IBackpackDefinition definition, EnumBackpackType type) {
		Objects.requireNonNull(definition, "definition must not be null");
		Objects.requireNonNull(type, "type must not be null");
		if (type == EnumBackpackType.NATURALIST) {
			throw new IllegalArgumentException("type must not be NATURALIST. Use createNaturalistBackpack instead.");
		}
		throw new UnsupportedOperationException("ItemBackpack lands in Track B1");
	}

	@Override
	public Item createNaturalistBackpack(IBackpackDefinition definition, Identifier speciesTypeId, CreativeModeTab tab) {
		Objects.requireNonNull(definition, "definition must not be null");
		Objects.requireNonNull(speciesTypeId, "speciesTypeId must not be null");
		Objects.requireNonNull(tab, "tab must not be null");
		throw new UnsupportedOperationException("ItemBackpackNaturalist lands in Track B4");
	}

	@Override
	public Predicate<ItemStack> createNaturalistBackpackFilter(Identifier speciesTypeId) {
		Objects.requireNonNull(speciesTypeId, "speciesTypeId must not be null");
		return new BackpackFilterNaturalist(speciesTypeId);
	}
}
