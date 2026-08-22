package com.leon1236.reforestry.lepidopterology.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.lepidopterology.recipe.ButterflyMatingRecipe;

public class LepidopterologyRecipes {
	public static final RecipeSerializer<ButterflyMatingRecipe> MATING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			ReForestry.id("butterfly_mating"),
			ButterflyMatingRecipe.SERIALIZER);

	public static void init() {
	}
}
