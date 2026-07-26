package com.leon1236.reforestry.factory.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerStill;
import com.leon1236.reforestry.factory.tiles.TileStill;

public class ScreenStill extends ScreenForestry<ContainerStill> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	private static final int RESOURCE_TANK_X = 35;
	private static final int PRODUCT_TANK_X = 125;
	private static final int TANK_Y = 15;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;

	private static final int BIOMASS_COLOR = 0xFF648429;
	private static final int BIO_ETHANOL_COLOR = 0xFFFF6F00;


	public ScreenStill(ContainerStill menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		addTankClickRegion(RESOURCE_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		addTankClickRegion(PRODUCT_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 1);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.STILL, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY, RESOURCE_TANK_X, menu.getResourceAmountMb(), menu.getResourceFluidType());
		drawTank(guiGraphics, mouseX, mouseY, PRODUCT_TANK_X, menu.getProductAmountMb(), menu.getProductFluidType());

		FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.STILL, leftPos + 81, topPos + 57, 176, 60, 14, 14);

		if (menu.getProgressPercent() > 0) {
			int massRemaining = menu.getProgressPercent() * 16 / 100;
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.STILL,
					leftPos + 84, topPos + 17 + massRemaining, 176, 74 + massRemaining, 4, 17 - massRemaining);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileStill.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int tankX, int amountMb, int fluidType) {
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int color = colorFor(fluidType);
			int x = leftPos + tankX;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
		}

		int tankLeft = leftPos + tankX;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			Component fluidName = nameFor(fluidType);
			List<Component> lines = List.of(fluidName, Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}


	private static int colorFor(int fluidType) {
		return switch (fluidType) {
			case 1 -> BIOMASS_COLOR;
			case 2 -> BIO_ETHANOL_COLOR;
			default -> 0xFF808080;
		};
	}

	private static Component nameFor(int fluidType) {
		return switch (fluidType) {
			case 1 -> Component.translatable("fluid_type.reforestry.biomass");
			case 2 -> Component.translatable("fluid_type.reforestry.bio_ethanol");
			default -> Component.translatable("for.gui.empty");
		};
	}

}
