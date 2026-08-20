package com.leon1236.reforestry.core.client;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.core.escritoire.EscritoireGame;
import com.leon1236.reforestry.core.escritoire.EscritoireGameToken;

@Environment(EnvType.CLIENT)
public final class GameTokenWidget {
	private static final ItemStack HIDDEN_TOKEN = new ItemStack(Items.BOOK);
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;

	private final EscritoireGame game;
	private final Identifier texture;
	private final int xPos;
	private final int yPos;
	private final int index;

	public GameTokenWidget(EscritoireGame game, Identifier texture, int xPos, int yPos, int index) {
		this.game = game;
		this.texture = texture;
		this.xPos = xPos;
		this.yPos = yPos;
		this.index = index;
	}

	@Nullable
	private EscritoireGameToken getToken() {
		return this.game.getToken(this.index);
	}

	public void draw(GuiGraphicsExtractor graphics, int leftPos, int topPos) {
		EscritoireGameToken token = getToken();
		if (token == null) {
			return;
		}

		int tokenColour = token.getTokenColour();
		int color = ARGB.color(255, (tokenColour >> 16) & 255, (tokenColour >> 8) & 255, tokenColour & 255);
		graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				this.texture,
				leftPos + this.xPos,
				topPos + this.yPos,
				228.0f,
				0.0f,
				22,
				22,
				256,
				256,
				color);

		ItemStack tokenStack = token.isVisible() ? token.getTokenStack() : HIDDEN_TOKEN;
		graphics.fakeItem(tokenStack, leftPos + this.xPos + 3, topPos + this.yPos + 3);

		Identifier overlay = token.getOverlayToken();
		if (overlay != null) {
			graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					overlay,
					leftPos + this.xPos + 3,
					topPos + this.yPos + 3,
					0.0f,
					0.0f,
					16,
					16,
					16,
					16);
		}
	}

	public boolean isMouseOver(double mouseX, double mouseY, int leftPos, int topPos) {
		int x = leftPos + this.xPos;
		int y = topPos + this.yPos;
		return mouseX >= x && mouseX < x + WIDTH && mouseY >= y && mouseY < y + HEIGHT;
	}

	public void drawTooltip(GuiGraphicsExtractor graphics, net.minecraft.client.gui.Font font, int mouseX, int mouseY) {
		EscritoireGameToken token = getToken();
		if (token == null || !token.isVisible()) {
			return;
		}
		graphics.setTooltipForNextFrame(font, java.util.List.of(token.getTooltip()), Optional.<TooltipComponent>empty(), mouseX, mouseY);
	}

	public int buttonId() {
		return this.index;
	}

	public int index() {
		return this.index;
	}
}
