package com.leon1236.reforestry.core.book.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.book.IBookCategory;
import com.leon1236.reforestry.api.book.IBookEntry;
import com.leon1236.reforestry.core.book.BookLoader;

@Environment(EnvType.CLIENT)
public class ScreenForesterBookEntries extends AbstractForesterBookScreen {
	private final Identifier categoryId;

	public ScreenForesterBookEntries(Identifier categoryId) {
		super(Component.translatable("item.reforestry.foresters_manual"));
		this.categoryId = categoryId;
	}

	@Override
	protected void init() {
		super.init();
		IBookCategory category = BookLoader.get().book().category(categoryId);
		if (category == null) {
			addRenderableWidget(addBackButton(() -> minecraft.gui.setScreen(new ScreenForesterBookCategories())));
			return;
		}
		int column = 0;
		int row = 0;
		for (IBookEntry entry : category.entries()) {
			int x = leftPos + 12 + column * 56;
			int y = topPos + 28 + row * 22;
			addRenderableWidget(Button.builder(Component.translatable(entry.nameKey()), button -> {
				playPageTurn();
				minecraft.gui.setScreen(new ScreenForesterBookPages(entry.id()));
			}).bounds(x, y, 52, 20).build());
			column++;
			if (column >= 3) {
				column = 0;
				row++;
			}
		}
		addRenderableWidget(addBackButton(() -> {
			playPageTurn();
			minecraft.gui.setScreen(new ScreenForesterBookCategories());
		}));
	}

	@Override
	protected void extractBookBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractBookBackground(graphics, mouseX, mouseY, partialTick);
		IBookCategory category = BookLoader.get().book().category(categoryId);
		if (category == null) {
			Component missing = Component.translatable("for.gui.book.coming_soon");
			graphics.textWithWordWrap(font, missing, leftPos + 16, topPos + 48, IMAGE_WIDTH - 32, TEXT_COLOR, false);
			return;
		}
		Component title = Component.translatable(category.nameKey());
		int titleWidth = font.width(title);
		graphics.text(font, title, leftPos + (IMAGE_WIDTH - titleWidth) / 2, topPos + 10, TEXT_COLOR, false);
		if (category.entries().isEmpty()) {
			Component empty = Component.translatable("for.gui.book.coming_soon");
			graphics.textWithWordWrap(font, empty, leftPos + 16, topPos + 48, IMAGE_WIDTH - 32, TEXT_COLOR, false);
		}
	}
}
