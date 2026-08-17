package com.leon1236.reforestry.storage;

import java.util.function.Predicate;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.storage.IBackpackDefinition;

public class BackpackDefinition implements IBackpackDefinition {
	private final int primaryColor;
	private final int secondaryColor;
	private final Predicate<ItemStack> filter;

	public BackpackDefinition(int primaryColor, int secondaryColor, Predicate<ItemStack> filter) {
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.filter = filter;
	}

	@Override
	public Predicate<ItemStack> getFilter() {
		return this.filter;
	}

	@Override
	public Component getName(ItemStack backpack) {
		return backpack.getHoverName();
	}

	@Override
	public int getPrimaryColour() {
		return this.primaryColor;
	}

	@Override
	public int getSecondaryColour() {
		return this.secondaryColor;
	}
}
