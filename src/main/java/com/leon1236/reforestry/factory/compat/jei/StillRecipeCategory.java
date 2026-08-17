package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
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

import com.leon1236.reforestry.api.recipes.IStillRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;
import com.leon1236.reforestry.factory.tiles.TileStill;

public class StillRecipeCategory<F> extends ForestryRecipeCategory<IStillRecipe> {
	private final IDrawable tankOverlay;
	private final IDrawable icon;
	private final IDrawableAnimated progressBar;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public StillRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.STILL, 34, 14, 108, 60), "block.reforestry.still");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.tankOverlay = guiHelper.createDrawable(FactoryGuiTextures.STILL, 176, 0, 16, 58);
		IDrawableStatic progressBarDrawable = guiHelper.createDrawable(FactoryGuiTextures.STILL, 176, 74, 4, 18);
		this.progressBar = guiHelper.createAnimatedDrawable(progressBarDrawable, 20, IDrawableAnimated.StartDirection.BOTTOM, false);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.STILL).block()));
	}

	@Override
	public IRecipeType<IStillRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.STILL;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IStillRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.setFluidRenderer(TileStill.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getInputFluid().getFluid().defaultFluidState().typeHolder(),
						recipe.getInputAmount()));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 1)
				.setFluidRenderer(TileStill.TANK_CAPACITY, false, 16, 58)
				.setOverlay(this.tankOverlay, 0, 0)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getOutputFluid().getFluid().defaultFluidState().typeHolder(),
						recipe.getOutputAmount()));
	}

	@Override
	public void draw(IStillRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.progressBar.draw(graphics, 50, 3);
	}
}
