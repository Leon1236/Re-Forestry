package com.leon1236.reforestry.extra_bees.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extra_bees.gui.ContainerAlvearyStimulator;

public class ScreenAlvearyStimulator extends AbstractContainerScreen<ContainerAlvearyStimulator> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/alveary_stimulator.png");
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 144;
	private static final int BAR_X = 75;
	private static final int BAR_Y = 29;
	private static final int BAR_WIDTH = 60;
	private static final int BAR_HEIGHT = 16;
	private static final int BAR_COLOR = 0xFFE8A400;

	public ScreenAlvearyStimulator(ContainerAlvearyStimulator menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		int capacity = Math.max(1, menu.getEnergyCapacity());
		int filled = BAR_WIDTH * menu.getEnergyStored() / capacity;
		if (filled > 0) {
			int x = leftPos + BAR_X;
			int y = topPos + BAR_Y;
			guiGraphics.fill(x, y, x + filled, y + BAR_HEIGHT, BAR_COLOR);
		}

		int barLeft = leftPos + BAR_X;
		int barTop = topPos + BAR_Y;
		if (mouseX >= barLeft && mouseX < barLeft + BAR_WIDTH
				&& mouseY >= barTop && mouseY < barTop + BAR_HEIGHT) {
			guiGraphics.setTooltipForNextFrame(font,
					List.of(Component.translatable("gui.reforestry.energy", menu.getEnergyStored(), menu.getEnergyCapacity()),
							Component.translatable("gui.reforestry.alveary_stimulator.usage", menu.getPowerUsage())),
					Optional.empty(), mouseX, mouseY);
		}
	}
}
