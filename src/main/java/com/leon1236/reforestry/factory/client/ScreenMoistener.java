package com.leon1236.reforestry.factory.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerMoistener;
import com.leon1236.reforestry.factory.tiles.TileMoistener;

public class ScreenMoistener extends ScreenForestry<ContainerMoistener> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	private static final int TANK_X = 16;
	private static final int TANK_Y = 16;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int WATER_COLOR = 0xFF3F76E4;


	public ScreenMoistener(ContainerMoistener menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.MOISTENER, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY);

		int production = menu.getProductionProgressScaled(16);
		if (production > 0) {
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.MOISTENER,
					leftPos + 124, topPos + 36, 176, 74, 16 - production, 16);
		}

		int consumption = menu.getConsumptionProgressScaled(54);
		if (consumption > 0) {
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.MOISTENER,
					leftPos + 93, topPos + 18 + consumption, 176, 92 + consumption, 29, 54 - consumption);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileMoistener.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		int amountMb = menu.getResourceAmountMb();
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int x = leftPos + TANK_X;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, WATER_COLOR);
		}

		int tankLeft = leftPos + TANK_X;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = List.of(
					Component.translatable("fluid_type.minecraft.water"),
					Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}


}
