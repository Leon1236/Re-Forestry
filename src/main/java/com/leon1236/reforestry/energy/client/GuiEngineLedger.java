package com.leon1236.reforestry.energy.client;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
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
import com.leon1236.reforestry.energy.gui.IEngineMenu;

@Environment(EnvType.CLIENT)
public final class GuiEngineLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 98;
	private static final int MAX_HEIGHT = 94;
	private static final int ICON_SIZE = 16;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final int HEADER_COLOR = 0xFF2A2A2A;
	private static final int LABEL_COLOR = 0xFF3F3F3F;
	private static final int VALUE_COLOR = 0xFF111111;
	private static final Identifier LEDGER_RIGHT = ReForestry.id("textures/gui/ledger.png");
	private static final Identifier ENERGY_ICON = ReForestry.id("textures/reforestry/atlas/gui/misc/energy.png");

	private final IEngineMenu menu;
	private boolean open;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;

	public GuiEngineLedger(IEngineMenu menu) {
		this.menu = menu;
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageWidth, int mouseX, int mouseY) {
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos + imageWidth;
		this.ledgerY = topPos + 8;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.blit(RenderPipelines.GUI_TEXTURED, ENERGY_ICON,
				this.ledgerX + 4, this.ledgerY + 4, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				graphics.setTooltipForNextFrame(font,
						List.of(Component.literal(formatRate(this.menu.getCurrentOutput()))),
						Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		int xHeader = this.ledgerX + 22;
		int xBody = this.ledgerX + 12;
		graphics.text(font, Component.translatable("for.gui.energy"), xHeader, this.ledgerY + 8, HEADER_COLOR, false);

		graphics.text(font, Component.translatable("for.gui.currentOutput").append(":"), xBody, this.ledgerY + 20, LABEL_COLOR, false);
		graphics.text(font, Component.literal(formatRate(this.menu.getCurrentOutput())), xBody, this.ledgerY + 32, VALUE_COLOR, false);

		graphics.text(font, Component.translatable("for.gui.stored").append(":"), xBody, this.ledgerY + 44, LABEL_COLOR, false);
		graphics.text(font, Component.literal(formatEnergy(this.menu.getEnergyStored())), xBody, this.ledgerY + 56, VALUE_COLOR, false);

		graphics.text(font, Component.translatable("for.gui.heat").append(":"), xBody, this.ledgerY + 68, LABEL_COLOR, false);
		graphics.text(font, Component.literal((this.menu.getHeat() / 10.0) + 20.0 + " C"), xBody, this.ledgerY + 80, VALUE_COLOR, false);
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

	private static String formatEnergy(int energy) {
		return NumberFormat.getIntegerInstance(Locale.ROOT).format(energy) + " RF";
	}

	private static String formatRate(int rate) {
		return formatEnergy(rate) + "/t";
	}

	private static void drawLedgerTab(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + width - 4, y + 4, TEXTURE_SIZE - 4, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y, TEXTURE_SIZE - width, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x + width - 4, y, TEXTURE_SIZE - 4, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_RIGHT, x, y + 4, TEXTURE_SIZE - width, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}
}
