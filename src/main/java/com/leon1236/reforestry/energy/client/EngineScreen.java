package com.leon1236.reforestry.energy.client;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.energy.gui.IEngineMenu;

@Environment(EnvType.CLIENT)
public class EngineScreen<T extends AbstractContainerMenu & IEngineMenu> extends ScreenForestry<T> {
	protected static final int IMAGE_WIDTH = 176;
	protected static final int IMAGE_HEIGHT = 166;

	private final GuiEngineLedger engineLedger;

	public EngineScreen(T menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey(menu.getHintKey());
		this.engineLedger = new GuiEngineLedger(menu);
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		this.engineLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageWidth, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.engineLedger.mouseClicked(event.x(), event.y())) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public List<Rect2i> getRecipeLedgerAreas() {
		List<Rect2i> areas = super.getRecipeLedgerAreas();
		areas.addAll(this.engineLedger.getExtraAreas());
		return areas;
	}
}
