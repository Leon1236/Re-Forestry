package com.leon1236.reforestry.factory.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.types.IRecipeType;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.fuels.RainSubstrate;
import com.leon1236.reforestry.core.compat.jei.ForestryRecipeCategory;
import com.leon1236.reforestry.core.compat.jei.ReforestryJeiRecipeTypes;
import com.leon1236.reforestry.factory.blocks.BlockTypeFactoryTesr;
import com.leon1236.reforestry.factory.features.FactoryBlocks;

public class RainmakerRecipeCategory extends ForestryRecipeCategory<RainSubstrate> {
	private static final int DARK_GRAY = 0xFF404040;
	private static final int GRAY = 0xFF808080;

	private final IDrawable slot;
	private final IDrawable icon;

	public RainmakerRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createBlankDrawable(150, 30), "block.reforestry.rainmaker");
		this.slot = guiHelper.getSlotDrawable();
		this.icon = guiHelper.createDrawableIngredient(
				VanillaTypes.ITEM_STACK,
				new ItemStack(FactoryBlocks.TESR.get(BlockTypeFactoryTesr.RAINMAKER).block()));
	}

	@Override
	public IRecipeType<RainSubstrate> getRecipeType() {
		return ReforestryJeiRecipeTypes.RAINMAKER;
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, RainSubstrate recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
				.setBackground(this.slot, -1, -1)
				.add(recipe.item());
	}

	@Override
	public void draw(RainSubstrate recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor graphics, double mouseX, double mouseY) {
		super.draw(recipe, recipeSlotsView, graphics, mouseX, mouseY);
		Component effect = getEffectString(recipe);
		Component speed = Component.translatable("for.jei.rainmaker.speed", recipe.speed());

		Font font = Minecraft.getInstance().font;
		graphics.text(font, effect, 24, 0, DARK_GRAY, false);
		graphics.text(font, speed, 24, 10, GRAY, false);
		if (!recipe.reverse()) {
			Component duration = Component.translatable("for.jei.rainmaker.duration", recipe.duration());
			graphics.text(font, duration, 24, 20, GRAY, false);
		}
	}

	private static Component getEffectString(RainSubstrate recipe) {
		if (recipe.reverse()) {
			return Component.translatable("for.jei.rainmaker.stops.rain");
		}
		return Component.translatable("for.jei.rainmaker.causes.rain");
	}
}
