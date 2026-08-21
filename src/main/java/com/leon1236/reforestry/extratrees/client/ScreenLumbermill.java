package com.leon1236.reforestry.extratrees.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.extratrees.gui.ContainerLumbermill;
import com.leon1236.reforestry.extratrees.tiles.TileLumbermill;

public class ScreenLumbermill extends ScreenForestry<ContainerLumbermill> {
	private static final int IMAGE_WIDTH = 220;
	private static final int IMAGE_HEIGHT = 192;
	private static final int TANK_X = 16;
	private static final int TANK_Y = 32;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int WATER_COLOR = 0xFF3F76E4;

	public ScreenLumbermill(ContainerLumbermill menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("lumbermill");
		addTankClickRegion(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		ExtraTreesGuiTextures.blitBackground(guiGraphics, ExtraTreesGuiTextures.LUMBERMILL, leftPos, topPos, imageWidth, imageHeight);

		int amountMb = menu.getWaterAmountMb();
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int x = leftPos + TANK_X;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, WATER_COLOR);
		}

		int progress = menu.getProgressPercent();
		if (progress > 0) {
			int width = progress * 72 / 100;
			ExtraTreesGuiTextures.blitProgress(guiGraphics, ExtraTreesGuiTextures.LUMBERMILL,
					leftPos + 71, topPos + 43, 0, 192, width, 18);
		}

		int tankLeft = leftPos + TANK_X;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = new ArrayList<>();
			lines.add(Component.translatable("block.minecraft.water"));
			lines.add(Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileLumbermill.ERROR_SLOT_COUNT, mouseX, mouseY);
	}
}
