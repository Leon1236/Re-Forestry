package com.leon1236.reforestry.apiculture.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearySwarmer;

public class ScreenAlvearySwarmer extends AbstractContainerScreen<ContainerAlvearySwarmer> {
    private static final Identifier TEXTURE = ReForestry.id("textures/gui/swarmer.png");
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 169;

    public ScreenAlvearySwarmer(ContainerAlvearySwarmer menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);
    }
}
