package com.leon1236.reforestry.apiculture.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.gui.ContainerAlvearyHygroregulator;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ScreenAlvearyHygroregulator extends AbstractContainerScreen<ContainerAlvearyHygroregulator> {
    private static final Identifier TEXTURE = ReForestry.id("textures/gui/hygroregulator.png");
    private static final int IMAGE_WIDTH = 176;
    private static final int IMAGE_HEIGHT = 166;

    private static final int TANK_X = 104;
    private static final int TANK_Y = 17;
    private static final int TANK_WIDTH = 16;
    private static final int TANK_HEIGHT = 58;

    public ScreenAlvearyHygroregulator(ContainerAlvearyHygroregulator menu, Inventory inventory, Component title) {
        super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        ItemStack carried = this.menu.getCarried();
        if (this.menu instanceof IContainerLiquidTanks
                && PipetteTankHelper.canHandleClick(carried)
                && isHovering(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, event.x(), event.y())
                && this.menu.clickMenuButton(this.minecraft.player, 0)) {
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 0);
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(guiGraphics, mouseX, mouseY, delta);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

        Fluid fluid = menu.getResourceFluid();
        int amount = menu.getFluidAmountInMillibuckets();
        int capacity = menu.getCapacityInMillibuckets();
        boolean empty = fluid == null || fluid.defaultFluidState().isEmpty() || amount <= 0;

        if (!empty && capacity > 0) {
            int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amount / capacity);
            int color = 0xFF000000 | (RenderUtil.getFluidColor(fluid) & 0xFFFFFF);
            int x = leftPos + TANK_X;
            int y = topPos + TANK_Y + (TANK_HEIGHT - filled);
            guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
        }

        int tankLeft = leftPos + TANK_X;
        int tankTop = topPos + TANK_Y;
        if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
            Component fluidName = empty
                    ? Component.translatable("for.gui.empty")
                    : FluidVariantAttributes.getName(FluidVariant.of(fluid));
            List<Component> lines = List.of(fluidName, Component.literal(amount + " / " + capacity + " mB"));
            guiGraphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
        }
    }
}
