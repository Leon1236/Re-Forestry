package com.leon1236.reforestry.apiculture.compat.jei;

import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;

import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.resources.Identifier;

public final class ApicultureJeiRecipeTypes {
	public static final IRecipeType<ProductRecipe> BEE_PRODUCTS =
			IRecipeType.create(withSuffix(ForestrySpeciesTypes.BEE, "_products"), ProductRecipe.class);
	public static final IRecipeType<MutationDisplay> BEE_MUTATIONS =
			IRecipeType.create(withSuffix(ForestrySpeciesTypes.BEE, "_mutations"), MutationDisplay.class);

	private ApicultureJeiRecipeTypes() {
	}

	private static Identifier withSuffix(Identifier id, String suffix) {
		return Identifier.fromNamespaceAndPath(id.getNamespace(), id.getPath() + suffix);
	}
}
