package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.api.recipes.IStillRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class StillRecipeCategory<F> extends ForestryRecipeCategory<IStillRecipe> {
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public StillRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.STILL, 12, 13, 152, 62), "block.reforestry.still");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
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
		long inputAmount = recipe.getInputAmount();
		long outputAmount = recipe.getOutputAmount();
		long inputCapacity = Math.max(inputAmount, FluidUnits.mbToDroplets(1000));
		long outputCapacity = Math.max(outputAmount, FluidUnits.mbToDroplets(1000));

		builder.addSlot(RecipeIngredientRole.INPUT, 44, 22)
				.setFluidRenderer(inputCapacity, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getInputFluid().getFluid().defaultFluidState().typeHolder(),
						inputAmount));

		builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 22)
				.setFluidRenderer(outputCapacity, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getOutputFluid().getFluid().defaultFluidState().typeHolder(),
						outputAmount));
	}
}
