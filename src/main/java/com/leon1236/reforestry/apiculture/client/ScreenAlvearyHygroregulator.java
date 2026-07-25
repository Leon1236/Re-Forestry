package com.leon1236.reforestry.apiculture.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearyHygroregulator;

public class ScreenAlvearyHygroregulator extends AbstractContainerScreen<ContainerAlvearyHygroregulator> {
    private static final Identifier TEXTURE = ReForestry.id("textures/gui/hygroregulator.png");
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 166;

    private static final int TANK_X = 104;
    private static final int TANK_Y = 17;
    private static final int TANK_WIDTH = 16;
    private static final int TANK_HEIGHT = 58;

    private static final int WATER_COLOR = 0xFF3F76E4;
    private static final int LAVA_COLOR = 0xFFCF5A17;

    public ScreenAlvearyHygroregulator(ContainerAlvearyHygroregulator menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

        int fluidType = menu.getFluidType();
        int amount = menu.getFluidAmountInMillibuckets();
        int capacity = menu.getCapacityInMillibuckets();

        if (fluidType != ContainerAlvearyHygroregulator.FLUID_NONE && amount > 0 && capacity > 0) {
            int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amount / capacity);
            int color = fluidType == ContainerAlvearyHygroregulator.FLUID_LAVA ? LAVA_COLOR : WATER_COLOR;
            int x = leftPos + TANK_X;
            int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
            guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
        }

        int tankLeft = leftPos + TANK_X;
        int tankTop = topPos + TANK_Y;
        if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
            Component fluidName = switch (fluidType) {
                case ContainerAlvearyHygroregulator.FLUID_WATER -> Component.translatable("block.minecraft.water");
                case ContainerAlvearyHygroregulator.FLUID_LAVA -> Component.translatable("block.minecraft.lava");
                default -> Component.translatable("for.gui.empty");
            };
            List<Component> lines = List.of(fluidName, Component.literal(amount + " / " + capacity + " mB"));
            guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
        }
    }
}
