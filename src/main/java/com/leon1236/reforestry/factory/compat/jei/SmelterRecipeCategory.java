package com.leon1236.reforestry.factory.compat.jei;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.recipes.ISmelterRecipe;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.core.recipes.CraftingPatternHelper;
import com.leon1236.reforestry.core.recipes.IngredientStack;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class SmelterRecipeCategory extends ForestryRecipeCategory<ISmelterRecipe> {
	private final IDrawableAnimated arrow;
	private final IDrawable fire;
	private final IDrawable icon;

	public SmelterRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.SMELTER, 9, 16, 158, 62), "block.reforestry.smelter");
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(FactoryGuiTextures.SMELTER, 176, 52, 50, 16);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 720, IDrawableAnimated.StartDirection.LEFT, false);
		this.fire = guiHelper.createDrawable(FactoryGuiTextures.SMELTER, 176, 68, 14, 14);
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
		int cellX = 0;
		int cellY = 0;
		int originX = 12;
		int originY = 5;

		for (IngredientStack input : recipe.getInputs()) {
			List<ItemStack> stacks = new ArrayList<>();
			for (ItemStack stack : CraftingPatternHelper.stacksFromIngredient(input.ingredient())) {
				stacks.add(stack.copyWithCount(input.count()));
			}
			builder.addSlot(RecipeIngredientRole.INPUT, originX + cellX, originY + cellY)
					.addItemStacks(stacks);

			cellX += 18;
			if (cellX >= 54) {
				cellX = 0;
				cellY += 18;
			}
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 130, 23)
				.add(recipe.getOutput());
	}

	@Override
	public void draw(ISmelterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		this.arrow.draw(graphics, 72, 23);
		this.fire.draw(graphics, 87, 42);
	}
}
