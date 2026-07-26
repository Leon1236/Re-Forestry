package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileMoistener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import com.leon1236.reforestry.api.core.IToolPipette;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerMoistener extends ContainerMachine<TileMoistener> implements IContainerLiquidTanks {
    private static final int INVENTORY_Y = 84;

    private final net.minecraft.world.inventory.SimpleContainerData tankData = new net.minecraft.world.inventory.SimpleContainerData(2);

    public ContainerMoistener(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileMoistener.class));
    }

    public ContainerMoistener(int containerId, Inventory playerInventory, TileMoistener tile) {
        super(FactoryMenuTypes.MOISTENER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getMachineData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileMoistener tile) {
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 3; column++) {
                addSlot(new FilteredSlot(tile, column + row * 3, 39 + column * 18, 16 + row * 18));
            }
        }
        for (int column = 0; column < TileMoistener.SLOT_RESERVOIR_COUNT; column++) {
            addSlot(new FilteredSlot(tile, TileMoistener.SLOT_RESERVOIR_1 + column, 39 + column * 18, 58));
        }
        addSlot(new WorkingSlot(tile, TileMoistener.SLOT_WORKING, 105, 37));
        addSlot(new FilteredSlot(tile, TileMoistener.SLOT_PRODUCT, 143, 55));
        addSlot(new ResourceSlot(tile, TileMoistener.SLOT_RESOURCE, 143, 19));
    }

    public int getBurnTime() {
        return tile.getMachineData().get(0);
    }

    public int getTotalTime() {
        return tile.getMachineData().get(1);
    }

    public int getProductionTime() {
        return tile.getMachineData().get(2);
    }

    public int getTimePerItem() {
        return tile.getMachineData().get(3);
    }

    public int getConsumptionProgressScaled(int pixels) {
        int total = getTotalTime();
        if (total == 0) {
            return 0;
        }
        return getBurnTime() * pixels / total;
    }

    public int getProductionProgressScaled(int pixels) {
        int total = getTimePerItem();
        if (total == 0) {
            return 0;
        }
        return getProductionTime() * pixels / total;
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

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileMoistener.TANK_CAPACITY);
    }


    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (getTank(id) == null || !(player.containerMenu.getCarried().getItem() instanceof IToolPipette)) {
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
        tankData.set(0, (int) FluidUnits.dropletsToMb(tile.getResourceTank().getAmount()));
        super.broadcastChanges();
    }

    private static class FilteredSlot extends Slot {
        protected final TileMoistener tile;
        private final int slotIndex;

        FilteredSlot(TileMoistener tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(slotIndex, stack);
        }
    }

    private static final class WorkingSlot extends Slot {
        WorkingSlot(TileMoistener tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }

    private static final class ResourceSlot extends FilteredSlot {
        ResourceSlot(TileMoistener tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public void setChanged() {
            super.setChanged();
            tile.checkRecipe();
        }
    }
}
