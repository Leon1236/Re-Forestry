package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerEnergy;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileBottler;

public class ContainerBottler extends ContainerMachine<TileBottler> implements IContainerLiquidTanks, IContainerEnergy {
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

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerBottler(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileBottler.class));
    }

    public ContainerBottler(int containerId, Inventory playerInventory, TileBottler tile) {
        super(FactoryMenuTypes.BOTTLER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getRecipeData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tile.getEnergyData());
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

    public int getResourceFluidId() {
        return tankData.get(1);
    }

    public Fluid getResourceFluid() {
        return BuiltInRegistries.FLUID.byId(getResourceFluidId());
    }

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileBottler.TANK_CAPACITY);
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

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (super.clickMenuButton(player, id)) {
            return true;
        }
        if (getTank(id) == null || !PipetteTankHelper.canHandleClick(player.containerMenu.getCarried())) {
            return false;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            handlePipetteClick(id, serverPlayer);
        }
        return true;
    }

    @Override
    public void handlePipetteClick(int slot, ServerPlayer player) {
        SingleFluidStorage tank = getTank(slot);
        if (tank != null) {
            PipetteTankHelper.handlePipetteClick(tank, player, this);
        }
    }

    @Override
    public SingleFluidStorage getTank(int slot) {
        return slot == 0 ? tile.getResourceTank() : null;
    }

    @Override
    public void broadcastChanges() {
        var tank = tile.getResourceTank();
        tankData.set(0, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        Fluid fluid = tank.getResource().getFluid();
        tankData.set(1, BuiltInRegistries.FLUID.getId(fluid));
        super.broadcastChanges();
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
        public void onTake(Player player, ItemStack stack) {
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
