package com.leon1236.reforestry.farming.multiblock;

import java.util.List;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.agriculture.IFarmInventory;

public enum FakeFarmInventory implements IFarmInventory {
	INSTANCE;

	private static final Container EMPTY = new SimpleContainer(0);

	@Override
	public boolean hasResources(List<ItemStack> resources) {
		return false;
	}

	@Override
	public void removeResources(List<ItemStack> resources) {
	}

	@Override
	public boolean acceptsAsSeedling(ItemStack stack) {
		return false;
	}

	@Override
	public boolean acceptsAsResource(ItemStack stack) {
		return false;
	}

	@Override
	public boolean acceptsAsFertilizer(ItemStack stack) {
		return false;
	}

	@Override
	public Container getProductInventory() {
		return EMPTY;
	}

	@Override
	public Container getGermlingsInventory() {
		return EMPTY;
	}

	@Override
	public Container getResourcesInventory() {
		return EMPTY;
	}

	@Override
	public Container getFertilizerInventory() {
		return EMPTY;
	}
}
