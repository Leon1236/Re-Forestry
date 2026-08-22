package com.leon1236.reforestry.core.compat.jei;

import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.apiculture.compat.jei.MutationDisplay;
import com.leon1236.reforestry.apiculture.compat.jei.ProductRecipe;

public final class GeneticsJeiRecipeTypes {
	public static final IRecipeType<ProductRecipe> TREE_PRODUCTS =
			products(ForestrySpeciesTypes.TREE);
	public static final IRecipeType<MutationDisplay> TREE_MUTATIONS =
			mutations(ForestrySpeciesTypes.TREE);
	public static final IRecipeType<ProductRecipe> BUTTERFLY_PRODUCTS =
			products(ForestrySpeciesTypes.BUTTERFLY);
	public static final IRecipeType<MutationDisplay> BUTTERFLY_MUTATIONS =
			mutations(ForestrySpeciesTypes.BUTTERFLY);

	private GeneticsJeiRecipeTypes() {
	}

	public static IRecipeType<ProductRecipe> products(Identifier speciesTypeId) {
		return IRecipeType.create(withSuffix(speciesTypeId, "_products"), ProductRecipe.class);
	}

	public static IRecipeType<MutationDisplay> mutations(Identifier speciesTypeId) {
		return IRecipeType.create(withSuffix(speciesTypeId, "_mutations"), MutationDisplay.class);
	}

	private static Identifier withSuffix(Identifier id, String suffix) {
		return Identifier.fromNamespaceAndPath(id.getNamespace(), id.getPath() + suffix);
	}
}
