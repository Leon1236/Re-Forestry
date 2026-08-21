package com.leon1236.reforestry.farming.multiblock;

import java.util.ArrayDeque;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmInventory;

public interface IFarmInventoryInternal extends IFarmInventory {
	int getFertilizerValue();

	boolean useFertilizer();

	void stowProducts(Iterable<ItemStack> harvested, ArrayDeque<ItemStack> pendingProduce);

	boolean tryAddPendingProduce(ArrayDeque<ItemStack> pendingProduce);
}
