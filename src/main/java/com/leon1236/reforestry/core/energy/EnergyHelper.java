package com.leon1236.reforestry.core.energy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.core.tiles.IPowerHandler;

public final class EnergyHelper {
    private EnergyHelper() {
    }

    public static boolean consumeEnergyToDoWork(SimpleEnergyStorage energyStorage, int ticksPerWorkCycle, int energyPerWorkCycle) {
        if (energyPerWorkCycle == 0) {
            return true;
        }

        long energyPerCycle = (long) Math.ceil(energyPerWorkCycle / (double) ticksPerWorkCycle);
        if (energyStorage.amount < energyPerCycle) {
            return false;
        }

        energyStorage.amount -= energyPerCycle;
        return true;
    }

    public static <T extends BlockEntity & IPowerHandler> void registerSided(BlockEntityType<T> type) {
        EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyManager(), type);
    }
}
