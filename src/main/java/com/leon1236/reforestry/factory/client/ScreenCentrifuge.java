package com.leon1236.reforestry.factory.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerCentrifuge;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;

public class ScreenCentrifuge extends ScreenForestry<ContainerCentrifuge> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	public ScreenCentrifuge(ContainerCentrifuge menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.CENTRIFUGE, leftPos, topPos, imageWidth, imageHeight);

		int progress = menu.getProgressPercent() * 16 / 100;
		FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.CENTRIFUGE,
				leftPos + 43, topPos + 36 + 17 - progress, 176, 17 - progress, 4, progress);
		FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.CENTRIFUGE,
				leftPos + 67, topPos + 36 + 17 - progress, 176, 17 - progress, 4, progress);

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileCentrifuge.ERROR_SLOT_COUNT, mouseX, mouseY);
	}
}
