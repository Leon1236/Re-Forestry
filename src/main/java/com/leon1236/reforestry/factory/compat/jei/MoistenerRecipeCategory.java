package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.recipes.IMoistenerRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class MoistenerRecipeCategory extends ForestryRecipeCategory<IMoistenerRecipe> {
	private final IDrawable icon;

	public MoistenerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.MOISTENER, 12, 13, 152, 62), "block.reforestry.moistener");
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.MOISTENER).block()));
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
		builder.addSlot(RecipeIngredientRole.INPUT, 44, 22)
				.add(recipe.getInput());
		builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 22)
				.add(recipe.getProduct());
	}
}
