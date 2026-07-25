package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySieve;

public class ContainerAlvearySieve extends ContainerAlvearyPart<TileAlvearySieve> {
    private static final int[][] POLLEN_SLOT_POSITIONS = {
            {94, 52}, {115, 39}, {73, 39}, {94, 26},
    };

    public ContainerAlvearySieve(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearySieve.class));
    }

    public ContainerAlvearySieve(int containerId, Inventory playerInventory, TileAlvearySieve tile) {
        super(ApicultureMenuTypes.ALVEARY_SIEVE.type(), containerId, playerInventory, tile, 87);
    }

    @Override
    protected void addPartSlots(TileAlvearySieve tile) {
        for (int i = 0; i < POLLEN_SLOT_POSITIONS.length; i++) {
            addSlot(new Slot(tile, TileAlvearySieve.SLOT_POLLEN_1 + i,
                    POLLEN_SLOT_POSITIONS[i][0], POLLEN_SLOT_POSITIONS[i][1]));
        }
        addSlot(new Slot(tile, TileAlvearySieve.SLOT_SIEVE, 43, 39));
    }
}
