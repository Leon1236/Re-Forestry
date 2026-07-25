package com.leon1236.reforestry.factory.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerSmelter;
import com.leon1236.reforestry.factory.tiles.TileSmelter;

public class ScreenSmelter extends ScreenForestry<ContainerSmelter> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	public ScreenSmelter(ContainerSmelter menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.SMELTER, leftPos, topPos, imageWidth, imageHeight);

		int progress = menu.getProgressPercent() * 50 / 100;
		FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.SMELTER,
				leftPos + 81, topPos + 39, 176, 52, progress, 16);

		if (menu.getErrorCount() <= 0) {
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.SMELTER,
					leftPos + 96, topPos + 58, 176, 68, 14, 14);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileSmelter.ERROR_SLOT_COUNT, mouseX, mouseY);
	}
}
