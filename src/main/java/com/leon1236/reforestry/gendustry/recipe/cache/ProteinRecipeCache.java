package com.leon1236.reforestry.gendustry.recipe.cache;

import java.util.IdentityHashMap;
import java.util.List;

import com.google.common.collect.ImmutableList;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import com.leon1236.reforestry.gendustry.features.GRecipeTypes;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;

public enum ProteinRecipeCache implements IRecipeCache {
	INSTANCE;

	private IdentityHashMap<Item, ProteinRecipe> simple = new IdentityHashMap<>();
	private List<ProteinRecipe> complex = List.of();

	@Nullable
	public ProteinRecipe getRecipe(ItemStack stack) {
		ProteinRecipe recipe = this.simple.get(stack.getItem());
		if (recipe == null) {
			for (ProteinRecipe candidate : this.complex) {
				if (candidate.getIngredient().test(stack)) {
					return candidate;
				}
			}
		}
		return recipe;
	}

	@Override
	public void reload(RecipeManager recipes) {
		IdentityHashMap<Item, ProteinRecipe> simpleBuilder = new IdentityHashMap<>();
		ImmutableList.Builder<ProteinRecipe> complexBuilder = ImmutableList.builder();

		for (RecipeHolder<ProteinRecipe> holder : recipes.getAllOfType(GRecipeTypes.PROTEIN.type())) {
			ProteinRecipe recipe = holder.value();
			Ingredient ingredient = recipe.getIngredient();
			if (ingredient.requiresTesting()) {
				complexBuilder.add(recipe);
			} else {
				for (Holder<Item> item : ingredient.items().toList()) {
					simpleBuilder.put(item.value(), recipe);
				}
			}
		}

		this.simple = simpleBuilder;
		this.complex = complexBuilder.build();
	}

	@Override
	public void unload() {
		this.simple = new IdentityHashMap<>();
		this.complex = List.of();
	}
}
