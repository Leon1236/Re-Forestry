package com.leon1236.reforestry.factory.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerFermenter;
import com.leon1236.reforestry.factory.tiles.TileFermenter;

public class ScreenFermenter extends ScreenForestry<ContainerFermenter> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	private static final int RESOURCE_TANK_X = 35;
	private static final int PRODUCT_TANK_X = 125;
	private static final int TANK_Y = 19;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;

	private static final int WATER_COLOR = 0xFF3F76E4;
	private static final int JUICE_COLOR = 0xFFA8C972;
	private static final int HONEY_COLOR = 0xFFFFC423;
	private static final int BIOMASS_COLOR = 0xFF648429;
	private static final int SHORT_MEAD_COLOR = 0xFFEF9A38;


	public ScreenFermenter(ContainerFermenter menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.FERMENTER, leftPos, topPos, imageWidth, imageHeight);

		drawTank(guiGraphics, mouseX, mouseY, RESOURCE_TANK_X, menu.getResourceAmountMb(), menu.getResourceFluidType());
		drawTank(guiGraphics, mouseX, mouseY, PRODUCT_TANK_X, menu.getProductAmountMb(), menu.getProductFluidType());

		int fuelRemain = menu.getFuelProgressScaled(16);
		if (fuelRemain > 0) {
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.FERMENTER,
					leftPos + 98, topPos + 46 + 17 - fuelRemain, 176, 78 + 17 - fuelRemain, 4, fuelRemain);
		}

		int bioRemain = menu.getFermentationProgressScaled(16);
		if (bioRemain > 0) {
			FactoryGuiTextures.blitProgress(guiGraphics, FactoryGuiTextures.FERMENTER,
					leftPos + 74, topPos + 32 + 17 - bioRemain, 176, 60 + 17 - bioRemain, 4, bioRemain);
		}

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileFermenter.ERROR_SLOT_COUNT, mouseX, mouseY);
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
			case 1 -> WATER_COLOR;
			case 2 -> JUICE_COLOR;
			case 3 -> HONEY_COLOR;
			case 4 -> BIOMASS_COLOR;
			case 5 -> SHORT_MEAD_COLOR;
			default -> 0xFF808080;
		};
	}

	private static Component nameFor(int fluidType) {
		return switch (fluidType) {
			case 1 -> Component.translatable("fluid_type.minecraft.water");
			case 2 -> Component.translatable("fluid_type.reforestry.juice");
			case 3 -> Component.translatable("fluid_type.reforestry.honey");
			case 4 -> Component.translatable("fluid_type.reforestry.biomass");
			case 5 -> Component.translatable("fluid_type.reforestry.short_mead");
			default -> Component.translatable("for.gui.empty");
		};
	}

}
