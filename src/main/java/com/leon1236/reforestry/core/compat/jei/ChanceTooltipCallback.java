package com.leon1236.reforestry.core.compat.jei;

import java.text.DecimalFormat;

import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public record ChanceTooltipCallback(float chance) implements mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback {
	private static final DecimalFormat FORMATTER = createFormatter();

	public ChanceTooltipCallback {
		if (chance < 0f) {
			chance = 0f;
		} else if (chance > 1f) {
			chance = 1f;
		}
	}

	@Override
	public void onRichTooltip(IRecipeSlotView recipeSlotView, ITooltipBuilder tooltip) {
		tooltip.add(Component.translatable("for.jei.chance", FORMATTER.format(this.chance * 100d))
				.withStyle(ChatFormatting.GRAY));
	}

	private static DecimalFormat createFormatter() {
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMinimumFractionDigits(0);
		formatter.setMaximumFractionDigits(3);
		return formatter;
	}
}
