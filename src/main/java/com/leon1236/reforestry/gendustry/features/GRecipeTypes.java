package com.leon1236.reforestry.gendustry.features;

import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;
import com.leon1236.reforestry.gendustry.recipe.GeneticTemplateRecipe;
import com.leon1236.reforestry.gendustry.recipe.MutagenRecipe;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;
import com.leon1236.reforestry.modules.features.FeatureRecipeType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class GRecipeTypes {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("gendustry"));

	public static final FeatureRecipeType<MutagenRecipe> MUTAGEN =
			REGISTRY.recipeType("mutagen", () -> MutagenRecipe.SERIALIZER);

	public static final FeatureRecipeType<ProteinRecipe> PROTEIN =
			REGISTRY.recipeType("protein", () -> ProteinRecipe.SERIALIZER);

	public static final FeatureRecipeType<DnaRecipe> DNA =
			REGISTRY.recipeType("dna", () -> DnaRecipe.SERIALIZER);

	public static final RecipeSerializer<GeneticTemplateRecipe> GENETIC_TEMPLATE = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER,
			ReForestry.id("genetic_template"),
			GeneticTemplateRecipe.SERIALIZER);

	public static void init() {
		RecipeSynchronization.synchronizeRecipeSerializer(MUTAGEN.serializer());
		RecipeSynchronization.synchronizeRecipeSerializer(PROTEIN.serializer());
		RecipeSynchronization.synchronizeRecipeSerializer(DNA.serializer());
		RecipeSynchronization.synchronizeRecipeSerializer(GENETIC_TEMPLATE);
	}
}
