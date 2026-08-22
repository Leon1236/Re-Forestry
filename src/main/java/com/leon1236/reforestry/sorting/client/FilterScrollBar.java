package com.leon1236.reforestry.sorting.client;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class FilterScrollBar extends FilterWidget {
	private static final Identifier TABS = Identifier.parse("minecraft:textures/gui/container/creative_inventory/tabs.png");

	private boolean visible;
	private int minValue;
	private int maxValue;
	private int step;
	private int currentValue;
	@Nullable
	private IFilterScrollable listener;
	private boolean scrolling;
	private boolean wasClicked;
	private int initialMouseClickY;
	private int sliderOffset;

	public FilterScrollBar(int xPos, int yPos, int width, int height) {
		super(xPos, yPos);
		this.width = width;
		this.height = height;
	}

	public void setParameters(IFilterScrollable listener, int minValue, int maxValue, int step) {
		this.listener = listener;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.step = step;
		setValue(this.currentValue);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public int getValue() {
		return Mth.clamp(this.currentValue, this.minValue, this.maxValue);
	}

	public int setValue(int value) {
		this.currentValue = Mth.clamp(value, this.minValue, this.maxValue);
		if (this.listener != null) {
			this.listener.onScroll(this.currentValue);
		}
		int sliderHeight = 15;
		if (value >= this.maxValue) {
			this.sliderOffset = this.height - sliderHeight;
		} else if (value <= this.minValue) {
			this.sliderOffset = 0;
		} else {
			this.sliderOffset = (int) (((float) (this.currentValue - this.minValue) / (this.maxValue - this.minValue))
					* (float) (this.height - sliderHeight));
		}
		return this.currentValue;
	}

	@Override
	public void draw(GuiGraphicsExtractor graphics, int startX, int startY) {
		if (!isVisible()) {
			return;
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, TABS, startX + this.xPos, startY + this.yPos + this.sliderOffset,
				232.0f, 0.0f, 12, 15, 256, 256);
	}

	public void update(int mouseX, int mouseY, boolean mouseDown) {
		if (!isVisible()) {
			return;
		}
		int y = mouseY - this.yPos;
		if (!mouseDown && this.wasClicked) {
			this.wasClicked = false;
		}
		if (!mouseDown && this.scrolling) {
			this.scrolling = false;
		}
		if (this.scrolling) {
			applyValueFromY(y - this.initialMouseClickY);
		} else if (isSliderOver(mouseX, mouseY)) {
			if (mouseDown) {
				this.scrolling = true;
				this.initialMouseClickY = y - this.sliderOffset;
			}
		} else if (mouseDown && !this.wasClicked && isMouseOver(mouseX, mouseY)) {
			applyValueFromY(y - 7);
			this.wasClicked = true;
		}
	}

	private boolean isSliderOver(int mouseX, int mouseY) {
		int x = this.xPos;
		int y = this.yPos + this.sliderOffset;
		return mouseX >= x && mouseX <= x + this.width && mouseY >= y && mouseY <= y + 15;
	}

	private void applyValueFromY(int relativeY) {
		float range = (float) (this.maxValue - this.minValue);
		float value = (float) relativeY / (float) (this.height - 15);
		value *= range;
		if (value < (float) this.step / 2f) {
			setValue(this.minValue);
		} else if (value > this.maxValue - ((float) this.step / 2f)) {
			setValue(this.maxValue);
		} else {
			setValue((int) (this.minValue + (float) this.step * Math.round(value)));
		}
	}
}
