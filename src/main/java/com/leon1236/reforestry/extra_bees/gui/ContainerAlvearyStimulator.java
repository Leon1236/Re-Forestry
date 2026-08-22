package com.leon1236.reforestry.extra_bees.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.extra_bees.features.ExtraBeesMenuTypes;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyStimulator;

public class ContainerAlvearyStimulator extends ContainerExtraBeeAlvearyPart<TileAlvearyStimulator> {
	public ContainerAlvearyStimulator(int containerId, Inventory playerInventory, BlockPos pos) {
		this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearyStimulator.class));
	}

	public ContainerAlvearyStimulator(int containerId, Inventory playerInventory, TileAlvearyStimulator tile) {
		super(ExtraBeesMenuTypes.ALVEARY_STIMULATOR.type(), containerId, playerInventory, tile, 62);
		addDataSlots(tile.getEnergyData());
	}

	@Override
	protected void addPartSlots(TileAlvearyStimulator tile) {
		addSlot(new Slot(tile, TileAlvearyStimulator.SLOT_CIRCUIT, 41, 28));
	}

	public int getEnergyStored() {
		return tile.getEnergyData().get(0);
	}

	public int getEnergyCapacity() {
		return tile.getEnergyData().get(1);
	}

	public int getPowerUsage() {
		return tile.getEnergyData().get(2);
	}
}
