package com.leon1236.reforestry.api.agriculture;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.item.Item;

public interface IFarmableFactory {
	IFarmable create(Item germling, ImmutableSet<Item> windfall);
}
