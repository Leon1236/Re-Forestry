package com.leon1236.reforestry.gendustry.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.gendustry.blockentity.ReplicatorBlockEntity;
import com.leon1236.reforestry.gendustry.menu.ReplicatorMenu;

public class ScreenReplicator extends ScreenForestry<ReplicatorMenu> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 176;

	private static final int DNA_TANK_X = 11;
	private static final int PROTEIN_TANK_X = 31;
	private static final int TANK_Y = 8;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;

	public ScreenReplicator(ReplicatorMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey(menu.getHintsKey());
		addTankClickRegion(DNA_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		addTankClickRegion(PROTEIN_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 1);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		GendustryGuiTextures.blitBackground(guiGraphics, GendustryGuiTextures.REPLICATOR, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY, DNA_TANK_X, menu.getDnaAmountMb(), menu.getDnaFluid());
		drawTank(guiGraphics, mouseX, mouseY, PROTEIN_TANK_X, menu.getProteinAmountMb(), menu.getProteinFluid());

		int progress = menu.getProgressPercent() * 42 / 100;
		GendustryGuiTextures.blitProgress(guiGraphics, GendustryGuiTextures.REPLICATOR,
				leftPos + 70, topPos + 46, 176, 60, progress, 18);

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				ReplicatorBlockEntity.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int tankX, int amountMb, Fluid fluid) {
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int color = colorFor(fluid);
			int x = leftPos + tankX;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
		}

		int tankLeft = leftPos + tankX;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			Component fluidName;
			if (fluid == null || fluid.defaultFluidState().isEmpty()) {
				fluidName = Component.translatable("for.gui.empty");
			} else {
				fluidName = FluidVariantAttributes.getName(FluidVariant.of(fluid));
			}
			List<Component> lines = List.of(fluidName, Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(font, lines, Optional.empty(), mouseX, mouseY);
		}
	}

	private static int colorFor(Fluid fluid) {
		if (fluid == null || fluid.defaultFluidState().isEmpty()) {
			return 0xFF808080;
		}
		return 0xFF000000 | (RenderUtil.getFluidColor(fluid) & 0xFFFFFF);
	}
}
