package com.leon1236.reforestry.core.book.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.book.IBookCategory;
import com.leon1236.reforestry.api.book.IForesterBook;
import com.leon1236.reforestry.core.book.BookLoader;

@Environment(EnvType.CLIENT)
public class ScreenForesterBookCategories extends AbstractForesterBookScreen {
	private final boolean landing;

	public ScreenForesterBookCategories() {
		this(false);
	}

	public ScreenForesterBookCategories(boolean landing) {
		super(Component.translatable("item.reforestry.foresters_manual"));
		this.landing = landing;
	}

	@Override
	protected void init() {
		super.init();
		IForesterBook book = BookLoader.get().book();
		int column = 0;
		int row = 0;
		for (IBookCategory category : book.categories()) {
			if (category.entries().isEmpty()) {
				continue;
			}
			int x = leftPos + 16 + column * 52;
			int y = topPos + 36 + row * 28;
			addRenderableWidget(Button.builder(Component.translatable(category.nameKey()), button -> {
				playPageTurn();
				minecraft.gui.setScreen(new ScreenForesterBookEntries(category.id()));
			}).bounds(x, y, 48, 20).build());
			column++;
			if (column >= 3) {
				column = 0;
				row++;
			}
		}
	}

	@Override
	protected void extractBookBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractBookBackground(graphics, mouseX, mouseY, partialTick);
		IForesterBook book = BookLoader.get().book();
		if (landing) {
			Component landingText = BookTextHelper.format(book.landingTextKey());
			graphics.textWithWordWrap(font, landingText, leftPos + 16, topPos + 24, IMAGE_WIDTH - 32, TEXT_COLOR, false);
		}
		Component heading = Component.translatable("for.gui.book.category.index");
		int headingWidth = font.width(heading);
		graphics.text(font, heading, leftPos + (IMAGE_WIDTH - headingWidth) / 2, topPos + 12, TEXT_COLOR, false);
	}
}
