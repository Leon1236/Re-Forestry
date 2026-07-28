package com.leon1236.reforestry.core.compat.jei;

import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public record ChanceTooltipCallback(float chance) implements mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback {
	@Override
	public void onRichTooltip(IRecipeSlotView recipeSlotView, ITooltipBuilder tooltip) {
		tooltip.add(Component.translatable("for.jei.chance", formatPercentage(this.chance)).withStyle(ChatFormatting.GRAY));
	}

	private static String formatPercentage(float chance) {
		return String.valueOf(Math.round(chance * 1000f) / 10f);
	}
}
