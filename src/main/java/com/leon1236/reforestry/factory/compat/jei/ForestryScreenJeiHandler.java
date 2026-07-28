package com.leon1236.reforestry.factory.compat.jei;

import java.util.List;
import java.util.Optional;

import mezz.jei.api.gui.builder.IClickableIngredientFactory;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.runtime.IClickableIngredient;

import net.minecraft.client.renderer.Rect2i;

import com.leon1236.reforestry.core.client.GuiRecipeLedger;
import com.leon1236.reforestry.core.client.ScreenForestry;

public class ForestryScreenJeiHandler implements IGuiContainerHandler<ScreenForestry<?>> {
	@Override
	public List<Rect2i> getGuiExtraAreas(ScreenForestry<?> screen) {
		return screen.getRecipeLedgerAreas();
	}

	@Override
	public Optional<? extends IClickableIngredient<?>> getClickableIngredientUnderMouse(
			IClickableIngredientFactory builder,
			ScreenForestry<?> screen,
			double mouseX,
			double mouseY
	) {
		GuiRecipeLedger.HoveredIngredient hovered = screen.getRecipeLedgerIngredient(mouseX, mouseY);
		if (hovered == null || hovered.stack().isEmpty()) {
			return Optional.empty();
		}
		return builder.createBuilder(hovered.stack())
				.buildWithArea(hovered.area())
				.map(ingredient -> ingredient);
	}
}
