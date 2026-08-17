package com.leon1236.reforestry.worktable.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.worktable.gui.ContainerWorktable;
import com.leon1236.reforestry.worktable.gui.WorktableButtons;
import com.leon1236.reforestry.worktable.recipes.RecipeMemory;
import com.leon1236.reforestry.worktable.tiles.TileWorktable;

@Environment(EnvType.CLIENT)
public class ScreenWorktable extends ScreenForestry<ContainerWorktable> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/worktable2.png");
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 218;
	private static final int SPACING = 18;
	private static final int MEMORY_X = 110;
	private static final int MEMORY_Y = 20;
	private static final int CLEAR_X = 66;
	private static final int CLEAR_Y = 19;
	private static final int CLEAR_SIZE = 7;
	private static final int PREV_X = 76;
	private static final int NEXT_X = 85;
	private static final int CONFLICT_Y = 56;
	private static final int CONFLICT_SIZE = 8;
	private static final int LOCK_OVERLAY = 0x99000000;

	public ScreenWorktable(ContainerWorktable menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("worktable");
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0f, 0.0f, imageWidth, imageHeight, 256, 256);

		TileWorktable tile = this.menu.getTile();
		RecipeMemory memory = tile.getMemory();
		int slot = 0;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				int x = leftPos + MEMORY_X + column * SPACING;
				int y = topPos + MEMORY_Y + row * SPACING;
				ItemStack stack = memory.getRecipeDisplayOutput(tile.getLevel(), slot);
				if (!stack.isEmpty()) {
					graphics.fakeItem(stack, x, y);
					if (memory.isLocked(slot)) {
						graphics.fill(x, y, x + 16, y + 16, LOCK_OVERLAY);
					}
					if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 16) {
						graphics.setTooltipForNextFrame(this.font, stack, mouseX, mouseY);
					}
				}
				slot++;
			}
		}

		if (tile.hasRecipeConflict()) {
			drawConflictArrow(graphics, leftPos + PREV_X, topPos + CONFLICT_Y, true);
			drawConflictArrow(graphics, leftPos + NEXT_X, topPos + CONFLICT_Y, false);
		}
	}

	private static void drawConflictArrow(GuiGraphicsExtractor graphics, int x, int y, boolean left) {
		int color = 0xFF404040;
		if (left) {
			graphics.fill(x + 5, y + 1, x + 6, y + 7, color);
			graphics.fill(x + 4, y + 2, x + 5, y + 6, color);
			graphics.fill(x + 3, y + 3, x + 4, y + 5, color);
			graphics.fill(x + 2, y + 4, x + 3, y + 5, color);
		} else {
			graphics.fill(x + 2, y + 1, x + 3, y + 7, color);
			graphics.fill(x + 3, y + 2, x + 4, y + 6, color);
			graphics.fill(x + 4, y + 3, x + 5, y + 5, color);
			graphics.fill(x + 5, y + 4, x + 6, y + 5, color);
		}
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (super.mouseClicked(event, doubleClick)) {
			return true;
		}
		if (this.minecraft == null || this.minecraft.player == null || this.minecraft.gameMode == null) {
			return false;
		}

		if (isHovering(CLEAR_X, CLEAR_Y, CLEAR_SIZE, CLEAR_SIZE, event.x(), event.y())) {
			return sendButton(WorktableButtons.CLEAR);
		}

		TileWorktable tile = this.menu.getTile();
		if (tile.hasRecipeConflict()) {
			if (isHovering(PREV_X, CONFLICT_Y, CONFLICT_SIZE, CONFLICT_SIZE, event.x(), event.y())) {
				return sendButton(WorktableButtons.PREV_CONFLICT);
			}
			if (isHovering(NEXT_X, CONFLICT_Y, CONFLICT_SIZE, CONFLICT_SIZE, event.x(), event.y())) {
				return sendButton(WorktableButtons.NEXT_CONFLICT);
			}
		}

		int slot = 0;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				int x = MEMORY_X + column * SPACING;
				int y = MEMORY_Y + row * SPACING;
				if (isHovering(x, y, 16, 16, event.x(), event.y())) {
					ItemStack stack = tile.getMemory().getRecipeDisplayOutput(tile.getLevel(), slot);
					if (!stack.isEmpty()) {
						int buttonId = event.button() == 1
								? WorktableButtons.lock(slot)
								: WorktableButtons.recall(slot);
						return sendButton(buttonId);
					}
					return false;
				}
				slot++;
			}
		}
		return false;
	}

	private boolean sendButton(int buttonId) {
		if (this.menu.clickMenuButton(this.minecraft.player, buttonId)) {
			this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, buttonId);
			return true;
		}
		return false;
	}
}
