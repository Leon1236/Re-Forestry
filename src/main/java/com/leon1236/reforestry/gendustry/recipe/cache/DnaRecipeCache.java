package com.leon1236.reforestry.gendustry.recipe.cache;

import java.util.IdentityHashMap;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.gendustry.features.GRecipeTypes;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;

public enum DnaRecipeCache implements IRecipeCache {
	INSTANCE;

	private IdentityHashMap<ILifeStage, DnaRecipe> recipes = new IdentityHashMap<>();

	@Nullable
	public DnaRecipe getRecipe(ILifeStage stage) {
		return this.recipes.get(stage);
	}

	@Override
	public void reload(RecipeManager recipes) {
		IdentityHashMap<ILifeStage, DnaRecipe> builder = new IdentityHashMap<>();
		for (RecipeHolder<DnaRecipe> holder : recipes.getAllOfType(GRecipeTypes.DNA.type())) {
			DnaRecipe recipe = holder.value();
			builder.put(recipe.getStage(), recipe);
		}
		this.recipes = builder;
	}

	@Override
	public void unload() {
		this.recipes = new IdentityHashMap<>();
	}
}
