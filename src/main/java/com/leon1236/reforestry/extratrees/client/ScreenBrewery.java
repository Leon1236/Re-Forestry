package com.leon1236.reforestry.extratrees.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.extratrees.gui.ContainerBrewery;

public class ScreenBrewery extends ScreenForestry<ContainerBrewery> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 222;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int INPUT_TANK_X = 76;
	private static final int OUTPUT_TANK_X = 134;
	private static final int TANK_Y = 15;
	private static final int FLUID_COLOR = 0xFFC4A35A;

	public ScreenBrewery(ContainerBrewery menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("brewery");
		addTankClickRegion(INPUT_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		addTankClickRegion(OUTPUT_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 1);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		ExtraTreesGuiTextures.blitBackground(guiGraphics, ExtraTreesGuiTextures.BREWERY, leftPos, topPos, imageWidth, imageHeight);
		drawTank(guiGraphics, mouseX, mouseY, INPUT_TANK_X, menu.getInputAmountMb());
		drawTank(guiGraphics, mouseX, mouseY, OUTPUT_TANK_X, menu.getOutputAmountMb());
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int tankX, int amountMb) {
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int x = leftPos + tankX;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, FLUID_COLOR);
		}
		int tankLeft = leftPos + tankX;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = new ArrayList<>();
			lines.add(Component.translatable("for.gui.empty"));
			lines.add(Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}
}
