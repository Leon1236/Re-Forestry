package com.leon1236.reforestry.factory.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.gui.ContainerMachine;
import com.leon1236.reforestry.factory.features.FactoryMenuTypes;
import com.leon1236.reforestry.factory.tiles.TileFabricator;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import com.leon1236.reforestry.api.core.IToolPipette;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerFabricator extends ContainerMachine<TileFabricator> implements IContainerLiquidTanks {
    private static final int SLOT_GAP = 18;
    private static final int STORAGE_X = 8;
    private static final int STORAGE_Y = 84;
    private static final int METAL_X = 26;
    private static final int METAL_Y = 21;
    private static final int PLAN_X = 139;
    private static final int PLAN_Y = 17;
    private static final int RESULT_X = 139;
    private static final int RESULT_Y = 53;
    private static final int CRAFT_X = 67;
    private static final int CRAFT_Y = 17;
    private static final int INVENTORY_Y = 129;

    private static final int FLUID_NONE = 0;
    private static final int FLUID_GLASS = 1;

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerFabricator(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileFabricator.class));
    }

    public ContainerFabricator(int containerId, Inventory playerInventory, TileFabricator tile) {
        super(FactoryMenuTypes.FABRICATOR.type(), containerId, playerInventory, tile, INVENTORY_Y);
        addDataSlots(tile.getMachineData());
        addDataSlots(tile.getErrorData());
        addDataSlots(tankData);
    }

    @Override
    protected void addMachineSlots(TileFabricator tile) {
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 9; column++) {
                int index = column + row * 9;
                addSlot(new FilteredSlot(tile, TileFabricator.SLOT_INVENTORY_1 + index,
                        STORAGE_X + column * SLOT_GAP, STORAGE_Y + row * SLOT_GAP));
            }
        }

        addSlot(new FilteredSlot(tile, TileFabricator.SLOT_METAL, METAL_X, METAL_Y));
        addSlot(new FilteredSlot(tile, TileFabricator.SLOT_PLAN, PLAN_X, PLAN_Y));
        addSlot(new OutputSlot(tile, TileFabricator.SLOT_RESULT, RESULT_X, RESULT_Y));

        Container craftingInventory = tile.getCraftingInventory();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int index = column + row * 3;
                addSlot(new CraftMatrixSlot(craftingInventory, index, CRAFT_X + column * SLOT_GAP, CRAFT_Y + row * SLOT_GAP));
            }
        }
    }

    public int getHeat() {
        return tile.getMachineData().get(0);
    }

    public int getMeltingPoint() {
        return tile.getMachineData().get(1);
    }

    public int getHeatScaled(int pixels) {
        return getHeat() * pixels / TileFabricator.MAX_HEAT;
    }

    public int getMeltingPointScaled(int pixels) {
        int point = getMeltingPoint();
        if (point <= 0) {
            return 0;
        }
        return point * pixels / TileFabricator.MAX_HEAT;
    }

    public int getErrorCount() {
        return tile.getErrorData().get(0);
    }

    public short getErrorId(int index) {
        return (short) tile.getErrorData().get(index + 1);
    }

    public int getMoltenAmountMb() {
        return tankData.get(0);
    }

    public int getMoltenFluidType() {
        return tankData.get(1);
    }

    public int getTankCapacityMb() {
        return (int) FluidUnits.dropletsToMb(TileFabricator.TANK_CAPACITY);
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
        return slot == 0 ? tile.getMoltenTank() : null;
    }

    @Override
    public void broadcastChanges() {
        syncTank(tankData, 0, 1, tile.getMoltenTank());
        super.broadcastChanges();
    }

    private static void syncTank(SimpleContainerData data, int amountIndex, int typeIndex,
            com.leon1236.reforestry.core.fluids.FilteredFluidStorage tank) {
        data.set(amountIndex, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        data.set(typeIndex, fluidTypeOf(tank.getResource().getFluid()));
    }

    private static int fluidTypeOf(net.minecraft.world.level.material.Fluid fluid) {
        if (ForestryFluids.GLASS.is(fluid)) {
            return FLUID_GLASS;
        }
        return FLUID_NONE;
    }

    private static final class FilteredSlot extends Slot {
        private final TileFabricator tile;
        private final int slotIndex;

        FilteredSlot(TileFabricator tile, int index, int x, int y) {
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
        OutputSlot(TileFabricator tile, int index, int x, int y) {
            super(tile, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }
    }

    private static final class CraftMatrixSlot extends Slot {
        CraftMatrixSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }
    }
}
