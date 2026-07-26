package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.core.IToolPipette;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

@Environment(EnvType.CLIENT)
public abstract class ScreenForestry<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	private static final int TITLE_COLOR = 0xFF404040;

	private final List<TankClickRegion> tankRegions = new ArrayList<>();

	protected ScreenForestry(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
		super(menu, inventory, title, imageWidth, imageHeight);
	}

	protected void addTankClickRegion(int x, int y, int width, int height, int tankSlot) {
		this.tankRegions.add(new TankClickRegion(x, y, width, height, tankSlot));
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (tryPipetteTankClick(event)) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	protected boolean tryPipetteTankClick(MouseButtonEvent event) {
		if (!(this.menu instanceof IContainerLiquidTanks)) {
			return false;
		}
		ItemStack carried = this.menu.getCarried();
		if (!(carried.getItem() instanceof IToolPipette)) {
			return false;
		}
		for (TankClickRegion region : this.tankRegions) {
			if (isHovering(region.x, region.y, region.width, region.height, event.x(), event.y())
					&& this.menu.clickMenuButton(this.minecraft.player, region.tankSlot)) {
				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, region.tankSlot);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		int titleWidth = this.font.width(this.title);
		graphics.text(this.font, this.title, (this.imageWidth - titleWidth) / 2, this.titleLabelY, TITLE_COLOR, false);
	}

	private record TankClickRegion(int x, int y, int width, int height, int tankSlot) {
	}
}
