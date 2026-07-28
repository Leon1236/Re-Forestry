package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.recipes.IngredientStack;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class SmelterRecipeCategory extends ForestryRecipeCategory<ISmelterRecipe> {
	private final IDrawable icon;

	public SmelterRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.SMELTER, 12, 13, 152, 62), "block.reforestry.smelter");
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.SMELTER).block()));
	}

	@Override
	public IRecipeType<ISmelterRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.SMELTER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ISmelterRecipe recipe, IFocusGroup focuses) {
		int x = 20;
		for (IngredientStack input : recipe.getInputs()) {
			builder.addSlot(RecipeIngredientRole.INPUT, x, 22)
					.add(input.ingredient());
			x += 20;
		}
		builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 22)
				.add(recipe.getOutput());
	}
}
