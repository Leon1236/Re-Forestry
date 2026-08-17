package com.leon1236.reforestry.core.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.ReForestry;

@Environment(EnvType.CLIENT)
public final class GuiHintLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 124;
	private static final int MAX_TEXT_WIDTH = 90;
	private static final int ICON_SIZE = 16;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final Identifier LEDGER_LEFT = ReForestry.id("textures/gui/ledger_left.png");
	private static final Identifier HINT_ICON = ReForestry.id("textures/reforestry/atlas/gui/misc/hint.png");

	private final Component hintString;
	private final Component hintTooltip;
	private final int maxHeight;
	private boolean open;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;

	public GuiHintLedger(List<String> hints, Font font) {
		String hint = hints.get(ThreadLocalRandom.current().nextInt(hints.size()));
		this.hintString = Component.translatable("for.hints." + hint + ".desc");
		this.hintTooltip = Component.translatable("for.hints." + hint + ".tag");
		int lineCount = font.split(this.hintString, MAX_TEXT_WIDTH).size();
		this.maxHeight = (lineCount + 1) * font.lineHeight + 20;
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageHeight, int mouseX, int mouseY) {
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos - width;
		this.ledgerY = topPos + imageHeight - height - 4;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.blit(RenderPipelines.GUI_TEXTURED, HINT_ICON,
				this.ledgerX + 4, this.ledgerY + 4, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				graphics.setTooltipForNextFrame(font,
						List.of(this.hintTooltip),
						Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		graphics.text(font, Component.translatable("for.gui.didyouknow").append("?"),
				this.ledgerX + 22, this.ledgerY + 8, 0xFFE1C92F, true);
		int textY = this.ledgerY + 20;
		for (FormattedCharSequence line : font.split(this.hintString, MAX_TEXT_WIDTH)) {
			graphics.text(font, line, this.ledgerX + 12, textY, 0xFF000000, false);
			textY += font.lineHeight;
		}
	}

	public boolean mouseClicked(double mouseX, double mouseY) {
		if (isOverTab(mouseX, mouseY)) {
			this.open = !this.open;
			return true;
		}
		return false;
	}

	public List<Rect2i> getExtraAreas() {
		if (!this.open && this.currentWidth <= MIN_WIDTH + 0.5f) {
			return List.of(new Rect2i(this.ledgerX, this.ledgerY, MIN_WIDTH, MIN_HEIGHT));
		}
		return List.of(new Rect2i(this.ledgerX, this.ledgerY, Math.round(this.currentWidth), Math.round(this.currentHeight)));
	}

	private void updateAnimation() {
		long now = System.currentTimeMillis();
		if (this.lastUpdateMs == 0L) {
			this.lastUpdateMs = now;
		}
		float delta = (now - this.lastUpdateMs) / 1000.0f * ANIM_SPEED;
		this.lastUpdateMs = now;
		float targetWidth = this.open ? MAX_WIDTH : MIN_WIDTH;
		float targetHeight = this.open ? this.maxHeight : MIN_HEIGHT;
		this.currentWidth = Mth.lerp(Math.min(1.0f, delta), this.currentWidth, targetWidth);
		this.currentHeight = Mth.lerp(Math.min(1.0f, delta), this.currentHeight, targetHeight);
		if (Math.abs(this.currentWidth - targetWidth) < 0.5f) {
			this.currentWidth = targetWidth;
		}
		if (Math.abs(this.currentHeight - targetHeight) < 0.5f) {
			this.currentHeight = targetHeight;
		}
	}

	private boolean isOverTab(double mouseX, double mouseY) {
		return mouseX >= this.ledgerX && mouseX < this.ledgerX + MIN_WIDTH
				&& mouseY >= this.ledgerY && mouseY < this.ledgerY + MIN_HEIGHT;
	}

	private static void drawLedgerTab(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y + 4, 0.0f, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y, TEXTURE_SIZE - width + 4, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y, 0.0f, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y + 4, TEXTURE_SIZE - width + 4, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}
}
