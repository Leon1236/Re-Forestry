package com.leon1236.reforestry.api.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IProduct {
	Item item();

	default int count() {
		return 1;
	}

	float chance();

	default ItemStack createStack() {
		return new ItemStack(item(), count());
	}
}
