package com.leon1236.reforestry.gendustry.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.gendustry.menu.AdvancedMutatronMenu;

public class ScreenAdvancedMutatron extends AbstractMutatronScreen<AdvancedMutatronMenu> {
	private static final int LEFT_BUTTON_X = 130;
	private static final int RIGHT_BUTTON_X = 141;
	private static final int BUTTON_Y = 73;
	private static final int BUTTON_SIZE = 10;

	private boolean showCycleButtons;

	public ScreenAdvancedMutatron(AdvancedMutatronMenu menu, Inventory inventory, Component title) {
		super(GendustryGuiTextures.ADVANCED_MUTATRON, menu, inventory, title);
		menu.setDataListener(this::updateButtonVisibility);
	}

	private void updateButtonVisibility() {
		this.showCycleButtons = this.menu.getPossibilityCount() > 4;
	}

	@Override
	protected void init() {
		super.init();
		updateButtonVisibility();
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);

		int slots = Math.min(4, this.menu.getPossibilityCount());
		for (int i = 0; i < slots; i++) {
			int x = this.leftPos + 63 + i * 16;
			int y = this.topPos + 70;
			int v = 176;
			if (this.menu.getSelected() == i + this.menu.getOffset()) {
				v += 18;
			} else if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 18) {
				v += 36;
			}
			GendustryGuiTextures.blitProgress(guiGraphics, getGuiTexture(), x, y, 0, v, 16, 18);
		}

		if (this.showCycleButtons) {
			guiGraphics.text(this.font, "<", this.leftPos + LEFT_BUTTON_X + 1, this.topPos + BUTTON_Y, 0xFF404040, false);
			guiGraphics.text(this.font, ">", this.leftPos + RIGHT_BUTTON_X + 1, this.topPos + BUTTON_Y, 0xFF404040, false);
		}
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.minecraft != null && this.minecraft.gameMode != null && this.minecraft.player != null) {
			if (this.showCycleButtons) {
				if (isHovering(LEFT_BUTTON_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE, event.x(), event.y())) {
					this.minecraft.gameMode.handleInventoryButtonClick(
							this.menu.containerId, AdvancedMutatronMenu.BUTTON_CYCLE_LEFT);
					return true;
				}
				if (isHovering(RIGHT_BUTTON_X, BUTTON_Y, BUTTON_SIZE, BUTTON_SIZE, event.x(), event.y())) {
					this.minecraft.gameMode.handleInventoryButtonClick(
							this.menu.containerId, AdvancedMutatronMenu.BUTTON_CYCLE_RIGHT);
					return true;
				}
			}

			Slot slot = this.hoveredSlot;
			if (slot instanceof AdvancedMutatronMenu.ChoiceSlot choiceSlot) {
				this.minecraft.gameMode.handleInventoryButtonClick(
						this.menu.containerId, AdvancedMutatronMenu.CHOICE_CLICKED + choiceSlot.choiceIndex);
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}
}
