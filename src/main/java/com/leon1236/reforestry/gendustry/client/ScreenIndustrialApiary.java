package com.leon1236.reforestry.gendustry.client;

import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.gendustry.blockentity.IndustrialApiaryBlockEntity;
import com.leon1236.reforestry.gendustry.menu.IndustrialApiaryMenu;

public class ScreenIndustrialApiary extends ScreenForestry<IndustrialApiaryMenu> {
	private static final int IMAGE_WIDTH = 176;
	private static final int IMAGE_HEIGHT = 166;

	private static final int PROGRESS_X = 60;
	private static final int PROGRESS_Y = 21;
	private static final int PROGRESS_WIDTH = 38;
	private static final int PROGRESS_HEIGHT = 18;

	public ScreenIndustrialApiary(IndustrialApiaryMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey(menu.getHintsKey());
		setOwnerLedger(menu.getTile().getOwner());
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		GendustryGuiTextures.blitBackground(guiGraphics, GendustryGuiTextures.INDUSTRIAL_APIARY, leftPos, topPos, imageWidth, imageHeight);

		int progress = getProgress(PROGRESS_WIDTH);
		GendustryGuiTextures.blitProgress(guiGraphics, GendustryGuiTextures.INDUSTRIAL_APIARY,
				leftPos + PROGRESS_X, topPos + PROGRESS_Y, 176, 0, progress, PROGRESS_HEIGHT);

		GuiErrorTabs.draw(guiGraphics, font, leftPos, topPos, menu::getErrorCount, menu::getErrorId,
				IndustrialApiaryBlockEntity.ERROR_SLOT_COUNT, mouseX, mouseY);

		int barLeft = leftPos + PROGRESS_X;
		int barTop = topPos + PROGRESS_Y;
		if (mouseX >= barLeft && mouseX < barLeft + PROGRESS_WIDTH
				&& mouseY >= barTop && mouseY < barTop + PROGRESS_HEIGHT) {
			guiGraphics.setTooltipForNextFrame(font,
					List.of(Component.literal(getProgress(100) + "%")),
					Optional.empty(), mouseX, mouseY);
		}
	}

	private int getProgress(int pixels) {
		int progress = menu.getHealthScaled(pixels);
		ItemStack queen = menu.getTile().getQueen();
		if (queen.getItem() instanceof ItemBeeGE bee && "queen".equals(bee.lifeStage())) {
			progress = pixels - progress;
		}
		return progress;
	}
}
