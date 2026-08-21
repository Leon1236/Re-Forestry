package com.leon1236.reforestry.extra_bees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyMutator;

public class ContainerAlvearyMutator extends ContainerExtraBeeAlvearyPart<TileAlvearyMutator> {
	public ContainerAlvearyMutator(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearyMutator.class));
	}

	public ContainerAlvearyMutator(int containerId, Inventory playerInventory, TileAlvearyMutator tile) {
		super(ExtraBeesMenuTypes.ALVEARY_MUTATOR.type(), containerId, playerInventory, tile, 94);
	}

	@Override
	protected void addPartSlots(TileAlvearyMutator tile) {
		addSlot(new Slot(tile, TileAlvearyMutator.SLOT_MUTATOR, 79, 30));
	}
}
