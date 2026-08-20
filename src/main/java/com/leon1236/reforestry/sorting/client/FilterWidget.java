package com.leon1236.reforestry.sorting.client;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public abstract class FilterWidget {
	protected final int xPos;
	protected final int yPos;
	protected int width = 16;
	protected int height = 16;

	protected FilterWidget(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getX() {
		return this.xPos;
	}

	public int getY() {
		return this.yPos;
	}

	public abstract void draw(GuiGraphicsExtractor graphics, int startX, int startY);

	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= this.xPos && mouseX <= this.xPos + this.width && mouseY >= this.yPos && mouseY <= this.yPos + this.height;
	}

	public void handleMouseClick(double mouseX, double mouseY, int mouseButton) {
	}

	@Nullable
	public List<Component> getToolTip(int mouseX, int mouseY) {
		return null;
	}

	public void drawTooltip(GuiGraphicsExtractor graphics, Font font, int mouseX, int mouseY) {
		List<Component> tooltip = getToolTip(mouseX, mouseY);
		if (tooltip != null && !tooltip.isEmpty()) {
			graphics.setTooltipForNextFrame(font, tooltip, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}
}
