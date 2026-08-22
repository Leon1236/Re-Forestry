package com.leon1236.reforestry.apiculture.gui;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.material.Fluid;

import com.leon1236.reforestry.apiculture.features.ApicultureMenuTypes;
import com.leon1236.reforestry.apiculture.multiblock.TileAlvearyHygroregulator;
import com.leon1236.reforestry.core.fluids.FluidUnits;
import com.leon1236.reforestry.core.fluids.PipetteTankHelper;
import com.leon1236.reforestry.core.gui.IContainerLiquidTanks;

public class ContainerAlvearyHygroregulator extends ContainerAlvearyPart<TileAlvearyHygroregulator> implements IContainerLiquidTanks {
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

    public Fluid getResourceFluid() {
        return BuiltInRegistries.FLUID.byId(tankData.get(1));
    }

    public int getCapacityInMillibuckets() {
        return (int) FluidUnits.dropletsToMb(TileAlvearyHygroregulator.TANK_CAPACITY);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
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
        return slot == 0 ? tile.getTank() : null;
    }

    @Override
    public void broadcastChanges() {
        var tank = tile.getTank();
        tankData.set(0, (int) FluidUnits.dropletsToMb(tank.getAmount()));
        tankData.set(1, BuiltInRegistries.FLUID.getId(tank.getResource().getFluid()));
        super.broadcastChanges();
    }
}
