package com.leon1236.reforestry.core.book.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;

import com.leon1236.reforestry.ReForestry;

@Environment(EnvType.CLIENT)
public abstract class AbstractForesterBookScreen extends Screen {
	protected static final int IMAGE_WIDTH = 180;
	protected static final int IMAGE_HEIGHT = 208;
	protected static final int TEXT_COLOR = 0xFF404040;
	protected static final Identifier ALMANAC_TEXTURE = ReForestry.id("textures/gui/almanac/almanac.png");

	protected int leftPos;
	protected int topPos;

	protected AbstractForesterBookScreen(Component title) {
		super(title);
	}

	@Override
	protected void init() {
		super.init();
		this.leftPos = (this.width - IMAGE_WIDTH) / 2;
		this.topPos = (this.height - IMAGE_HEIGHT) / 2;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		extractBookBackground(graphics, mouseX, mouseY, partialTick);
	}

	protected void extractBookBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, ALMANAC_TEXTURE, leftPos, topPos, 0.0f, 0.0f, IMAGE_WIDTH, IMAGE_HEIGHT, 256, 256);
	}

	protected Button addBackButton(Runnable action) {
		return Button.builder(Component.translatable("gui.back"), button -> action.run())
				.bounds(leftPos + 8, topPos + IMAGE_HEIGHT - 24, 50, 20)
				.build();
	}

	protected void playPageTurn() {
		if (this.minecraft != null) {
			this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0f));
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
}
