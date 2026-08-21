package com.leon1236.reforestry.extratrees.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

public final class ExtraTreesGuiTextures {
	private ExtraTreesGuiTextures() {
	}

	public static final Identifier LUMBERMILL = ReForestry.id("textures/gui/lumbermill.png");
	public static final Identifier PRESS = ReForestry.id("textures/gui/press.png");
	public static final Identifier BREWERY = ReForestry.id("textures/gui/brewery.png");
	public static final Identifier DISTILLERY = ReForestry.id("textures/gui/distillery.png");

	public static void blitBackground(GuiGraphicsExtractor guiGraphics, Identifier texture, int left, int top, int width, int height) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, left, top, 0.0f, 0.0f, width, height, 256, 256);
	}

	public static void blitProgress(GuiGraphicsExtractor guiGraphics, Identifier texture, int x, int y, int u, int v, int width, int height) {
		if (width <= 0 || height <= 0) {
			return;
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, (float) u, (float) v, width, height, 256, 256);
	}
}
