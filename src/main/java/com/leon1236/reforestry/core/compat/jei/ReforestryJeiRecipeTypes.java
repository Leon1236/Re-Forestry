package com.leon1236.reforestry.core.compat.jei;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.api.recipes.ICentrifugeRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.api.recipes.IStillRecipe;

import mezz.jei.api.recipe.types.IRecipeType;

public final class ReforestryJeiRecipeTypes {
	public static final IRecipeType<IFabricatorRecipe> FABRICATOR =
			IRecipeType.create(ReForestry.id("fabricator"), IFabricatorRecipe.class);
	public static final IRecipeType<ICarpenterRecipe> CARPENTER =
			IRecipeType.create(ReForestry.id("carpenter"), ICarpenterRecipe.class);
	public static final IRecipeType<ICentrifugeRecipe> CENTRIFUGE =
			IRecipeType.create(ReForestry.id("centrifuge"), ICentrifugeRecipe.class);
	public static final IRecipeType<IFermenterRecipe> FERMENTER =
			IRecipeType.create(ReForestry.id("fermenter"), IFermenterRecipe.class);
	public static final IRecipeType<IMoistenerRecipe> MOISTENER =
			IRecipeType.create(ReForestry.id("moistener"), IMoistenerRecipe.class);
	public static final IRecipeType<ISmelterRecipe> SMELTER =
			IRecipeType.create(ReForestry.id("smelter"), ISmelterRecipe.class);
	public static final IRecipeType<ISqueezerRecipe> SQUEEZER =
			IRecipeType.create(ReForestry.id("squeezer"), ISqueezerRecipe.class);
	public static final IRecipeType<IStillRecipe> STILL =
			IRecipeType.create(ReForestry.id("still"), IStillRecipe.class);

	private ReforestryJeiRecipeTypes() {
	}
}
