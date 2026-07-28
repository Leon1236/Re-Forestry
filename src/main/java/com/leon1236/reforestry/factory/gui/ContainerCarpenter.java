package com.leon1236.reforestry.factory.gui;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.core.gui.MachineGuiRecipes;
import com.leon1236.reforestry.core.gui.PhantomSlotHelper;
import com.leon1236.reforestry.core.gui.SlotGhostCrafting;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileCarpenter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import com.leon1236.reforestry.api.gui.IContainerRecipeBook;
import com.leon1236.reforestry.api.gui.MachineRecipeEntry;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerCarpenter extends ContainerMachine<TileCarpenter> implements IContainerLiquidTanks, IContainerRecipeBook {
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
    public List<MachineRecipeEntry> getGuiRecipes() {
        Level level = tile.getLevel();
        if (level == null) {
            return List.of();
        }
        return MachineGuiRecipes.carpenter(level);
    }

    @Override
    public boolean selectRecipe(int index, Player player) {
        List<MachineRecipeEntry> recipes = getGuiRecipes();
        if (index < 0 || index >= recipes.size()) {
            return false;
        }
        MachineRecipeEntry entry = recipes.get(index);
        if (!entry.hasPattern()) {
            return false;
        }
        long timeMs = System.currentTimeMillis();
        for (int i = 0; i < 9; i++) {
            ItemStack stack = entry.patternStack(i, timeMs);
            tile.setItem(TileCarpenter.SLOT_CRAFTING_1 + i,
                    stack.isEmpty() ? ItemStack.EMPTY : stack);
        }
        tile.checkRecipe();
        return true;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (super.clickMenuButton(player, id)) {
            return true;
        }
        if (IContainerRecipeBook.isRecipeButton(id)) {
            return selectRecipe(IContainerRecipeBook.recipeIndex(id), player);
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
    public void clicked(int slotIndex, int button, ContainerInput input, Player player) {
        if (slotIndex >= 0 && slotIndex < slots.size()) {
            Slot slot = slots.get(slotIndex);
            if (slot instanceof SlotGhostCrafting ghost) {
                PhantomSlotHelper.clickGhost(ghost, button, input, player);
                return;
            }
            if (slot instanceof PreviewSlot) {
                return;
            }
        }
        super.clicked(slotIndex, button, input, player);
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

    private static final class CraftMatrixSlot extends SlotGhostCrafting {
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
        public boolean mayPickup(Player player) {
            return false;
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
