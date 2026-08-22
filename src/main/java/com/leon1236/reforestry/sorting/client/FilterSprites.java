package com.leon1236.reforestry.sorting.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

public final class FilterSprites {
	private FilterSprites() {
	}

	public static Identifier texture(Identifier sprite) {
		return Identifier.fromNamespaceAndPath(sprite.getNamespace(), "textures/reforestry/atlas/gui/" + sprite.getPath() + ".png");
	}

	public static void blit(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, texture(sprite), x, y, 0.0f, 0.0f, 16, 16, 16, 16);
	}
}
