package com.leon1236.reforestry.apiculture.gui;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHygroregulator;

public class ContainerAlvearyHygroregulator extends ContainerAlvearyPart<TileAlvearyHygroregulator> {
    public static final int FLUID_NONE = 0;
    public static final int FLUID_WATER = 1;
    public static final int FLUID_LAVA = 2;

    private final SimpleContainerData tankData = new SimpleContainerData(2);

    public ContainerAlvearyHygroregulator(int containerId, Inventory playerInventory, BlockPos pos) {
        this(containerId, playerInventory, resolveTile(playerInventory, pos, TileAlvearyHygroregulator.class));
    }

    public ContainerAlvearyHygroregulator(int containerId, Inventory playerInventory, TileAlvearyHygroregulator tile) {
        super(ApicultureMenuTypes.ALVEARY_HYGROREGULATOR.type(), containerId, playerInventory, tile, 84);
        addDataSlots(tankData);
    }

    @Override
    protected void addPartSlots(TileAlvearyHygroregulator tile) {
        addSlot(new Slot(tile, TileAlvearyHygroregulator.SLOT_INPUT, 56, 38));
    }

    public int getFluidAmountInMillibuckets() {
        return tankData.get(0);
    }

    public int getFluidType() {
        return tankData.get(1);
    }

    public int getCapacityInMillibuckets() {
        return (int) (TileAlvearyHygroregulator.TANK_CAPACITY / (FluidConstants.BUCKET / 1000));
    }

    @Override
    public void broadcastChanges() {
        tankData.set(0, (int) (tile.getTank().getAmount() / (FluidConstants.BUCKET / 1000)));
        tankData.set(1, fluidTypeOf(tile.getTank().getResource().getFluid()));
        super.broadcastChanges();
    }

    private static int fluidTypeOf(Fluid fluid) {
        if (fluid == Fluids.WATER) {
            return FLUID_WATER;
        }
        if (fluid == Fluids.LAVA) {
            return FLUID_LAVA;
        }
        return FLUID_NONE;
    }
}
