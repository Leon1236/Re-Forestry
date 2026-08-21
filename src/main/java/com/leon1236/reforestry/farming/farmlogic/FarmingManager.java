package com.leon1236.reforestry.farming.farmlogic;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.agriculture.IFarmingManager;

public class FarmingManager implements IFarmingManager {
	private final Object2IntOpenHashMap<Item> fertilizers;
	private final ImmutableMap<Identifier, IFarmType> farmTypes;

	public FarmingManager(Object2IntOpenHashMap<Item> fertilizers, ImmutableMap<Identifier, IFarmType> farmTypes) {
		this.fertilizers = fertilizers;
		this.farmTypes = farmTypes;
	}

	@Override
	public int getFertilizeValue(ItemStack stack) {
		return this.fertilizers.getInt(stack.getItem());
	}

	@Nullable
	@Override
	public IFarmType getFarmType(Identifier id) {
		return this.farmTypes.get(id);
	}
}
