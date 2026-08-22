package com.leon1236.reforestry.farming.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import com.leon1236.reforestry.api.agriculture.IFarmLogic;
import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.farming.gui.IFarmLedgerDelegate;
import com.leon1236.reforestry.farming.multiblock.IFarmControllerInternal;

@Environment(EnvType.CLIENT)
public class FarmLogicSlot {
	private static final int SIZE = 16;

	private final IFarmControllerInternal farmController;
	private final IFarmLedgerDelegate ledger;
	private final int xPos;
	private final int yPos;
	private final Direction farmDirection;

	public FarmLogicSlot(IFarmControllerInternal farmController, IFarmLedgerDelegate ledger, int xPos, int yPos,
			Direction farmDirection) {
		this.farmController = farmController;
		this.ledger = ledger;
		this.xPos = xPos;
		this.yPos = yPos;
		this.farmDirection = farmDirection;
	}

	public void draw(GuiGraphicsExtractor graphics, int leftPos, int topPos) {
		IFarmLogic logic = this.farmController.getFarmLogic(this.farmDirection);
		var icon = logic.getType().getIcon();
		if (!icon.isEmpty()) {
			graphics.fakeItem(icon, leftPos + this.xPos, topPos + this.yPos);
		}
	}

	public boolean isMouseOver(int mouseX, int mouseY, int leftPos, int topPos) {
		int x = leftPos + this.xPos;
		int y = topPos + this.yPos;
		return mouseX >= x && mouseX < x + SIZE && mouseY >= y && mouseY < y + SIZE;
	}

	public void appendTooltip(GuiGraphicsExtractor graphics, Font font, int mouseX, int mouseY) {
		IFarmLogic logic = this.farmController.getFarmLogic(this.farmDirection);
		IFarmType type = logic.getType();
		Component name = type.getDisplayName(logic.isManual());
		if (name.getString().isEmpty() && type.getIcon().isEmpty()) {
			return;
		}
		List<Component> lines = new ArrayList<>();
		lines.add(name);
		lines.add(Component.translatable("for.gui.farm.fertilizer", type.getFertilizerConsumption(this.farmController)));
		lines.add(Component.translatable("for.gui.farm.water",
				type.getWaterConsumption(this.farmController, this.ledger.getHydrationModifier())));
		graphics.setTooltipForNextFrame(font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
	}
}
