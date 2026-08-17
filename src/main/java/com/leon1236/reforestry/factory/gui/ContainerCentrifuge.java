package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.gui.ContainerSocketedMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileCentrifuge;

public class ContainerCentrifuge extends ContainerSocketedMachine<TileCentrifuge> implements IContainerEnergy {
    private static final int RESOURCE_X = 16;
    private static final int RESOURCE_Y = 37;
    private static final int PREVIEW_X = 49;
    private static final int PREVIEW_Y = 37;
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
        addDataSlots(tile.getEnergyData());
    }

    @Override
    protected void addMachineSlots(TileCentrifuge tile) {
        addSlot(new FilteredSlot(tile, TileCentrifuge.SLOT_RESOURCE, RESOURCE_X, RESOURCE_Y));
        addSlot(new PreviewSlot(tile.getCraftPreviewInventory(), 0, PREVIEW_X, PREVIEW_Y));
        addCircuitSocket(0, SOCKET_X, SOCKET_Y);
        for (int i = 0; i < TileCentrifuge.SLOT_PRODUCT_COUNT; i++) {
            int column = i % PRODUCT_COLUMNS;
            int row = i / PRODUCT_COLUMNS;
            addSlot(new OutputSlot(tile, TileCentrifuge.SLOT_PRODUCT_1 + i,
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

    @Override
    public int getEnergyStored() {
        return tile.getEnergyData().get(0);
    }

    @Override
    public int getEnergyCapacity() {
        return tile.getEnergyData().get(1);
    }

    @Override
    public int getEnergyMaxReceive() {
        return tile.getEnergyData().get(2);
    }

    @Override
    public int getEnergyUsage() {
        return tile.getEnergyData().get(3);
    }

    private static final class FilteredSlot extends Slot {
        private final TileCentrifuge tile;

        FilteredSlot(TileCentrifuge tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(getContainerSlot(), stack);
        }
    }

    private static final class PreviewSlot extends Slot {
        PreviewSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }

        @Override
        public boolean mayPickup(Player player) {
            return false;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }

    private static final class OutputSlot extends Slot {
        OutputSlot(TileCentrifuge tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
