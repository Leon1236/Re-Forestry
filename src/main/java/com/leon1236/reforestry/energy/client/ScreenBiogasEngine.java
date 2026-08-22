package com.leon1236.reforestry.energy.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.energy.gui.ContainerBiogasEngine;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;

@Environment(EnvType.CLIENT)
public class ScreenBiogasEngine extends EngineScreen<ContainerBiogasEngine> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/bioengine.png");

	private static final int FUEL_TANK_X = 89;
	private static final int HEATING_TANK_X = 107;
	private static final int TANK_Y = 19;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int BURN_X = 30;
	private static final int BURN_Y = 47;
	private static final int BURN_SIZE = 16;

	public ScreenBiogasEngine(ContainerBiogasEngine menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		addTankClickRegion(FUEL_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		addTankClickRegion(HEATING_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 1);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		drawTank(graphics, mouseX, mouseY, FUEL_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT,
				menu.getFuelAmountMb(), menu.getTankCapacityMb(), menu.getFuelFluid(), true);
		drawTank(graphics, mouseX, mouseY, HEATING_TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT,
				menu.getHeatingAmountMb(), menu.getTankCapacityMb(), menu.getHeatingFluid(), true);
		drawTank(graphics, mouseX, mouseY, BURN_X, BURN_Y, BURN_SIZE, BURN_SIZE,
				menu.getBurnAmountMb(), menu.getBurnCapacityMb(), menu.getBurnFluid(), false);

		int temperature = menu.getOperatingTemperatureScaled(16);
		if (temperature > 16) {
			temperature = 16;
		}
		if (temperature > 0) {
			graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
					leftPos + 53, topPos + 47 + 16 - temperature,
					176.0f, 60 + 16 - temperature,
					4, temperature, 256, 256);
		}

		GuiErrorTabs.draw(graphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				EngineBlockEntity.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawTank(
			GuiGraphicsExtractor graphics,
			int mouseX,
			int mouseY,
			int tankX,
			int tankY,
			int width,
			int height,
			int amountMb,
			int capacityMb,
			Fluid fluid,
			boolean showAmount
	) {
		if (amountMb > 0 && capacityMb > 0) {
			int filled = Math.min(height, Math.max(1, height * amountMb / capacityMb));
			int color = colorFor(fluid);
			int x = leftPos + tankX;
			int y = topPos + tankY + (height - filled);
			graphics.fill(x, y, x + width, y + filled, color);
		}

		int tankLeft = leftPos + tankX;
		int tankTop = topPos + tankY;
		if (mouseX >= tankLeft && mouseX < tankLeft + width && mouseY >= tankTop && mouseY < tankTop + height) {
			List<Component> lines = new ArrayList<>();
			lines.add(nameFor(fluid));
			if (showAmount) {
				lines.add(Component.translatable("for.gui.tooltip.liquid.amount", amountMb, capacityMb));
			}
			graphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}

	private static int colorFor(Fluid fluid) {
		if (fluid == null || fluid == Fluids.EMPTY || fluid.defaultFluidState().isEmpty()) {
			return 0xFF808080;
		}
		return 0xFF000000 | (RenderUtil.getFluidColor(fluid) & 0xFFFFFF);
	}

	private static Component nameFor(Fluid fluid) {
		if (fluid == null || fluid == Fluids.EMPTY || fluid.defaultFluidState().isEmpty()) {
			return Component.translatable("for.gui.empty");
		}
		return FluidVariantAttributes.getName(FluidVariant.of(fluid));
	}
}
