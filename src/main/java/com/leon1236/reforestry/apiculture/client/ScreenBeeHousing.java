package com.leon1236.reforestry.apiculture.client;

import java.util.ArrayList;
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
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;
import com.leon1236.reforestry.apiculture.gui.ContainerBeeHousing;
import com.leon1236.reforestry.apiculture.tiles.TileBeeHousing;

public class ScreenBeeHousing extends AbstractContainerScreen<ContainerBeeHousing> {
    private static final Identifier TEXTURE = ReForestry.id("textures/gui/apiary.png");
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 190;

    private static final int METER_X = 20;
    private static final int METER_Y = 37;
    private static final int METER_WIDTH = 4;
    private static final int METER_HEIGHT = 46;
    private static final int METER_COLOR = 0xFFE8A400;

    private static final int ERROR_ICON_SIZE = 16;
    private static final int ERROR_ICON_GAP = 2;
    private static final int ERROR_ROW_Y = 8;
    private static final int ERROR_ROW_RIGHT = 8;

    public ScreenBeeHousing(ContainerBeeHousing menu, Inventory inventory, Component title) {
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

        int errorCount = Math.min(menu.getErrorCount(), TileBeeHousing.ERROR_SLOT_COUNT);
        for (int i = 0; i < errorCount; i++) {
            IError error = IForestryApi.INSTANCE.getErrorManager().getError(menu.getErrorId(i));
            if (error == null) {
                continue;
            }
            Identifier texture = textureFor(error);
            int x = leftPos + imageWidth - ERROR_ROW_RIGHT - ERROR_ICON_SIZE - i * (ERROR_ICON_SIZE + ERROR_ICON_GAP);
            int y = topPos + ERROR_ROW_Y;
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0f, 0.0f, ERROR_ICON_SIZE, ERROR_ICON_SIZE, ERROR_ICON_SIZE, ERROR_ICON_SIZE);

            if (mouseX >= x && mouseX < x + ERROR_ICON_SIZE && mouseY >= y && mouseY < y + ERROR_ICON_SIZE) {
                List<Component> lines = new ArrayList<>(2);
                lines.add(Component.translatable(error.getDescriptionTranslationKey()));
                lines.add(Component.translatable(error.getHelpTranslationKey()));
                guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
            }
        }
    }

    private static Identifier textureFor(IError error) {
        if (error instanceof ForestryError forestryError) {
            return forestryError.getTexture();
        }
        Identifier sprite = error.getSprite();
        return ReForestry.id("textures/reforestry/atlas/gui/" + sprite.getPath() + ".png");
    }
}
