package com.leon1236.reforestry.gendustry.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.gendustry.blockentity.SamplerBlockEntity;
import com.leon1236.reforestry.gendustry.menu.ThreeInputMenu;

public class ScreenThreeInput extends ScreenForestry<ThreeInputMenu> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	public ScreenThreeInput(ThreeInputMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey(menu.getHintsKey());
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		GendustryGuiTextures.blitBackground(guiGraphics, GendustryGuiTextures.SAMPLER, leftPos, topPos, imageWidth, imageHeight);

		int progress = menu.getProgressPercent() * 68 / 100;
		GendustryGuiTextures.blitProgress(guiGraphics, GendustryGuiTextures.SAMPLER,
				leftPos + 53, topPos + 48, 176, 0, progress, 18);

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				SamplerBlockEntity.ERROR_SLOT_COUNT, mouseX, mouseY);
	}
}
