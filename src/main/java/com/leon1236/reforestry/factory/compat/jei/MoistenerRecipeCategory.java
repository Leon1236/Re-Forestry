package com.leon1236.reforestry.factory.compat.jei;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.fuels.MoistenerFuel;
import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.tiles.TileMoistener;

public class MoistenerRecipeCategory<F> extends ForestryRecipeCategory<IMoistenerRecipe> {
	private final IDrawableAnimated arrow;
	private final IDrawableAnimated progressBar;
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final List<ItemStack> fuelResources;
	private final List<ItemStack> fuelProducts;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public MoistenerRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.MOISTENER, 15, 15, 145, 60), "block.reforestry.moistener");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(FactoryGuiTextures.MOISTENER, 176, 91, 29, 55);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
		IDrawableStatic progressBarDrawable = guiHelper.createDrawable(FactoryGuiTextures.MOISTENER, 176, 74, 16, 15);
		this.progressBar = guiHelper.createAnimatedDrawable(progressBarDrawable, 160, IDrawableAnimated.StartDirection.LEFT, false);
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.MOISTENER, 176, 0, 16, 58);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.MOISTENER).block()));

		Collection<MoistenerFuel> fuels = moistenerFuels();
		this.fuelResources = fuels.stream().map(MoistenerFuel::resource).map(ItemStack::copy).toList();
		this.fuelProducts = fuels.stream().map(MoistenerFuel::product).map(ItemStack::copy).toList();
	}

	@Override
	public IRecipeType<IMoistenerRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.MOISTENER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IMoistenerRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 128, 4)
				.add(recipe.getInput());

		IRecipeSlotBuilder fuelResourceSlot = builder.addSlot(RecipeIngredientRole.INPUT, 24, 43)
				.addItemStacks(this.fuelResources);

		builder.addSlot(RecipeIngredientRole.OUTPUT, 128, 40)
				.add(recipe.getProduct());

		IRecipeSlotBuilder fuelProductsSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 22)
				.addItemStacks(this.fuelProducts);

		long waterAmount = FluidUnits.mbToDroplets(Math.max(1, recipe.getTimePerItem() / 4));
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.setFluidRenderer(TileMoistener.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						Fluids.WATER.defaultFluidState().typeHolder(),
						waterAmount));

		builder.createFocusLink(fuelResourceSlot, fuelProductsSlot);
	}

	@Override
	public void draw(IMoistenerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.arrow.draw(graphics, 78, 2);
		this.progressBar.draw(graphics, 109, 22);
	}

	private static Collection<MoistenerFuel> moistenerFuels() {
		Map<ItemStack, MoistenerFuel> fuels = FuelManager.moistenerResource;
		return fuels == null ? List.of() : fuels.values();
	}
}
