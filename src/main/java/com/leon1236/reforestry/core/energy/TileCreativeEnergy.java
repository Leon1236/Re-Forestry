package com.leon1236.reforestry.core.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.InfiniteEnergyStorage;

import com.leon1236.reforestry.core.features.CoreTiles;

public class TileCreativeEnergy extends BlockEntity {
    private static final long PUSH_RATE_PER_TICK = 1000;

    public TileCreativeEnergy(BlockPos pos, BlockState state) {
        super(CoreTiles.CREATIVE_ENERGY.type(), pos, state);
    }

    public EnergyStorage getEnergyStorage() {
        return InfiniteEnergyStorage.INSTANCE;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileCreativeEnergy tile) {
        for (Direction direction : Direction.values()) {
            EnergyStorage target = EnergyStorage.SIDED.find(level, pos.relative(direction), direction.getOpposite());
            if (target != null) {
                EnergyStorageUtil.move(InfiniteEnergyStorage.INSTANCE, target, PUSH_RATE_PER_TICK, null);
            }
        }
    }
}
