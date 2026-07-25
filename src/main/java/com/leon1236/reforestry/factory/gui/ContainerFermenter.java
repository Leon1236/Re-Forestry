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
import com.leon1236.reforestry.factory.tiles.TileFermenter;

public class ContainerFermenter extends ContainerMachine<TileFermenter> {
    private static final int RESOURCE_X = 85;
    private static final int RESOURCE_Y = 23;
    private static final int FUEL_X = 75;
    private static final int FUEL_Y = 57;
    private static final int CAN_INPUT_X = 150;
    private static final int CAN_INPUT_Y = 22;
    private static final int CAN_OUTPUT_X = 150;
    private static final int CAN_OUTPUT_Y = 58;
    private static final int FLUID_INPUT_X = 10;
    private static final int FLUID_INPUT_Y = 40;
    private static final int INVENTORY_Y = 84;

    private static final int FLUID_NONE = 0;
    private static final int FLUID_WATER = 1;
    private static final int FLUID_JUICE = 2;
    private static final int FLUID_HONEY = 3;
    private static final int FLUID_BIOMASS = 4;
    private static final int FLUID_SHORT_MEAD = 5;

    private final SimpleContainerData tankData = new SimpleContainerData(4);

    public ContainerFermenter(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileFermenter.class));
    }

    public ContainerFermenter(int containerId, Inventory playerInventory, TileFermenter tile) {
        super(FactoryMenuTypes.FERMENTER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getMachineData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileFermenter tile) {
        addSlot(new FilteredSlot(tile, TileFermenter.SLOT_RESOURCE, RESOURCE_X, RESOURCE_Y));
        addSlot(new FilteredSlot(tile, TileFermenter.SLOT_FUEL, FUEL_X, FUEL_Y));
        addSlot(new OutputSlot(tile, TileFermenter.SLOT_CAN_OUTPUT, CAN_OUTPUT_X, CAN_OUTPUT_Y));
        addSlot(new FilteredSlot(tile, TileFermenter.SLOT_CAN_INPUT, CAN_INPUT_X, CAN_INPUT_Y));
        addSlot(new FilteredSlot(tile, TileFermenter.SLOT_INPUT, FLUID_INPUT_X, FLUID_INPUT_Y));
    }

    public int getFuelBurnTime() {
        return tile.getMachineData().get(0);
    }

    public int getFuelTotalTime() {
        return tile.getMachineData().get(1);
    }

    public int getFermentationTime() {
        return tile.getMachineData().get(2);
    }

    public int getFermentationTotalTime() {
        return tile.getMachineData().get(3);
    }

    public int getFuelProgressScaled(int pixels) {
        int total = getFuelTotalTime();
        if (total == 0) {
            return 0;
        }
        return getFuelBurnTime() * pixels / total;
    }

    public int getFermentationProgressScaled(int pixels) {
        int total = getFermentationTotalTime();
        if (total == 0) {
            return 0;
        }
        return getFermentationTime() * pixels / total;
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
        return (int) FluidUnits.dropletsToMb(TileFermenter.TANK_CAPACITY);
    }

    @Override
    public void broadcastChanges() {
        syncTank(tankData, 0, 1, tile.getResourceTank());
        syncTank(tankData, 2, 3, tile.getProductTank());
        super.broadcastChanges();
    }

    private static void syncTank(SimpleContainerData data, int amountIndex, int typeIndex,
            com.leon1236.reforestry.core.fluids.FilteredFluidStorage tank) {
        data.set(amountIndex, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        data.set(typeIndex, fluidTypeOf(tank.getResource().getFluid()));
    }

    private static int fluidTypeOf(net.minecraft.world.level.material.Fluid fluid) {
        if (ForestryFluids.JUICE.is(fluid)) {
            return FLUID_JUICE;
        }
        if (ForestryFluids.HONEY.is(fluid)) {
            return FLUID_HONEY;
        }
        if (ForestryFluids.BIOMASS.is(fluid)) {
            return FLUID_BIOMASS;
        }
        if (ForestryFluids.SHORT_MEAD.is(fluid)) {
            return FLUID_SHORT_MEAD;
        }
        if (fluid == net.minecraft.world.level.material.Fluids.WATER
                || fluid == net.minecraft.world.level.material.Fluids.FLOWING_WATER) {
            return FLUID_WATER;
        }
        return FLUID_NONE;
    }

    private static final class FilteredSlot extends Slot {
        private final TileFermenter tile;
        private final int slotIndex;

        FilteredSlot(TileFermenter tile, int index, int x, int y) {
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
        OutputSlot(TileFermenter tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
