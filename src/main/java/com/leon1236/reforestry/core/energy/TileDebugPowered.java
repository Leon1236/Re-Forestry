package com.leon1236.reforestry.core.energy;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.features.CoreTiles;
import com.leon1236.reforestry.core.tiles.TilePowered;

public class TileDebugPowered extends TilePowered {
    private static final long CAPACITY = 10_000;
    private static final long MAX_RECEIVE = 200;
    private static final int ENERGY_PER_WORK_CYCLE = 100;

    public TileDebugPowered(BlockPos pos, BlockState state) {
        super(CoreTiles.DEBUG_POWERED.type(), pos, state, CAPACITY, MAX_RECEIVE);
        setEnergyPerWorkCycle(ENERGY_PER_WORK_CYCLE);
    }

    @Override
    public boolean hasWork() {
        return true;
    }

    @Override
    protected boolean workCycle() {
        return true;
    }

    @Override
    protected boolean hasGui() {
        return false;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return null;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileDebugPowered tile) {
        tile.doWork();
    }
}
