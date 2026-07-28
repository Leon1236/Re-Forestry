package com.leon1236.reforestry.factory.compat.jei;

import java.util.Comparator;
import java.util.List;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
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

import com.leon1236.reforestry.api.core.Product;
import com.leon1236.reforestry.api.recipes.ICentrifugeRecipe;
import com.leon1236.reforestry.core.compat.jei.ChanceTooltipCallback;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.JeiRecipeLayoutHelper;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryPlain;
import com.leon1236.reforestry.factory.client.FactoryGuiTextures;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class CentrifugeRecipeCategory extends ForestryRecipeCategory<ICentrifugeRecipe> {
	private final IDrawableAnimated arrow;
	private final IDrawable icon;

	public CentrifugeRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(FactoryGuiTextures.CENTRIFUGE, 11, 18, 154, 54), "block.reforestry.centrifuge");
		IDrawableStatic arrowDrawable = guiHelper.createDrawable(FactoryGuiTextures.CENTRIFUGE, 176, 0, 4, 17);
		this.arrow = guiHelper.createAnimatedDrawable(arrowDrawable, 80, IDrawableAnimated.StartDirection.BOTTOM, false);
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.PLAIN.get(BlockTypeFactoryPlain.CENTRIFUGE).block()));
	}

	@Override
	public IRecipeType<ICentrifugeRecipe> getRecipeType() {
		return ReforestryJeiRecipeTypes.CENTRIFUGE;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ICentrifugeRecipe recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 5, 19)
				.add(recipe.getInput());

		List<IRecipeSlotBuilder> outputSlots = JeiRecipeLayoutHelper.layoutSlotGrid(
				builder, RecipeIngredientRole.OUTPUT, 3, 3, 101, 1, 18);
		List<Product> sortedProducts = recipe.getAllProducts().stream()
				.sorted(Comparator.comparing(Product::chance).reversed())
				.toList();
		for (int i = 0; i < sortedProducts.size() && i < outputSlots.size(); i++) {
			Product product = sortedProducts.get(i);
			outputSlots.get(i)
					.add(product.createStack())
					.addRichTooltipCallback(new ChanceTooltipCallback(product.chance()));
		}
	}

	@Override
	public void draw(ICentrifugeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		this.arrow.draw(graphics, 32, 18);
		this.arrow.draw(graphics, 56, 18);
	}
}
