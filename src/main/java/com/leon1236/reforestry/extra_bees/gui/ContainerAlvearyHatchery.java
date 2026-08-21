package com.leon1236.reforestry.extra_bees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyHatchery;

public class ContainerAlvearyHatchery extends ContainerExtraBeeAlvearyPart<TileAlvearyHatchery> {
	public ContainerAlvearyHatchery(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearyHatchery.class));
	}

	public ContainerAlvearyHatchery(int containerId, Inventory playerInventory, TileAlvearyHatchery tile) {
		super(ExtraBeesMenuTypes.ALVEARY_HATCHERY.type(), containerId, playerInventory, tile, 62);
	}

	@Override
	protected void addPartSlots(TileAlvearyHatchery tile) {
		for (int i = 0; i < TileAlvearyHatchery.SLOT_COUNT; i++) {
			addSlot(new Slot(tile, i, 43 + i * 18, 30));
		}
	}
}
