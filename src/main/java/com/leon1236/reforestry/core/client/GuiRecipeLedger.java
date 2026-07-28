package com.leon1236.reforestry.core.client;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.gui.IContainerRecipeBook;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;

public final class GuiRecipeLedger {
	private static final int MIN_WIDTH = 24;
	private static final int MIN_HEIGHT = 24;
	private static final int MAX_WIDTH = 124;
	private static final int ICON_SIZE = 16;
	private static final int CELL = 18;
	private static final int COLUMNS = 5;
	private static final int MAX_ROWS = 6;
	private static final int VISIBLE_SLOTS = COLUMNS * MAX_ROWS;
	private static final int PAD = 6;
	private static final int HEADER = 20;
	private static final int TEXTURE_SIZE = 256;
	private static final float ANIM_SPEED = 8.0f;
	private static final int GHOST_OVERLAY = 0x66FFFFFF;
	private static final int SCROLLBAR_TRACK = 0x55000000;
	private static final int SCROLLBAR_THUMB = 0xFFE1C92F;
	private static final Identifier LEDGER_LEFT = ReForestry.id("textures/gui/ledger_left.png");
	private static final Identifier HINT_ICON = ReForestry.id("textures/reforestry/atlas/gui/misc/hint.png");

	private final IContainerRecipeBook recipeBook;
	private boolean open;
	private float currentWidth = MIN_WIDTH;
	private float currentHeight = MIN_HEIGHT;
	private long lastUpdateMs;
	private int ledgerX;
	private int ledgerY;
	private int scrollRow;

	public GuiRecipeLedger(IContainerRecipeBook recipeBook) {
		this.recipeBook = recipeBook;
	}

	public void draw(GuiGraphicsExtractor graphics, Font font, int leftPos, int topPos, int imageHeight, int mouseX, int mouseY) {
		updateAnimation();

		int width = Math.round(this.currentWidth);
		int height = Math.round(this.currentHeight);
		this.ledgerX = leftPos - width;
		this.ledgerY = topPos + imageHeight - height - 4;

		drawLedgerTab(graphics, this.ledgerX, this.ledgerY, width, height);
		graphics.blit(RenderPipelines.GUI_TEXTURED, HINT_ICON,
				this.ledgerX + 4, this.ledgerY + 4, 0.0f, 0.0f, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

		if (width < MAX_WIDTH - 1) {
			if (isOverTab(mouseX, mouseY) && !this.open) {
				graphics.setTooltipForNextFrame(font,
						List.of(Component.translatable("for.gui.recipes.tooltip")),
						Optional.<TooltipComponent>empty(), mouseX, mouseY);
			}
			return;
		}

		graphics.text(font, Component.translatable("for.gui.recipes"), this.ledgerX + 22, this.ledgerY + 6, 0xFFE1C92F, true);

		List<MachineRecipeEntry> recipes = this.recipeBook.getGuiRecipes();
		clampScroll(recipes.size());
		int startIndex = startIndex();
		int startX = this.ledgerX + PAD;
		int startY = this.ledgerY + HEADER;
		int endIndex = Math.min(recipes.size(), startIndex + VISIBLE_SLOTS);
		for (int index = startIndex; index < endIndex; index++) {
			int pos = index - startIndex;
			ItemStack stack = recipes.get(index).result();
			int x = startX + (pos % COLUMNS) * CELL;
			int y = startY + (pos / COLUMNS) * CELL;
			if (!stack.isEmpty()) {
				graphics.fakeItem(stack, x, y);
				graphics.fill(x, y, x + ICON_SIZE, y + ICON_SIZE, GHOST_OVERLAY);
				graphics.itemDecorations(font, stack, x, y);
			}
			if (mouseX >= x && mouseX < x + ICON_SIZE && mouseY >= y && mouseY < y + ICON_SIZE) {
				graphics.setTooltipForNextFrame(font, stack, mouseX, mouseY);
			}
		}
		drawScrollbar(graphics, recipes.size());
	}

	public boolean mouseClicked(double mouseX, double mouseY, IntConsumer onSelect) {
		if (isOverTab(mouseX, mouseY) && this.currentWidth <= MIN_WIDTH + 1) {
			toggleOpen();
			return true;
		}

		if (!this.open || this.currentWidth < MAX_WIDTH - 1) {
			if (isOverLedger(mouseX, mouseY)) {
				toggleOpen();
				return true;
			}
			return false;
		}

		List<MachineRecipeEntry> recipes = this.recipeBook.getGuiRecipes();
		clampScroll(recipes.size());
		int startIndex = startIndex();
		int startX = this.ledgerX + PAD;
		int startY = this.ledgerY + HEADER;
		int endIndex = Math.min(recipes.size(), startIndex + VISIBLE_SLOTS);
		for (int index = startIndex; index < endIndex; index++) {
			int pos = index - startIndex;
			int x = startX + (pos % COLUMNS) * CELL;
			int y = startY + (pos / COLUMNS) * CELL;
			if (mouseX >= x && mouseX < x + ICON_SIZE && mouseY >= y && mouseY < y + ICON_SIZE) {
				onSelect.accept(index);
				return true;
			}
		}

		if (isOverLedger(mouseX, mouseY)) {
			toggleOpen();
			return true;
		}
		return false;
	}

	public boolean mouseScrolled(double mouseX, double mouseY, double scrollY) {
		if (!this.open || this.currentWidth < MAX_WIDTH - 1 || !isOverLedger(mouseX, mouseY)) {
			return false;
		}
		int recipeCount = this.recipeBook.getGuiRecipes().size();
		int maxScroll = maxScrollRow(recipeCount);
		if (maxScroll <= 0) {
			return false;
		}
		this.scrollRow = Mth.clamp(this.scrollRow - (int) Math.signum(scrollY), 0, maxScroll);
		return true;
	}

	public List<Rect2i> getExtraAreas() {
		if (!this.open && this.currentWidth <= MIN_WIDTH + 1) {
			return List.of(new Rect2i(this.ledgerX, this.ledgerY, MIN_WIDTH, MIN_HEIGHT));
		}
		return List.of(new Rect2i(this.ledgerX, this.ledgerY, Math.round(this.currentWidth), Math.round(this.currentHeight)));
	}

	@Nullable
	public HoveredIngredient getHoveredIngredient(double mouseX, double mouseY) {
		if (!this.open || this.currentWidth < MAX_WIDTH - 1) {
			return null;
		}
		List<MachineRecipeEntry> recipes = this.recipeBook.getGuiRecipes();
		clampScroll(recipes.size());
		int startIndex = startIndex();
		int startX = this.ledgerX + PAD;
		int startY = this.ledgerY + HEADER;
		int endIndex = Math.min(recipes.size(), startIndex + VISIBLE_SLOTS);
		for (int index = startIndex; index < endIndex; index++) {
			int pos = index - startIndex;
			ItemStack stack = recipes.get(index).result();
			int x = startX + (pos % COLUMNS) * CELL;
			int y = startY + (pos / COLUMNS) * CELL;
			if (!stack.isEmpty()
					&& mouseX >= x && mouseX < x + ICON_SIZE
					&& mouseY >= y && mouseY < y + ICON_SIZE) {
				return new HoveredIngredient(stack, new Rect2i(x, y, ICON_SIZE, ICON_SIZE));
			}
		}
		return null;
	}

	public record HoveredIngredient(ItemStack stack, Rect2i area) {
	}

	private void toggleOpen() {
		this.open = !this.open;
		if (!this.open) {
			this.scrollRow = 0;
		}
	}

	private boolean isOverTab(double mouseX, double mouseY) {
		return mouseX >= this.ledgerX && mouseX < this.ledgerX + MIN_WIDTH
				&& mouseY >= this.ledgerY && mouseY < this.ledgerY + MIN_HEIGHT;
	}

	private boolean isOverLedger(double mouseX, double mouseY) {
		return mouseX >= this.ledgerX && mouseX < this.ledgerX + this.currentWidth
				&& mouseY >= this.ledgerY && mouseY < this.ledgerY + this.currentHeight;
	}

	private int startIndex() {
		return this.scrollRow * COLUMNS;
	}

	private void clampScroll(int recipeCount) {
		this.scrollRow = Mth.clamp(this.scrollRow, 0, maxScrollRow(recipeCount));
	}

	private static int maxScrollRow(int recipeCount) {
		return Math.max(0, rowsFor(recipeCount) - MAX_ROWS);
	}

	private void drawScrollbar(GuiGraphicsExtractor graphics, int recipeCount) {
		int maxScroll = maxScrollRow(recipeCount);
		if (maxScroll <= 0) {
			return;
		}
		int trackX = this.ledgerX + MAX_WIDTH - 5;
		int trackY = this.ledgerY + HEADER;
		int trackH = MAX_ROWS * CELL;
		graphics.fill(trackX, trackY, trackX + 2, trackY + trackH, SCROLLBAR_TRACK);
		int thumbH = Math.max(8, trackH * MAX_ROWS / (MAX_ROWS + maxScroll));
		int thumbY = trackY + (trackH - thumbH) * this.scrollRow / maxScroll;
		graphics.fill(trackX, thumbY, trackX + 2, thumbY + thumbH, SCROLLBAR_THUMB);
	}

	private void updateAnimation() {
		long now = System.currentTimeMillis();
		if (this.lastUpdateMs == 0) {
			this.lastUpdateMs = now;
			return;
		}
		float move = ANIM_SPEED * (now - this.lastUpdateMs) / 16.667f;
		this.lastUpdateMs = now;

		List<MachineRecipeEntry> recipes = this.recipeBook.getGuiRecipes();
		int maxHeight = Math.max(MIN_HEIGHT, HEADER + Math.min(MAX_ROWS, rowsFor(recipes.size())) * CELL + PAD);

		if (this.open && this.currentWidth < MAX_WIDTH) {
			this.currentWidth = Math.min(MAX_WIDTH, this.currentWidth + move);
		} else if (!this.open && this.currentWidth > MIN_WIDTH) {
			this.currentWidth = Math.max(MIN_WIDTH, this.currentWidth - move);
		}

		if (this.open && this.currentHeight < maxHeight) {
			this.currentHeight = Math.min(maxHeight, this.currentHeight + move);
		} else if (!this.open && this.currentHeight > MIN_HEIGHT) {
			this.currentHeight = Math.max(MIN_HEIGHT, this.currentHeight - move);
		} else if (this.open) {
			this.currentHeight = maxHeight;
		}
	}

	private static int rowsFor(int count) {
		if (count <= 0) {
			return 1;
		}
		return (count + COLUMNS - 1) / COLUMNS;
	}

	private static void drawLedgerTab(GuiGraphicsExtractor graphics, int x, int y, int width, int height) {
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y + 4, 0.0f, TEXTURE_SIZE - height + 4, 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y, TEXTURE_SIZE - width + 4, 0.0f, width - 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x, y, 0.0f, 0.0f, 4, 4, TEXTURE_SIZE, TEXTURE_SIZE);
		graphics.blit(RenderPipelines.GUI_TEXTURED, LEDGER_LEFT, x + 4, y + 4, TEXTURE_SIZE - width + 4, TEXTURE_SIZE - height + 4, width - 4, height - 4, TEXTURE_SIZE, TEXTURE_SIZE);
	}
}
