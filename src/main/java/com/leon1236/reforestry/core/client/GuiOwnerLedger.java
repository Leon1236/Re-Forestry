package com.leon1236.reforestry.core.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.mojang.authlib.GameProfile;

@Environment(EnvType.CLIENT)
public final class GuiOwnerLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 110;
	private static final int MAX_HEIGHT = 40;
	private static final int ICON_SIZE = 16;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final int HEADER_COLOR = 0xFFE1C92F;
	private static final int TEXT_COLOR = 0xFF000000;
	private static final Identifier LEDGER_LEFT = ReForestry.id("textures/gui/ledger_left.png");
	private static final Identifier ACCESS_ICON = ReForestry.id("textures/reforestry/atlas/gui/misc/access.shared.png");

	private final GameProfile owner;
	private boolean open;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;

	public GuiOwnerLedger(@Nullable GameProfile owner) {
		this.owner = owner;
	}

	public boolean isVisible() {
		return this.owner != null;
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageHeight, int mouseX, int mouseY) {
		if (!isVisible()) {
			return;
		}
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos - width;
		this.ledgerY = topPos + 8;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ACCESS_ICON,
				this.ledgerX + 4, this.ledgerY + 4, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				graphics.setTooltipForNextFrame(font,
						List.of(Component.translatable("for.gui.owner").append(": ").append(getOwnerName())),
						Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		graphics.text(font, Component.translatable("for.gui.owner"), this.ledgerX + 22, this.ledgerY + 8, HEADER_COLOR, false);
		graphics.text(font, getOwnerName(), this.ledgerX + 22, this.ledgerY + 20, TEXT_COLOR, false);
	}

	public boolean mouseClicked(double mouseX, double mouseY) {
		if (!isVisible()) {
			return false;
		}
		if (isOverTab(mouseX, mouseY)) {
			this.open = !this.open;
			return true;
		}
		return false;
	}

	public List<Rect2i> getExtraAreas() {
		if (!isVisible()) {
			return List.of();
		}
		if (!this.open && this.currentWidth <= MIN_WIDTH + 0.5f) {
			return List.of(new Rect2i(this.ledgerX, this.ledgerY, MIN_WIDTH, MIN_HEIGHT));
		}
		return List.of(new Rect2i(this.ledgerX, this.ledgerY, Math.round(this.currentWidth), Math.round(this.currentHeight)));
	}

	private Component getOwnerName() {
		if (this.owner.name() != null && !this.owner.name().isBlank()) {
			return Component.literal(this.owner.name());
		}
		UUID id = this.owner.id();
		return Component.literal(id != null ? id.toString() : "?");
	}

	private void updateAnimation() {
		long now = System.currentTimeMillis();
		if (this.lastUpdateMs == 0L) {
			this.lastUpdateMs = now;
		}
		float delta = (now - this.lastUpdateMs) / 1000.0f * ANIM_SPEED;
		this.lastUpdateMs = now;
		float targetWidth = this.open ? MAX_WIDTH : MIN_WIDTH;
		float targetHeight = this.open ? MAX_HEIGHT : MIN_HEIGHT;
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
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y, 0.0f, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y, 0.0f, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y + 4, 0.0f, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}
}
