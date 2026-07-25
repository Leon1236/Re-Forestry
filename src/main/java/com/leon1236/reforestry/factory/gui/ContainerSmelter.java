package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.gui.ContainerSocketedMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileSmelter;

public class ContainerSmelter extends ContainerSocketedMachine<TileSmelter> {
    private static final int INPUT_X = 21;
    private static final int INPUT_Y = 21;
    private static final int SOCKET_X = 95;
    private static final int SOCKET_Y = 21;
    private static final int INPUT_GAP = 18;
    private static final int INPUT_COLUMNS = 3;
    private static final int PREVIEW_X = 95;
    private static final int PREVIEW_Y = 39;
    private static final int OUTPUT_X = 139;
    private static final int OUTPUT_Y = 39;
    private static final int INVENTORY_Y = 84;

    public ContainerSmelter(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileSmelter.class));
    }

    public ContainerSmelter(int containerId, Inventory playerInventory, TileSmelter tile) {
        super(FactoryMenuTypes.SMELTER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
    }

    @Override
    protected void addMachineSlots(TileSmelter tile) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < INPUT_COLUMNS; column++) {
                int slot = TileSmelter.SLOT_INPUT_1 + row * INPUT_COLUMNS + column;
                addSlot(new Slot(tile, slot, INPUT_X + column * INPUT_GAP, INPUT_Y + row * INPUT_GAP));
            }
        }
        addSlot(new PreviewSlot(tile.getCraftPreviewInventory(), 0, PREVIEW_X, PREVIEW_Y));
        addSlot(new OutputSlot(tile, TileSmelter.SLOT_OUTPUT, OUTPUT_X, OUTPUT_Y));
        addCircuitSocket(0, SOCKET_X, SOCKET_Y);
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
        OutputSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
