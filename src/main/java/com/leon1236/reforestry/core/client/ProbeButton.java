package com.leon1236.reforestry.core.client;

import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.core.escritoire.ContainerEscritoire;

@Environment(EnvType.CLIENT)
public final class ProbeButton {
	private static final int WIDTH = 22;
	private static final int HEIGHT = 25;

	private final Identifier texture;
	private final int xPos;
	private final int yPos;
	private boolean pressed;

	public ProbeButton(Identifier texture, int xPos, int yPos) {
		this.texture = texture;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void draw(GuiGraphicsExtractor graphics, int leftPos, int topPos) {
		graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				this.texture,
				leftPos + this.xPos,
				topPos + this.yPos,
				228.0f,
				this.pressed ? 47.0f : 22.0f,
				WIDTH,
				HEIGHT,
				256,
				256);
	}

	public boolean isMouseOver(double mouseX, double mouseY, int leftPos, int topPos) {
		int x = leftPos + this.xPos;
		int y = topPos + this.yPos;
		return mouseX >= x && mouseX < x + WIDTH && mouseY >= y && mouseY < y + HEIGHT;
	}

	public void drawTooltip(GuiGraphicsExtractor graphics, Font font, int mouseX, int mouseY) {
		graphics.setTooltipForNextFrame(
				font,
				List.of(Component.translatable("for.gui.escritoire.probe")),
				Optional.<TooltipComponent>empty(),
				mouseX,
				mouseY);
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public boolean isPressed() {
		return this.pressed;
	}

	public int buttonId() {
		return ContainerEscritoire.BUTTON_PROBE;
	}
}
