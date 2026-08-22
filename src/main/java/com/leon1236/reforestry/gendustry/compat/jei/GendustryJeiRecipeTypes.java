package com.leon1236.reforestry.gendustry.compat.jei;

import mezz.jei.api.recipe.types.IRecipeType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.gendustry.recipe.DnaRecipe;
import com.leon1236.reforestry.gendustry.recipe.MutagenRecipe;
import com.leon1236.reforestry.gendustry.recipe.ProteinRecipe;

public final class GendustryJeiRecipeTypes {
	public static final IRecipeType<MutagenRecipe> MUTAGEN_PRODUCER =
			IRecipeType.create(ReForestry.id("mutagen_producer"), MutagenRecipe.class);
	public static final IRecipeType<DnaRecipe> DNA_EXTRACTOR =
			IRecipeType.create(ReForestry.id("dna_extractor"), DnaRecipe.class);
	public static final IRecipeType<ProteinRecipe> PROTEIN_LIQUEFIER =
			IRecipeType.create(ReForestry.id("protein_liquefier"), ProteinRecipe.class);

	private GendustryJeiRecipeTypes() {
	}
}
