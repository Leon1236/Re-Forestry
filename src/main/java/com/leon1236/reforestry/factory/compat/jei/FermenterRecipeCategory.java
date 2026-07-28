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

import com.leon1236.reforestry.api.recipes.IFermenterRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class FermenterRecipeCategory<F> extends ForestryRecipeCategory<IFermenterRecipe> {
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public FermenterRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.FERMENTER, 12, 13, 152, 62), "block.reforestry.fermenter");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.FERMENTER).block()));
	}

	@Override
	public IRecipeType<IFermenterRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.FERMENTER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IFermenterRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 44, 22)
				.add(recipe.getInputItem());

		long inputAmount = recipe.getInputFluidAmount();
		long capacity = Math.max(inputAmount, FluidUnits.mbToDroplets(1000));
		builder.addSlot(RecipeIngredientRole.INPUT, 70, 22)
				.setFluidRenderer(capacity, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getInputFluid().getFluid().defaultFluidState().typeHolder(),
						inputAmount));

		long outputAmount = FluidUnits.mbToDroplets(1000);
		builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 22)
				.setFluidRenderer(outputAmount, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getOutput().defaultFluidState().typeHolder(),
						outputAmount));
	}
}
