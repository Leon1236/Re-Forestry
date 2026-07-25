package com.leon1236.reforestry.apiculture.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearySwarmer;

public class ContainerAlvearySwarmer extends ContainerAlvearyPart<TileAlvearySwarmer> {
    private static final int[][] SLOT_POSITIONS = {
            {79, 52}, {100, 39}, {58, 39}, {79, 26},
    };

    public ContainerAlvearySwarmer(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearySwarmer.class));
    }

    public ContainerAlvearySwarmer(int containerId, Inventory playerInventory, TileAlvearySwarmer tile) {
        super(ApicultureMenuTypes.ALVEARY_SWARMER.type(), containerId, playerInventory, tile, 87);
    }

    @Override
    protected void addPartSlots(TileAlvearySwarmer tile) {
        for (int i = 0; i < SLOT_POSITIONS.length; i++) {
            addSlot(new Slot(tile, i, SLOT_POSITIONS[i][0], SLOT_POSITIONS[i][1]));
        }
    }
}
