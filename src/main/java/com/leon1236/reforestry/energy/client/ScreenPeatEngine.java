package com.leon1236.reforestry.energy.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.energy.gui.ContainerPeatEngine;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;

@Environment(EnvType.CLIENT)
public class ScreenPeatEngine extends EngineScreen<ContainerPeatEngine> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/peatengine.png");

	public ScreenPeatEngine(ContainerPeatEngine menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		if (menu.isBurning()) {
			int progress = menu.getBurnTimeRemainingScaled(12);
			int height = progress + 2;
			if (height > 0) {
				graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
						leftPos + 45, topPos + 27 + 12 - progress,
						176.0f, 12 - progress,
						14, height, 256, 256);
			}
		}

		GuiErrorTabs.draw(graphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				EngineBlockEntity.ERROR_SLOT_COUNT, mouseX, mouseY);
	}
}
