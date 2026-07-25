package com.leon1236.reforestry.factory.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.factory.gui.ContainerFabricator;
import com.leon1236.reforestry.factory.tiles.TileFabricator;

public class ScreenFabricator extends ScreenForestry<ContainerFabricator> {
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 211;

    private static final int MOLTEN_TANK_X = 26;
    private static final int TANK_Y = 48;
    private static final int TANK_WIDTH = 16;
    private static final int TANK_HEIGHT = 58;

    private static final int HEAT_METER_X = 55;
    private static final int HEAT_METER_Y = 17;
    private static final int HEAT_METER_WIDTH = 4;
    private static final int HEAT_METER_HEIGHT = 52;

    private static final int MELTING_MARKER_X = 52;
    private static final int MELTING_MARKER_WIDTH = 10;
    private static final int MELTING_MARKER_HEIGHT = 5;

    private static final int HEAT_FILL_COLOR = 0xFFE85A00;
    private static final int MELTING_MARKER_COLOR = 0xFFFF4444;
    private static final int GLASS_COLOR = 0xFFA4A4A4;


    public ScreenFabricator(ContainerFabricator menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		FactoryGuiTextures.blitBackground(guiGraphics, FactoryGuiTextures.FABRICATOR, leftPos, topPos, imageWidth, imageHeight);

        drawTank(guiGraphics, mouseX, mouseY, MOLTEN_TANK_X, menu.getMoltenAmountMb(), menu.getMoltenFluidType());

        int heatFilled = menu.getHeatScaled(HEAT_METER_HEIGHT);
        if (heatFilled > 0) {
            int x = leftPos + HEAT_METER_X;
            int y = topPos + HEAT_METER_Y + HEAT_METER_HEIGHT - heatFilled;
            guiGraphics.fill(x, y, x + HEAT_METER_WIDTH, y + heatFilled, HEAT_FILL_COLOR);
        }

        int meltingScaled = menu.getMeltingPointScaled(HEAT_METER_HEIGHT);
        if (meltingScaled > 0) {
            int x = leftPos + MELTING_MARKER_X;
            int y = topPos + HEAT_METER_Y + HEAT_METER_HEIGHT - meltingScaled - MELTING_MARKER_HEIGHT / 2;
            guiGraphics.fill(x, y, x + MELTING_MARKER_WIDTH, y + MELTING_MARKER_HEIGHT, MELTING_MARKER_COLOR);
        }

        GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
                TileFabricator.ERROR_SLOT_COUNT, mouseX, mouseY);

        int heatBarLeft = leftPos + HEAT_METER_X;
        int heatBarTop = topPos + HEAT_METER_Y;
        if (mouseX >= heatBarLeft && mouseX < heatBarLeft + HEAT_METER_WIDTH
                && mouseY >= heatBarTop && mouseY < heatBarTop + HEAT_METER_HEIGHT) {
            List<Component> lines;
            if (menu.getMeltingPoint() > 0) {
                lines = List.of(
                        Component.translatable("for.gui.fabricator.heat", menu.getHeat(), TileFabricator.MAX_HEAT),
                        Component.translatable("for.gui.fabricator.requiredHeat", menu.getMeltingPoint()));
            } else {
                lines = List.of(Component.translatable("for.gui.fabricator.heat", menu.getHeat(), TileFabricator.MAX_HEAT));
            }
            guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
        }
    }

    private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int tankX, int amountMb, int fluidType) {
        int capacity = menu.getTankCapacityMb();
        if (amountMb > 0 && capacity > 0) {
            int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
            int color = fluidType == 1 ? GLASS_COLOR : 0xFF808080;
            int x = leftPos + tankX;
            int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
            guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
        }

        int tankLeft = leftPos + tankX;
        int tankTop = topPos + TANK_Y;
        if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
            Component fluidName = fluidType == 1
                    ? Component.translatable("fluid_type.reforestry.glass")
                    : Component.translatable("for.gui.empty");
            List<Component> lines = List.of(fluidName, Component.literal(amountMb + " / " + capacity + " mB"));
            guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
        }
    }

}
