package com.leon1236.reforestry.factory.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerBottler;
import com.leon1236.reforestry.factory.tiles.TileBottler;

public class ScreenBottler extends ScreenForestry<ContainerBottler> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	private static final int TANK_X = 80;
	private static final int TANK_Y = 14;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;


	public ScreenBottler(ContainerBottler menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("bottler");
		addTankClickRegion(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.BOTTLER, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY);

		int progressArrow = menu.getProgressPercent() * 22 / 100;
		if (progressArrow > 0) {
			int meterX = menu.isFillRecipe() ? 108 : 46;
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.BOTTLER,
					leftPos + meterX, topPos + 35, 177, 74, progressArrow, 16);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileBottler.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		int amountMb = menu.getResourceAmountMb();
		int capacity = menu.getTankCapacityMb();
		Fluid fluid = menu.getResourceFluid();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int color = colorFor(fluid);
			int x = leftPos + TANK_X;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
		}

		int tankLeft = leftPos + TANK_X;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = new ArrayList<>();
			Component fluidName;
			if (fluid == null || fluid.defaultFluidState().isEmpty()) {
				fluidName = Component.translatable("for.gui.empty");
			} else {
				fluidName = FluidVariantAttributes.getName(FluidVariant.of(fluid));
			}
			lines.add(fluidName);
			lines.add(Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}

	private static int colorFor(Fluid fluid) {
		if (fluid == null || fluid.defaultFluidState().isEmpty()) {
			return 0xFF808080;
		}
		return 0xFF000000 | (RenderUtil.getFluidColor(fluid) & 0xFFFFFF);
	}
}
