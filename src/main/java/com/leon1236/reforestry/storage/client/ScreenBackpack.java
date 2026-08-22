package com.leon1236.reforestry.storage.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.storage.gui.ContainerBackpack;

public class ScreenBackpack extends AbstractContainerScreen<ContainerBackpack> {
	private static final Identifier TEXTURE_DEFAULT = ReForestry.id("textures/gui/backpack.png");
	private static final Identifier TEXTURE_T2 = ReForestry.id("textures/gui/backpack_t2.png");

	public ScreenBackpack(ContainerBackpack menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 176, menu.getSize() == ContainerBackpack.Size.T2 ? 192 : 166);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		Identifier texture = this.menu.getSize() == ContainerBackpack.Size.T2 ? TEXTURE_T2 : TEXTURE_DEFAULT;
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		if (this.menu.getSize() != ContainerBackpack.Size.T2) {
			graphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, -12566464, false);
		}
		graphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -12566464, false);
	}
}
