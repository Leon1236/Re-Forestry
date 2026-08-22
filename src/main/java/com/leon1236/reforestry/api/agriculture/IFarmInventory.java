package com.leon1236.reforestry.api.agriculture;

import java.util.List;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

public interface IFarmInventory {
	boolean hasResources(List<ItemStack> resources);

	void removeResources(List<ItemStack> resources);

	boolean acceptsAsSeedling(ItemStack stack);

	boolean acceptsAsResource(ItemStack stack);

	boolean acceptsAsFertilizer(ItemStack stack);

	Container getProductInventory();

	Container getGermlingsInventory();

	Container getResourcesInventory();

	Container getFertilizerInventory();
}
