package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileStill;

public class ContainerStill extends ContainerMachine<TileStill> {
    private static final int CAN_X = 10;
    private static final int CAN_Y = 36;
    private static final int RESOURCE_X = 150;
    private static final int RESOURCE_Y = 18;
    private static final int PRODUCT_X = 150;
    private static final int PRODUCT_Y = 54;
    private static final int INVENTORY_Y = 84;

    private static final int FLUID_NONE = 0;
    private static final int FLUID_BIOMASS = 1;
    private static final int FLUID_BIO_ETHANOL = 2;

    private final SimpleContainerData tankData = new SimpleContainerData(4);

    public ContainerStill(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileStill.class));
    }

    public ContainerStill(int containerId, Inventory playerInventory, TileStill tile) {
        super(FactoryMenuTypes.STILL.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileStill tile) {
        addSlot(new FilledContainerSlot(tile, TileStill.SLOT_CAN, CAN_X, CAN_Y));
        addSlot(new EmptyContainerSlot(tile, TileStill.SLOT_RESOURCE, RESOURCE_X, RESOURCE_Y));
        addSlot(new OutputSlot(tile, TileStill.SLOT_PRODUCT, PRODUCT_X, PRODUCT_Y));
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

    public int getResourceAmountMb() {
        return tankData.get(0);
    }

    public int getResourceFluidType() {
        return tankData.get(1);
    }

    public int getProductAmountMb() {
        return tankData.get(2);
    }

    public int getProductFluidType() {
        return tankData.get(3);
    }

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileStill.TANK_CAPACITY);
    }

    @Override
    public void broadcastChanges() {
        syncTank(tankData, 0, 1, tile.getResourceTank());
        syncTank(tankData, 2, 3, tile.getProductTank());
        super.broadcastChanges();
    }

    private static void syncTank(SimpleContainerData data, int amountIndex, int typeIndex, com.leon1236.reforestry.core.fluids.FilteredFluidStorage tank) {
        data.set(amountIndex, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        data.set(typeIndex, fluidTypeOf(tank.getResource().getFluid()));
    }

    private static int fluidTypeOf(net.minecraft.world.level.material.Fluid fluid) {
        if (ForestryFluids.BIOMASS.is(fluid)) {
            return FLUID_BIOMASS;
        }
        if (ForestryFluids.BIO_ETHANOL.is(fluid)) {
            return FLUID_BIO_ETHANOL;
        }
        return FLUID_NONE;
    }

    private static final class FilledContainerSlot extends Slot {
        private final TileStill tile;
        private final int slotIndex;

        FilledContainerSlot(TileStill tile, int index, int x, int y) {
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
        private final TileStill tile;
        private final int slotIndex;

        EmptyContainerSlot(TileStill tile, int index, int x, int y) {
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
        OutputSlot(TileStill tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
