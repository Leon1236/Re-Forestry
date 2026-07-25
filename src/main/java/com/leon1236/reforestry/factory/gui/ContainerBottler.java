package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileBottler;

public class ContainerBottler extends ContainerMachine<TileBottler> {
    private static final int FULL_INPUT_X = 18;
    private static final int FULL_INPUT_Y = 7;
    private static final int EMPTYING_X = 18;
    private static final int EMPTYING_Y = 35;
    private static final int EMPTY_OUTPUT_X = 18;
    private static final int EMPTY_OUTPUT_Y = 63;
    private static final int EMPTY_INPUT_X = 142;
    private static final int EMPTY_INPUT_Y = 7;
    private static final int FILLING_X = 142;
    private static final int FILLING_Y = 35;
    private static final int FULL_OUTPUT_X = 142;
    private static final int FULL_OUTPUT_Y = 63;
    private static final int INVENTORY_Y = 84;

    private static final int FLUID_NONE = 0;
    private static final int FLUID_BIOMASS = 1;
    private static final int FLUID_WATER = 2;
    private static final int FLUID_OTHER = 3;

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerBottler(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileBottler.class));
    }

    public ContainerBottler(int containerId, Inventory playerInventory, TileBottler tile) {
        super(FactoryMenuTypes.BOTTLER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getRecipeData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileBottler tile) {
        addSlot(new InputSlot(tile, TileBottler.SLOT_INPUT_FULL_CONTAINER, FULL_INPUT_X, FULL_INPUT_Y));
        addSlot(new ProcessingSlot(tile, TileBottler.SLOT_EMPTYING_PROCESSING, EMPTYING_X, EMPTYING_Y));
        addSlot(new OutputSlot(tile, TileBottler.SLOT_OUTPUT_EMPTY_CONTAINER, EMPTY_OUTPUT_X, EMPTY_OUTPUT_Y));
        addSlot(new InputSlot(tile, TileBottler.SLOT_INPUT_EMPTY_CONTAINER, EMPTY_INPUT_X, EMPTY_INPUT_Y));
        addSlot(new ProcessingSlot(tile, TileBottler.SLOT_FILLING_PROCESSING, FILLING_X, FILLING_Y));
        addSlot(new OutputSlot(tile, TileBottler.SLOT_OUTPUT_FULL_CONTAINER, FULL_OUTPUT_X, FULL_OUTPUT_Y));
    }

    public int getProgressPercent() {
        return tile.getProgressData().get(0);
    }

    public boolean isFillRecipe() {
        return tile.getRecipeData().get(0) == 1;
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

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileBottler.TANK_CAPACITY);
    }

    @Override
    public void broadcastChanges() {
        syncTank();
        super.broadcastChanges();
    }

    private void syncTank() {
        var tank = tile.getResourceTank();
        tankData.set(0, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        tankData.set(1, fluidTypeOf(tank.getResource().getFluid()));
    }

    private static int fluidTypeOf(net.minecraft.world.level.material.Fluid fluid) {
        if (ForestryFluids.BIOMASS.is(fluid)) {
            return FLUID_BIOMASS;
        }
        if (fluid == net.minecraft.world.level.material.Fluids.WATER) {
            return FLUID_WATER;
        }
        if (fluid == net.minecraft.world.level.material.Fluids.EMPTY) {
            return FLUID_NONE;
        }
        return FLUID_OTHER;
    }

    private static final class InputSlot extends Slot {
        private final TileBottler tile;
        private final int slotIndex;

        InputSlot(TileBottler tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(slotIndex, stack);
        }
    }

    private static final class ProcessingSlot extends Slot {
        private final TileBottler tile;
        private final int slotIndex;

        ProcessingSlot(TileBottler tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        @Override
        public void onTake(net.minecraft.world.entity.player.Player player, ItemStack stack) {
            super.onTake(player, stack);
            tile.onProcessingSlotTake(slotIndex);
        }
    }

    private static final class OutputSlot extends Slot {
        OutputSlot(TileBottler tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
