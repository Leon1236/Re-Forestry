package com.leon1236.reforestry.core.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class ScreenForestry<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	private static final int TITLE_COLOR = 0xFF404040;

	protected ScreenForestry(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
		super(menu, inventory, title, imageWidth, imageHeight);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int titleWidth = this.font.width(this.title);
		graphics.text(this.font, this.title, (this.imageWidth - titleWidth) / 2, this.titleLabelY, TITLE_COLOR, false);
	}
}
