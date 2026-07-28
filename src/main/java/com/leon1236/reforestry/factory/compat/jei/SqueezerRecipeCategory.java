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

import com.leon1236.reforestry.api.recipes.ISqueezerRecipe;
import com.leon1236.reforestry.core.compat.jei.ChanceTooltipCallback;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class SqueezerRecipeCategory<F> extends ForestryRecipeCategory<ISqueezerRecipe> {
	private final IDrawable icon;
	private final IPlatformFluidHelper<F> fluidHelper;
	private final IIngredientTypeWithSubtypes<Fluid, F> fluidType;

	public SqueezerRecipeCategory(IGuiHelper guiHelper, IPlatformFluidHelper<F> fluidHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.SQUEEZER, 12, 13, 152, 62), "block.reforestry.squeezer");
		this.fluidHelper = fluidHelper;
		this.fluidType = fluidHelper.getFluidIngredientType();
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SQUEEZER).block()));
	}

	@Override
	public IRecipeType<ISqueezerRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.SQUEEZER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ISqueezerRecipe recipe, IFocusGroup focuses) {
		int x = 20;
		for (var ingredient : recipe.getInputs()) {
			builder.addSlot(RecipeIngredientRole.INPUT, x, 22)
					.add(ingredient);
			x += 20;
		}

		long outputAmount = recipe.getOutputAmount();
		long capacity = Math.max(outputAmount, FluidUnits.mbToDroplets(1000));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 90, 22)
				.setFluidRenderer(capacity, false, 16, 16)
				.add(this.fluidType, this.fluidHelper.create(
						recipe.getOutputFluid().getFluid().defaultFluidState().typeHolder(),
						outputAmount));

		if (!recipe.getRemnants().isEmpty()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 22)
					.add(recipe.getRemnants())
					.addRichTooltipCallback(new ChanceTooltipCallback(recipe.getRemnantsChance()));
		}
	}
}
