package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import com.leon1236.reforestry.api.core.IToolPipette;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerCarpenter extends ContainerMachine<TileCarpenter> implements IContainerLiquidTanks {
    private static final int CRAFT_X = 10;
    private static final int CRAFT_Y = 20;
    private static final int CRAFT_GAP = 18;
    private static final int BOX_X = 83;
    private static final int BOX_Y = 20;
    private static final int PREVIEW_X = 80;
    private static final int PREVIEW_Y = 51;
    private static final int CAN_INPUT_X = 120;
    private static final int CAN_INPUT_Y = 20;
    private static final int PRODUCT_X = 120;
    private static final int PRODUCT_Y = 56;
    private static final int STORAGE_X = 8;
    private static final int STORAGE_Y = 90;
    private static final int INVENTORY_Y = 136;

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerCarpenter(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileCarpenter.class));
    }

    public ContainerCarpenter(int containerId, Inventory playerInventory, TileCarpenter tile) {
        super(FactoryMenuTypes.CARPENTER.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getProgressData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileCarpenter tile) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int index = column + row * 3;
                addSlot(new CraftMatrixSlot(tile, TileCarpenter.SLOT_CRAFTING_1 + index,
                        CRAFT_X + column * CRAFT_GAP, CRAFT_Y + row * CRAFT_GAP));
            }
        }
        addSlot(new InputSlot(tile, TileCarpenter.SLOT_BOX, BOX_X, BOX_Y));
        addSlot(new PreviewSlot(tile.getCraftPreviewInventory(), 0, PREVIEW_X, PREVIEW_Y));
        addSlot(new InputSlot(tile, TileCarpenter.SLOT_CAN_INPUT, CAN_INPUT_X, CAN_INPUT_Y));
        addSlot(new OutputSlot(tile, TileCarpenter.SLOT_PRODUCT, PRODUCT_X, PRODUCT_Y));
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 9; column++) {
                int index = column + row * 9;
                addSlot(new InputSlot(tile, TileCarpenter.SLOT_STORAGE_1 + index,
                        STORAGE_X + column * CRAFT_GAP, STORAGE_Y + row * CRAFT_GAP));
            }
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

    public int getResourceAmountMb() {
        return tankData.get(0);
    }

    public int getResourceFluidId() {
        return tankData.get(1);
    }

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileCarpenter.TANK_CAPACITY);
    }

    public Fluid getResourceFluid() {
        return BuiltInRegistries.FLUID.byId(getResourceFluidId());
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
        var tank = tile.getResourceTank();
        tankData.set(0, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        Fluid fluid = tank.getResource().getFluid();
        tankData.set(1, BuiltInRegistries.FLUID.getId(fluid));
        super.broadcastChanges();
    }

    private static final class CraftMatrixSlot extends Slot {
        private final TileCarpenter tile;

        CraftMatrixSlot(TileCarpenter tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
        }

        @Override
        public void setChanged() {
            super.setChanged();
            tile.checkRecipe();
        }
    }

    private static final class InputSlot extends Slot {
        private final TileCarpenter tile;
        private final int slotIndex;

        InputSlot(TileCarpenter tile, int index, int x, int y) {
            super(tile, index, x, y);
            this.tile = tile;
            this.slotIndex = index;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return tile.canPlaceItem(slotIndex, stack);
        }

        @Override
        public void setChanged() {
            super.setChanged();
            if (slotIndex == TileCarpenter.SLOT_BOX) {
                tile.checkRecipe();
            }
        }
    }

    private static final class PreviewSlot extends Slot {
        PreviewSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }

    private static final class OutputSlot extends Slot {
        OutputSlot(TileCarpenter tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }
}
