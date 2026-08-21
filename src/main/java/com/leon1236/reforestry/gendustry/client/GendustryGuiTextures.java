package com.leon1236.reforestry.gendustry.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public final class GendustryGuiTextures {
	private GendustryGuiTextures() {
	}

	public static final Identifier PROCESSOR = ReForestry.id("textures/gui/processor.png");
	public static final Identifier SAMPLER = ReForestry.id("textures/gui/sampler.png");
	public static final Identifier MUTATRON = ReForestry.id("textures/gui/mutatron.png");
	public static final Identifier ADVANCED_MUTATRON = ReForestry.id("textures/gui/advanced_mutatron.png");

	static void blitBackground(GuiGraphicsExtractor guiGraphics, Identifier texture, int left, int top, int width, int height) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, left, top, 0.0f, 0.0f, width, height, 256, 256);
	}

	static void blitProgress(GuiGraphicsExtractor guiGraphics, Identifier texture, int x, int y, int u, int v, int width, int height) {
		if (width <= 0 || height <= 0) {
			return;
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, (float) u, (float) v, width, height, 256, 256);
	}
}
