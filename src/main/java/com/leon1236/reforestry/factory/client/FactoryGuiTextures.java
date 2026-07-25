package com.leon1236.reforestry.factory.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;

final class FactoryGuiTextures {
	private FactoryGuiTextures() {
	}

	static final Identifier CENTRIFUGE = ReForestry.id("textures/gui/centrifugesocket2.png");
	static final Identifier SQUEEZER = ReForestry.id("textures/gui/squeezersocket.png");
	static final Identifier STILL = ReForestry.id("textures/gui/still.png");
	static final Identifier FERMENTER = ReForestry.id("textures/gui/fermenter.png");
	static final Identifier BOTTLER = ReForestry.id("textures/gui/bottler.png");
	static final Identifier MOISTENER = ReForestry.id("textures/gui/moistener.png");
	static final Identifier CARPENTER = ReForestry.id("textures/gui/carpenter.png");
	static final Identifier FABRICATOR = ReForestry.id("textures/gui/fabricator.png");
	static final Identifier SMELTER = ReForestry.id("textures/gui/smelter.png");

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
