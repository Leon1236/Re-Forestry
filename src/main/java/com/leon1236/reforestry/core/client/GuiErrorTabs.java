package com.leon1236.reforestry.core.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IError;

public final class GuiErrorTabs {
	private static final int TAB_SIZE = 24;
	private static final int ICON_SIZE = 16;
	private static final int START_Y = 8;
	private static final int TEXTURE_SIZE = 256;
	private static final Identifier LEDGER_LEFT = ReForestry.id("textures/gui/ledger_left.png");

	private GuiErrorTabs() {
	}

	public static void draw(
			GuiGraphicsExtractor graphics,
			Font font,
			int leftPos,
			int topPos,
			IntSupplier errorCount,
			IntFunction<Short> errorIdAt,
			int maxErrors,
			int mouseX,
			int mouseY
	) {
		int count = Math.min(errorCount.getAsInt(), maxErrors);
		int y = topPos + START_Y;
		for (int i = 0; i < count; i++) {
			IError error = IForestryApi.INSTANCE.getErrorManager().getError(errorIdAt.apply(i));
			if (error == null) {
				continue;
			}

			int tabX = leftPos - TAB_SIZE;
			drawLedgerTab(graphics, tabX, y, TAB_SIZE, TAB_SIZE);

			int iconX = tabX + 4;
			int iconY = y + 4;
			Identifier texture = textureFor(error);
			graphics.blit(RenderPipelines.GUI_TEXTURED, texture, iconX, iconY, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

			if (mouseX >= tabX && mouseX < tabX + TAB_SIZE && mouseY >= y && mouseY < y + TAB_SIZE) {
				List<Component> lines = new ArrayList<>(2);
				lines.add(Component.translatable(error.getDescriptionTranslationKey()));
				lines.add(Component.translatable(error.getHelpTranslationKey()));
				graphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}

			y += TAB_SIZE;
		}
	}

	private static void drawLedgerTab(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y + 4, 0.0f, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y, TEXTURE_SIZE - width + 4, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y, 0.0f, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y + 4, TEXTURE_SIZE - width + 4, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}

	private static Identifier textureFor(IError error) {
		if (error instanceof ForestryError forestryError) {
			return forestryError.getTexture();
		}
		Identifier sprite = error.getSprite();
		return ReForestry.id("textures/reforestry/atlas/gui/" + sprite.getPath() + ".png");
	}
}
