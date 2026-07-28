package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.recipes.ICarpenterRecipe;
import com.leon1236.reforestry.api.recipes.ICentrifugeRecipe;
import com.leon1236.reforestry.api.recipes.IFabricatorRecipe;
import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.api.recipes.IStillRecipe;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeSources;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.ScreenCarpenter;
import com.leon1236.reforestry.factory.client.ScreenCentrifuge;
import com.leon1236.reforestry.factory.client.ScreenFabricator;
import com.leon1236.reforestry.factory.client.ScreenFermenter;
import com.leon1236.reforestry.factory.client.ScreenMoistener;
import com.leon1236.reforestry.factory.client.ScreenSmelter;
import com.leon1236.reforestry.factory.client.ScreenSqueezer;
import com.leon1236.reforestry.factory.client.ScreenStill;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

@JeiPlugin
public class FactoryJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("factory");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IPlatformFluidHelper<?> fluidHelper = jeiHelpers.getPlatformFluidHelper();
		registry.addRecipeCategories(
				new FabricatorRecipeCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new CarpenterRecipeCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new CentrifugeRecipeCategory(jeiHelpers.getGuiHelper()),
				new FermenterRecipeCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new MoistenerRecipeCategory(jeiHelpers.getGuiHelper()),
				new SmelterRecipeCategory(jeiHelpers.getGuiHelper()),
				new SqueezerRecipeCategory<>(jeiHelpers.getGuiHelper(), fluidHelper),
				new StillRecipeCategory<>(jeiHelpers.getGuiHelper(), fluidHelper)
		);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		registry.addRecipes(ReforestryJeiRecipeTypes.FABRICATOR, JeiRecipeSources.collect(IFabricatorRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.CARPENTER, JeiRecipeSources.collect(ICarpenterRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.CENTRIFUGE, JeiRecipeSources.collect(ICentrifugeRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.FERMENTER, JeiRecipeSources.collect(IFermenterRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.MOISTENER, JeiRecipeSources.collect(IMoistenerRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.SMELTER, JeiRecipeSources.collect(ISmelterRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.SQUEEZER, JeiRecipeSources.collect(ISqueezerRecipe.class));
		registry.addRecipes(ReforestryJeiRecipeTypes.STILL, JeiRecipeSources.collect(IStillRecipe.class));
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
		registry.addCraftingStation(ReforestryJeiRecipeTypes.CARPENTER, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CARPENTER).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.CENTRIFUGE, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CENTRIFUGE).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.FABRICATOR, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FABRICATOR).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.FERMENTER, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FERMENTER).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.MOISTENER, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.MOISTENER).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.SMELTER, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SMELTER).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.SQUEEZER, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SQUEEZER).block());
		registry.addCraftingStation(ReforestryJeiRecipeTypes.STILL, FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.STILL).block());
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
		registry.addRecipeTransferHandler(new CarpenterRecipeTransferHandler(), ReforestryJeiRecipeTypes.CARPENTER);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registry) {
		registry.addGenericGuiContainerHandler(ScreenForestry.class, new ForestryScreenJeiHandler());
		registry.addRecipeClickArea(ScreenCarpenter.class, 98, 48, 21, 26, ReforestryJeiRecipeTypes.CARPENTER);
		registry.addRecipeClickArea(ScreenCentrifuge.class, 38, 22, 38, 14, ReforestryJeiRecipeTypes.CENTRIFUGE);
		registry.addRecipeClickArea(ScreenCentrifuge.class, 38, 54, 38, 14, ReforestryJeiRecipeTypes.CENTRIFUGE);
		registry.addRecipeClickArea(ScreenFabricator.class, 121, 53, 18, 18, ReforestryJeiRecipeTypes.FABRICATOR);
		registry.addRecipeClickArea(ScreenFermenter.class, 72, 40, 32, 18, ReforestryJeiRecipeTypes.FERMENTER);
		registry.addRecipeClickArea(ScreenMoistener.class, 123, 35, 19, 21, ReforestryJeiRecipeTypes.MOISTENER);
		registry.addRecipeClickArea(ScreenSmelter.class, 95, 57, 16, 16, ReforestryJeiRecipeTypes.SMELTER);
		registry.addRecipeClickArea(ScreenSqueezer.class, 76, 41, 43, 16, ReforestryJeiRecipeTypes.SQUEEZER);
		registry.addRecipeClickArea(ScreenStill.class, 73, 17, 33, 57, ReforestryJeiRecipeTypes.STILL);
	}
}
