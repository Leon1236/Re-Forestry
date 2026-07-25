package com.leon1236.reforestry.apiculture.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.apiculture.gui.ContainerAlveary;
import com.leon1236.reforestry.apiculture.multiblock.TileAlveary;

public class ScreenAlveary extends ScreenForestry<ContainerAlveary> {
    private static final Identifier TEXTURE = ReForestry.id("textures/gui/alveary.png");
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 190;

    private static final int METER_X = 20;
    private static final int METER_Y = 37;
    private static final int METER_WIDTH = 4;
    private static final int METER_HEIGHT = 46;
    private static final int METER_COLOR = 0xFFE8A400;


    public ScreenAlveary(ContainerAlveary menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

        int filled = METER_HEIGHT * menu.getWorkProgressPercent() / 100;
        if (filled > 0) {
            int x = leftPos + METER_X;
            int y = topPos + METER_Y + (METER_HEIGHT - filled);
            guiGraphics.fill(x, y, x + METER_WIDTH, y + filled, METER_COLOR);
        }

        GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
                TileAlveary.ERROR_SLOT_COUNT, mouseX, mouseY);
    }

}
