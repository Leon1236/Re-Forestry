package com.leon1236.reforestry.core.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.category.IRecipeCategory;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public abstract class ForestryRecipeCategory<T> implements IRecipeCategory<T> {
	private final IDrawable background;
	private final Component title;
	private final int width;
	private final int height;

	protected ForestryRecipeCategory(IDrawable background, String translationKey) {
		this.background = background;
		this.title = Component.translatable(translationKey);
		this.width = background.getWidth();
		this.height = background.getHeight();
	}

	@Override
	public Component getTitle() {
		return this.title;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void draw(T recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		this.background.draw(graphics, 0, 0);
	}

	@Override
	public abstract void setRecipe(IRecipeLayoutBuilder builder, T recipe, IFocusGroup focuses);
}
