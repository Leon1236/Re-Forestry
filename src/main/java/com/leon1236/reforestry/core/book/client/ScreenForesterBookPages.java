package com.leon1236.reforestry.core.book.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.book.IBookEntry;
import com.leon1236.reforestry.api.book.IBookPage;
import com.leon1236.reforestry.core.book.BookLoader;

@Environment(EnvType.CLIENT)
public class ScreenForesterBookPages extends AbstractForesterBookScreen {
	private final Identifier entryId;
	private int pageIndex;

	public ScreenForesterBookPages(Identifier entryId) {
		this(entryId, 0);
	}

	public ScreenForesterBookPages(Identifier entryId, int pageIndex) {
		super(Component.translatable("item.reforestry.foresters_manual"));
		this.entryId = entryId;
		this.pageIndex = pageIndex;
	}

	@Override
	protected void init() {
		super.init();
		IBookEntry entry = BookLoader.get().book().entry(entryId);
		if (entry == null) {
			addRenderableWidget(addBackButton(() -> minecraft.gui.setScreen(new ScreenForesterBookCategories())));
			return;
		}
		addRenderableWidget(Button.builder(Component.literal("<"), button -> changePage(-1))
				.bounds(leftPos + 12, topPos + IMAGE_HEIGHT - 24, 20, 20)
				.build());
		addRenderableWidget(Button.builder(Component.literal(">"), button -> changePage(1))
				.bounds(leftPos + IMAGE_WIDTH - 32, topPos + IMAGE_HEIGHT - 24, 20, 20)
				.build());
		addRenderableWidget(addBackButton(() -> {
			playPageTurn();
			minecraft.gui.setScreen(new ScreenForesterBookEntries(entry.categoryId()));
		}));
	}

	private void changePage(int delta) {
		IBookEntry entry = BookLoader.get().book().entry(entryId);
		if (entry == null || entry.pages().isEmpty()) {
			return;
		}
		int next = pageIndex + delta;
		if (next < 0 || next >= entry.pages().size()) {
			return;
		}
		playPageTurn();
		pageIndex = next;
	}

	@Override
	protected void extractBookBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractBookBackground(graphics, mouseX, mouseY, partialTick);
		IBookEntry entry = BookLoader.get().book().entry(entryId);
		if (entry == null) {
			return;
		}
		Component title = Component.translatable(entry.nameKey());
		int titleWidth = font.width(title);
		graphics.text(font, title, leftPos + (IMAGE_WIDTH - titleWidth) / 2, topPos + 10, TEXT_COLOR, false);
		if (entry.pages().isEmpty()) {
			Component empty = Component.translatable("for.gui.book.coming_soon");
			graphics.textWithWordWrap(font, empty, leftPos + 16, topPos + 48, IMAGE_WIDTH - 32, TEXT_COLOR, false);
			return;
		}
		int clampedIndex = Math.min(pageIndex, entry.pages().size() - 1);
		IBookPage page = entry.pages().get(clampedIndex);
		if (minecraft.level != null) {
			BookPageRenderer.render(graphics, font, minecraft.level, page, leftPos + 16, topPos + 28, IMAGE_WIDTH - 32, IMAGE_HEIGHT - 64);
		}
		Component pageNumber = Component.literal((clampedIndex + 1) + " / " + entry.pages().size());
		int pageNumberWidth = font.width(pageNumber);
		graphics.text(font, pageNumber, leftPos + (IMAGE_WIDTH - pageNumberWidth) / 2, topPos + IMAGE_HEIGHT - 18, TEXT_COLOR, false);
	}
}
