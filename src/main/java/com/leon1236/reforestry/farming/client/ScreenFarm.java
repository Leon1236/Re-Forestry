package com.leon1236.reforestry.farming.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.farming.gui.ContainerFarm;
import com.leon1236.reforestry.farming.multiblock.IFarmControllerInternal;
import com.leon1236.reforestry.farming.multiblock.TileFarm;

public class ScreenFarm extends ScreenForestry<ContainerFarm> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/mfarm.png");
	private static final int IMAGE_WIDTH = 216;
	private static final int IMAGE_HEIGHT = 220;
	private static final int TANK_X = 15;
	private static final int TANK_Y = 19;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int OVERLAY_U = 216;
	private static final int OVERLAY_V = 18;

	private final List<FarmLogicSlot> logicSlots = new ArrayList<>();
	private final GuiFarmLedger farmLedger;

	public ScreenFarm(ContainerFarm menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHintKey("farm");
		addTankClickRegion(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		IFarmControllerInternal controller = menu.getTile().getMultiblockLogic().getController();
		this.logicSlots.add(new FarmLogicSlot(controller, menu, 69, 22, Direction.NORTH));
		this.logicSlots.add(new FarmLogicSlot(controller, menu, 69, 58, Direction.SOUTH));
		this.logicSlots.add(new FarmLogicSlot(controller, menu, 51, 40, Direction.WEST));
		this.logicSlots.add(new FarmLogicSlot(controller, menu, 87, 40, Direction.EAST));
		this.farmLedger = new GuiFarmLedger(menu, 36);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(guiGraphics, mouseX, mouseY, delta);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0.0f, 0.0f,
				this.imageWidth, this.imageHeight, 256, 256);

		drawTank(guiGraphics, mouseX, mouseY);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos + TANK_X, this.topPos + TANK_Y,
				OVERLAY_U, OVERLAY_V, TANK_WIDTH, TANK_HEIGHT, 256, 256);

		int fertilizerRemain = this.menu.getFertilizerScaled();
		if (fertilizerRemain > 0) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE,
					this.leftPos + 81, this.topPos + 94 + 17 - fertilizerRemain,
					this.imageWidth, 17 - fertilizerRemain, 4, fertilizerRemain, 256, 256);
		}

		for (FarmLogicSlot slot : this.logicSlots) {
			slot.draw(guiGraphics, this.leftPos, this.topPos);
			if (slot.isMouseOver(mouseX, mouseY, this.leftPos, this.topPos)) {
				slot.appendTooltip(guiGraphics, this.font, mouseX, mouseY);
			}
		}

		GuiErrorTabs.draw(guiGraphics, this.font, this.leftPos, this.topPos, this.menu::getErrorCount, this.menu::getErrorId,
				TileFarm.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
		super.extractRenderState(graphics, mouseX, mouseY, partialTick);
		this.farmLedger.draw(graphics, this.font, this.leftPos, this.topPos, this.imageWidth, mouseX, mouseY);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		if (this.farmLedger.mouseClicked(event.x(), event.y())) {
			return true;
		}
		return super.mouseClicked(event, doubleClick);
	}

	@Override
	public List<Rect2i> getRecipeLedgerAreas() {
		List<Rect2i> areas = super.getRecipeLedgerAreas();
		areas.addAll(this.farmLedger.getExtraAreas());
		return areas;
	}

	private void drawTank(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		int amountMb = this.menu.getTankAmountMb();
		int capacity = this.menu.getTankCapacityMb();
		Fluid fluid = this.menu.getTankFluid();
		boolean empty = fluid == null || fluid.defaultFluidState().isEmpty() || amountMb <= 0;
		if (!empty && capacity > 0) {
			int filled = Math.min(TANK_HEIGHT, TANK_HEIGHT * amountMb / capacity);
			int color = 0xFF000000 | (RenderUtil.getFluidColor(fluid) & 0xFFFFFF);
			int x = this.leftPos + TANK_X;
			int y = this.topPos + TANK_Y + (TANK_HEIGHT - filled);
			guiGraphics.fill(x, y, x + TANK_WIDTH, y + filled, color);
		}

		int tankLeft = this.leftPos + TANK_X;
		int tankTop = this.topPos + TANK_Y;
		if (mouseX >= tankLeft && mouseX < tankLeft + TANK_WIDTH && mouseY >= tankTop && mouseY < tankTop + TANK_HEIGHT) {
			Component fluidName = empty
					? Component.translatable("for.gui.empty")
					: FluidVariantAttributes.getName(FluidVariant.of(fluid));
			List<Component> lines = List.of(fluidName, Component.literal(amountMb + " / " + capacity + " mB"));
			guiGraphics.setTooltipForNextFrame(this.font, lines, Optional.<TooltipComponent>empty(), mouseX, mouseY);
		}
	}
}
