package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.gui.ContainerSocketedMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileSqueezer;

public class ContainerSqueezer extends ContainerSocketedMachine<TileSqueezer> {
    private static final int RESOURCE_X = 17;
    private static final int RESOURCE_Y = 21;
    private static final int SOCKET_X = 75;
    private static final int SOCKET_Y = 20;
    private static final int RESOURCE_GAP = 18;
    private static final int RESOURCE_COLUMNS = 3;
    private static final int REMNANT_X = 97;
    private static final int REMNANT_Y = 60;
    private static final int CAN_INPUT_X = 147;
    private static final int CAN_INPUT_Y = 24;
    private static final int CAN_OUTPUT_X = 147;
    private static final int CAN_OUTPUT_Y = 60;
    private static final int INVENTORY_Y = 84;

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerSqueezer(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileSqueezer.class));
    }

    public ContainerSqueezer(int containerId, Inventory playerInventory, TileSqueezer tile) {
        super(FactoryMenuTypes.SQUEEZER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileSqueezer tile) {
        for (int i = 0; i < TileSqueezer.SLOTS_RESOURCE_COUNT; i++) {
            int column = i % RESOURCE_COLUMNS;
            int row = i / RESOURCE_COLUMNS;
            addSlot(new ResourceSlot(tile, TileSqueezer.SLOT_RESOURCE_1 + i,
                    RESOURCE_X + column * RESOURCE_GAP, RESOURCE_Y + row * RESOURCE_GAP));
        }
        addSlot(new OutputSlot(tile, TileSqueezer.SLOT_REMNANT, REMNANT_X, REMNANT_Y));
        addSlot(new EmptyContainerSlot(tile, TileSqueezer.SLOT_CAN_INPUT, CAN_INPUT_X, CAN_INPUT_Y));
        addSlot(new OutputSlot(tile, TileSqueezer.SLOT_CAN_OUTPUT, CAN_OUTPUT_X, CAN_OUTPUT_Y));
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

    public int getProductAmountMb() {
        return tankData.get(0);
    }

    public int getProductFluidId() {
        return tankData.get(1);
    }

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileSqueezer.TANK_CAPACITY);
    }

    public Fluid getProductFluid() {
        return BuiltInRegistries.FLUID.byId(getProductFluidId());
    }

    @Override
    public void broadcastChanges() {
        tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getProductTank().getAmount()));
        Fluid fluid = tile.getProductTank().getResource().getFluid();
        tankData.set(1, BuiltInRegistries.FLUID.getId(fluid));
        super.broadcastChanges();
    }

    private static final class ResourceSlot extends Slot {
        private final TileSqueezer tile;
        private final int slotIndex;

        ResourceSlot(TileSqueezer tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(slotIndex, stack);
        }
    }

    private static final class EmptyContainerSlot extends Slot {
        private final TileSqueezer tile;
        private final int slotIndex;

        EmptyContainerSlot(TileSqueezer tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(slotIndex, stack);
        }
    }

    private static final class OutputSlot extends Slot {
        OutputSlot(TileSqueezer tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
