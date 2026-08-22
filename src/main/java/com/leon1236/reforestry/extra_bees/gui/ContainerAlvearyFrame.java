package com.leon1236.reforestry.extra_bees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyFrameHousing;

public class ContainerAlvearyFrame extends ContainerExtraBeeAlvearyPart<TileAlvearyFrameHousing> {
	public ContainerAlvearyFrame(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearyFrameHousing.class));
	}

	public ContainerAlvearyFrame(int containerId, Inventory playerInventory, TileAlvearyFrameHousing tile) {
		super(ExtraBeesMenuTypes.ALVEARY_FRAME.type(), containerId, playerInventory, tile, 62);
	}

	@Override
	protected void addPartSlots(TileAlvearyFrameHousing tile) {
		addSlot(new Slot(tile, TileAlvearyFrameHousing.SLOT_FRAME, 79, 30));
	}
}
