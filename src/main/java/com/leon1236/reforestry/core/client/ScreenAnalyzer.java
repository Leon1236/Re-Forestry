package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.gui.ContainerAnalyzer;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;

@Environment(EnvType.CLIENT)
public class ScreenAnalyzer extends ScreenForestry<ContainerAnalyzer> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/alyzer.png");
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 176;
	private static final int TANK_X = 95;
	private static final int TANK_Y = 24;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int METER_X = 64;
	private static final int METER_Y = 30;
	private static final int METER_HEIGHT = 46;
	private static final int HONEY_COLOR = 0xFFFFC423;

	public ScreenAnalyzer(ContainerAnalyzer menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("analyzer");
		addTankClickRegion(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		drawTank(graphics, mouseX, mouseY);
		drawAnalyzeMeter(graphics);

		GuiErrorTabs.draw(graphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				TileAnalyzer.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	private void drawAnalyzeMeter(GuiGraphicsExtractor graphics) {
		int height = menu.getProgressPercent() * METER_HEIGHT / 100;
		if (height <= 0) {
			return;
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
				leftPos + METER_X, topPos + METER_Y + METER_HEIGHT - height,
				176.0f, 60.0f + METER_HEIGHT - height, 4, height, 256, 256);
	}

	private void drawTank(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int amountMb = menu.getResourceAmountMb();
		int capacity = menu.getTankCapacityMb();
		if (amountMb > 0 && capacity > 0 && menu.getResourceFluidType() != 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int x = leftPos + TANK_X;
			int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
			graphics.fill(x, y, x + TANK_WIDTH, y + filled, HONEY_COLOR);
		}

		int tankLeft = leftPos + TANK_X;
		int tankTop = topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			List<Component> lines = new ArrayList<>();
			if (menu.getResourceFluidType() != 0) {
				lines.add(Component.translatable("fluid_type.reforestry.honey"));
			} else {
				lines.add(Component.translatable("for.gui.empty"));
			}
			lines.add(Component.literal(amountMb + " / " + capacity + " mB"));
			graphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}
}
