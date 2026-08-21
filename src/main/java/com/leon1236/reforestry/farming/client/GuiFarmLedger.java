package com.leon1236.reforestry.farming.client;

import java.util.List;
import java.util.Optional;

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

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;

@Environment(EnvType.CLIENT)
public final class GuiFarmLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 98;
	private static final int MAX_HEIGHT = 110;
	private static final int ICON_SIZE = 16;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final int HEADER_COLOR = 0xFF2A2A2A;
	private static final int LABEL_COLOR = 0xFF3F3F3F;
	private static final int VALUE_COLOR = 0xFF111111;
	private static final Identifier LEDGER_RIGHT = ReForestry.id("textures/gui/ledger.png");
	private static final Identifier WATER_ICON = Identifier.fromNamespaceAndPath("minecraft", "textures/item/water_bucket.png");

	private final IFarmLedgerDelegate delegate;
	private final int topOffset;
	private boolean open;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;

	public GuiFarmLedger(IFarmLedgerDelegate delegate, int topOffset) {
		this.delegate = delegate;
		this.topOffset = topOffset;
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageWidth, int mouseX, int mouseY) {
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos + imageWidth;
		this.ledgerY = topPos + this.topOffset;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.blit(RenderPipelines.GUI_TEXTURED, WATER_ICON,
				this.ledgerX + 4, this.ledgerY + 4, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				Component tooltip = Component.literal(percent(this.delegate.getHydrationModifier()) + " ")
						.append(Component.translatable("for.gui.hydration"));
				graphics.setTooltipForNextFrame(font, List.of(tooltip), Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		int xHeader = this.ledgerX + 22;
		int xBody = this.ledgerX + 12;
		graphics.text(font, Component.translatable("for.gui.hydration"), xHeader, this.ledgerY + 8, HEADER_COLOR, false);

		int y = this.ledgerY + 20;
		graphics.text(font, Component.translatable("for.gui.hydr.heat").append(":"), xBody, y, LABEL_COLOR, false);
		y += 12;
		graphics.text(font, Component.literal(percent(this.delegate.getHydrationTempModifier())), xBody, y, VALUE_COLOR, false);
		y += 12;
		graphics.text(font, Component.translatable("for.gui.hydr.humid").append(":"), xBody, y, LABEL_COLOR, false);
		y += 12;
		graphics.text(font, Component.literal(percent(this.delegate.getHydrationHumidModifier())), xBody, y, VALUE_COLOR, false);
		y += 12;
		graphics.text(font, Component.translatable("for.gui.hydr.rainfall").append(":"), xBody, y, LABEL_COLOR, false);
		y += 12;
		graphics.text(font, Component.literal(percent(this.delegate.getHydrationRainfallModifier())
				+ " (" + this.delegate.getDrought() + " d)"), xBody, y, VALUE_COLOR, false);
		y += 12;
		graphics.text(font, Component.translatable("for.gui.hydr.overall").append(":"), xBody, y, LABEL_COLOR, false);
		y += 12;
		graphics.text(font, Component.literal(percent(this.delegate.getHydrationModifier())), xBody, y, VALUE_COLOR, false);
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
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + width - 4, y + 4, TEXTURE_SIZE - 4, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y, TEXTURE_SIZE - width, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + width - 4, y, TEXTURE_SIZE - 4, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y + 4, TEXTURE_SIZE - width, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}

	private static String percent(float value) {
		return Math.round(value * 100) + "%";
	}
}
