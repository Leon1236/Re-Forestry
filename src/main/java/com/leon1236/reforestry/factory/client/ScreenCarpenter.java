package com.leon1236.reforestry.factory.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerCarpenter;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;

public class ScreenCarpenter extends ScreenForestry<ContainerCarpenter> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 218;

	private static final int TANK_X = 150;
	private static final int TANK_Y = 17;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;


	public ScreenCarpenter(ContainerCarpenter menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.CARPENTER, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY);

		int progressScaled = menu.getProgressPercent() * 16 / 100;
		FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.CARPENTER,
				leftPos + 98, topPos + 51 + 16 - progressScaled, 176, 60 + 16 - progressScaled, 4, progressScaled);

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileCarpenter.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		int amountMb = menu.getResourceAmountMb();
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int color = colorFor(menu.getResourceFluid());
			int x = leftPos + TANK_X;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
		}

		int tankLeft = leftPos + TANK_X;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			Fluid fluid = menu.getResourceFluid();
			Component fluidName;
			if (fluid == null || fluid.defaultFluidState().isEmpty()) {
				fluidName = Component.translatable("for.gui.empty");
			} else {
				fluidName = FluidVariantAttributes.getName(FluidVariant.of(fluid));
			}
			List<Component> lines = List.of(fluidName, Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}


	private static int colorFor(Fluid fluid) {
		if (fluid == null || fluid.defaultFluidState().isEmpty()) {
			return 0xFF808080;
		}
		int hash = BuiltInRegistries.FLUID.getKey(fluid).hashCode();
		int r = 64 + (hash & 0x7F);
		int g = 64 + ((hash >> 8) & 0x7F);
		int b = 64 + ((hash >> 16) & 0x7F);
		return 0xFF000000 | (r << 16) | (g << 8) | b;
	}

}
