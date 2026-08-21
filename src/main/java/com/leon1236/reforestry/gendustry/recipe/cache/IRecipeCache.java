package com.leon1236.reforestry.gendustry.recipe.cache;

import net.minecraft.world.item.crafting.RecipeManager;

public interface IRecipeCache {
	void reload(RecipeManager recipes);

	void unload();
}
