package com.leon1236.reforestry.factory.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.factory.recipes.CarpenterRecipe;
import com.leon1236.reforestry.factory.recipes.CentrifugeRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorRecipe;
import com.leon1236.reforestry.factory.recipes.FabricatorSmeltingRecipe;
import com.leon1236.reforestry.factory.recipes.FermenterRecipe;
import com.leon1236.reforestry.factory.recipes.HygroregulatorRecipe;
import com.leon1236.reforestry.factory.recipes.MoistenerRecipe;
import com.leon1236.reforestry.factory.recipes.SmelterRecipe;
import com.leon1236.reforestry.factory.recipes.SqueezerContainerRecipe;
import com.leon1236.reforestry.factory.recipes.SqueezerRecipe;
import com.leon1236.reforestry.factory.recipes.StillRecipe;
import com.leon1236.reforestry.modules.features.FeatureRecipeType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FactoryRecipeTypes {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("factory"));

    public static final FeatureRecipeType<CentrifugeRecipe> CENTRIFUGE =
            REGISTRY.recipeType("centrifuge", () -> CentrifugeRecipe.SERIALIZER);

    public static final FeatureRecipeType<SmelterRecipe> SMELTER =
            REGISTRY.recipeType("smelter", () -> SmelterRecipe.SERIALIZER);

    public static final FeatureRecipeType<StillRecipe> STILL =
            REGISTRY.recipeType("still", () -> StillRecipe.SERIALIZER);

    public static final FeatureRecipeType<SqueezerRecipe> SQUEEZER =
            REGISTRY.recipeType("squeezer", () -> SqueezerRecipe.SERIALIZER);

    public static final FeatureRecipeType<SqueezerContainerRecipe> SQUEEZER_CONTAINER =
            REGISTRY.recipeType("squeezer_container", () -> SqueezerContainerRecipe.SERIALIZER);

    public static final FeatureRecipeType<CarpenterRecipe> CARPENTER =
            REGISTRY.recipeType("carpenter", () -> CarpenterRecipe.SERIALIZER);

    public static final FeatureRecipeType<FermenterRecipe> FERMENTER =
            REGISTRY.recipeType("fermenter", () -> FermenterRecipe.SERIALIZER);

    public static final FeatureRecipeType<FabricatorSmeltingRecipe> FABRICATOR_SMELTING =
            REGISTRY.recipeType("fabricator_smelting", () -> FabricatorSmeltingRecipe.SERIALIZER);

    public static final FeatureRecipeType<FabricatorRecipe> FABRICATOR =
            REGISTRY.recipeType("fabricator", () -> FabricatorRecipe.SERIALIZER);

    public static final FeatureRecipeType<MoistenerRecipe> MOISTENER =
            REGISTRY.recipeType("moistener", () -> MoistenerRecipe.SERIALIZER);

    public static final FeatureRecipeType<HygroregulatorRecipe> HYGROREGULATOR =
            REGISTRY.recipeType("hygroregulator", () -> HygroregulatorRecipe.SERIALIZER);

    public static void init() {
    }
}
