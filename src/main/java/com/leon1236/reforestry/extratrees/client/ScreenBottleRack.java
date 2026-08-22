package com.leon1236.reforestry.extratrees.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.extratrees.gui.ContainerBottleRack;
import com.leon1236.reforestry.extratrees.tiles.TileBottleRack;

public class ScreenBottleRack extends ScreenForestry<ContainerBottleRack> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 222;
	private static final int TANK_WIDTH = 12;
	private static final int TANK_HEIGHT = 32;
	private static final int GRID_X = 8;
	private static final int GRID_Y = 18;
	private static final int COLUMN_SPACING = 14;
	private static final int ROW_SPACING = 36;
	private static final int FLUID_COLOR = 0xFFC4A35A;

	public ScreenBottleRack(ContainerBottleRack menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("bottle_rack");
		for (int i = 0; i < TileBottleRack.TANK_COUNT; i++) {
			int column = i % 12;
			int row = i / 12;
			int x = GRID_X + column * COLUMN_SPACING;
			int y = GRID_Y + row * ROW_SPACING;
			addTankClickRegion(x, y, TANK_WIDTH, TANK_HEIGHT, i);
		}
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		ExtraTreesGuiTextures.blitBackground(guiGraphics, ExtraTreesGuiTextures.BOTTLE_RACK, leftPos, topPos, imageWidth, imageHeight);
		for (int i = 0; i < TileBottleRack.TANK_COUNT; i++) {
			int column = i % 12;
			int row = i / 12;
			int x = GRID_X + column * COLUMN_SPACING;
			drawTank(guiGraphics, mouseX, mouseY, x, menu.getTankAmountMb(i), menu.getTankFluid(i));
		}
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int tankX, int amountMb, Fluid fluid) {
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int x = leftPos + tankX;
			int y = topPos + GRID_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, FLUID_COLOR);
		}
		int tankLeft = leftPos + tankX;
		int tankTop = topPos + GRID_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = new ArrayList<>();
			lines.add(fluidName(fluid, amountMb));
			lines.add(Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}

	private static Component fluidName(Fluid fluid, int amountMb) {
		if (amountMb <= 0 || fluid == null || fluid == Fluids.EMPTY) {
			return Component.translatable("for.gui.empty");
		}
		return FluidVariantAttributes.getName(FluidVariant.of(fluid));
	}
}
