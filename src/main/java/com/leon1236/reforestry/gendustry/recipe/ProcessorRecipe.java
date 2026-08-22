package com.leon1236.reforestry.gendustry.recipe;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.recipes.IForestryRecipe;

public abstract class ProcessorRecipe implements IForestryRecipe {
	protected final int amount;

	protected ProcessorRecipe(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}

	public abstract boolean isIngredient(ItemStack stack);
}
