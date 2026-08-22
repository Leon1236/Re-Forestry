package com.leon1236.reforestry.api.core.genetics;

import java.util.List;

import net.minecraft.world.item.ItemStack;

public interface IFruitBearer {
	boolean hasFruit();

	List<ItemStack> pickFruit(ItemStack tool);

	float getRipeness();

	void addRipeness(float add);
}
