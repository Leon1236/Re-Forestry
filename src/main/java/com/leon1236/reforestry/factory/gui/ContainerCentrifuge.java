package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

import com.leon1236.reforestry.core.gui.ContainerSocketedMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;

public class ContainerCentrifuge extends ContainerSocketedMachine<TileCentrifuge> {
    private static final int RESOURCE_X = 16;
    private static final int RESOURCE_Y = 37;
    private static final int SOCKET_X = 79;
    private static final int SOCKET_Y = 37;
    private static final int PRODUCT_X = 112;
    private static final int PRODUCT_Y = 19;
    private static final int PRODUCT_GAP = 18;
    private static final int PRODUCT_COLUMNS = 3;
    private static final int INVENTORY_Y = 84;

    public ContainerCentrifuge(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileCentrifuge.class));
    }

    public ContainerCentrifuge(int containerId, Inventory playerInventory, TileCentrifuge tile) {
        super(FactoryMenuTypes.CENTRIFUGE.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
    }

    @Override
    protected void addMachineSlots(TileCentrifuge tile) {
        addSlot(new Slot(tile, TileCentrifuge.SLOT_RESOURCE, RESOURCE_X, RESOURCE_Y));
        addCircuitSocket(0, SOCKET_X, SOCKET_Y);
        for (int i = 0; i < TileCentrifuge.SLOT_PRODUCT_COUNT; i++) {
            int column = i % PRODUCT_COLUMNS;
            int row = i / PRODUCT_COLUMNS;
            addSlot(new Slot(tile, TileCentrifuge.SLOT_PRODUCT_1 + i,
                    PRODUCT_X + column * PRODUCT_GAP, PRODUCT_Y + row * PRODUCT_GAP));
        }
    }

    public int getProgressPercent() {
        return tile.getProgressData().get(0);
    }

    public int getErrorCount() {
        return tile.getErrorData().get(0);
    }

    public short getErrorId(int index) {
        return (short) tile.getErrorData().get(index + 1);
    }
}
