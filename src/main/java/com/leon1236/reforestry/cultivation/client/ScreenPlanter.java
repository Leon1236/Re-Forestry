package com.leon1236.reforestry.cultivation.client;

import java.util.List;
import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.agriculture.HorizontalDirection;
import com.leon1236.reforestry.core.client.GuiErrorTabs;
import com.leon1236.reforestry.core.client.RenderUtil;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.cultivation.gui.ContainerPlanter;
import com.leon1236.reforestry.cultivation.inventory.InventoryPlanter;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;
import com.leon1236.reforestry.farming.client.GuiFarmLedger;

@Environment(EnvType.CLIENT)
public class ScreenPlanter extends ScreenForestry<ContainerPlanter> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/planter.png");
	private static final int IMAGE_WIDTH = 202;
	private static final int IMAGE_HEIGHT = 192;
	private static final int TANK_X = 178;
	private static final int TANK_Y = 44;
	private static final int TANK_WIDTH = 16;
	private static final int TANK_HEIGHT = 58;
	private static final int OVERLAY_U = 202;
	private static final int OVERLAY_V = 18;
	private static final int GHOST_OVERLAY_U = 206;
	private static final int DIRECTION_COLOR = 0xFF404040;

	private final GuiFarmLedger farmLedger;

	public ScreenPlanter(ContainerPlanter menu, Inventory inventory, Component title) {
		super(menu, inventory, title, IMAGE_WIDTH, IMAGE_HEIGHT);
		addTankClickRegion(TANK_X, TANK_Y, TANK_WIDTH, TANK_HEIGHT, 0);
		this.farmLedger = new GuiFarmLedger(menu, 64);
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
					this.leftPos + 101, this.topPos + 21 + 17 - fertilizerRemain,
					this.imageWidth, 17 - fertilizerRemain, 4, fertilizerRemain, 256, 256);
		}

		GuiErrorTabs.draw(guiGraphics, this.font, this.leftPos, this.topPos, this.menu::getErrorCount, this.menu::getErrorId,
				TilePlanter.ERROR_SLOT_COUNT, mouseX, mouseY);
	}

	@Override
	protected void extractSlot(GuiGraphicsExtractor graphics, Slot slot, int mouseX, int mouseY) {
		if (isPlanterMachineSlot(slot) && !slot.hasItem()) {
			ItemStack ghost = ghostStackFor(slot);
			if (!ghost.isEmpty()) {
				graphics.fakeItem(ghost, slot.x, slot.y, slot.x + slot.y * this.imageWidth);
			}
			Component direction = directionLabel(slot);
			if (direction != null) {
				graphics.text(this.font, direction, slot.x + 5, slot.y + 4, DIRECTION_COLOR, false);
			}
			graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, slot.x, slot.y,
					GHOST_OVERLAY_U, 0, 16, 16, 256, 256);
		}
		super.extractSlot(graphics, slot, mouseX, mouseY);
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

	private static boolean isPlanterMachineSlot(Slot slot) {
		return slot.index >= 0 && slot.index < InventoryPlanter.CONFIG.count;
	}

	private ItemStack ghostStackFor(Slot slot) {
		int index = slot.getContainerSlot();
		TilePlanter tile = this.menu.getTile();
		InventoryPlanter.InventoryConfig config = InventoryPlanter.CONFIG;
		if (index >= config.resourcesStart && index < config.resourcesStart + config.resourcesCount) {
			List<ItemStack> stacks = tile.createResourceStacks();
			int local = index - config.resourcesStart;
			return local < stacks.size() ? stacks.get(local) : ItemStack.EMPTY;
		}
		if (index >= config.germlingsStart && index < config.germlingsStart + config.germlingsCount) {
			List<ItemStack> stacks = tile.createGermlingStacks();
			int local = index - config.germlingsStart;
			return local < stacks.size() ? stacks.get(local) : ItemStack.EMPTY;
		}
		if (index >= config.productionStart && index < config.productionStart + config.productionCount) {
			List<ItemStack> stacks = tile.createProductionStacks();
			int local = index - config.productionStart;
			return local < stacks.size() ? stacks.get(local) : ItemStack.EMPTY;
		}
		if (index >= config.fertilizerStart && index < config.fertilizerStart + config.fertilizerCount) {
			return new ItemStack(CoreItems.FERTILIZER_COMPOUND.item());
		}
		return ItemStack.EMPTY;
	}

	private static Component directionLabel(Slot slot) {
		if (!isPlanterMachineSlot(slot)) {
			return null;
		}
		int index = slot.getContainerSlot();
		InventoryPlanter.InventoryConfig config = InventoryPlanter.CONFIG;
		boolean resource = index >= config.resourcesStart && index < config.resourcesStart + config.resourcesCount;
		boolean germling = index >= config.germlingsStart && index < config.germlingsStart + config.germlingsCount;
		if (!resource && !germling) {
			return null;
		}
		Direction direction = HorizontalDirection.VALUES.get(index % 4);
		return Component.translatable("for.gui.planter." + direction.getSerializedName());
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
