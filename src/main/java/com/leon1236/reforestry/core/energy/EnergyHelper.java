package com.leon1236.reforestry.core.energy;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.core.ISidedAccess;
import com.leon1236.reforestry.core.access.AccessStorageHelper;
import com.leon1236.reforestry.core.tiles.IEnginePowerHandler;
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
        EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> {
            if (tile instanceof ISidedAccess access) {
                return AccessStorageHelper.wrapEnergy(access, direction, tile.getEnergyManager());
            }
            return tile.getEnergyManager();
        }, type);
    }

    public static long sendEnergy(SimpleEnergyStorage energyStorage, Level level, BlockPos targetPos, Direction side) {
        return sendEnergy(energyStorage, level, targetPos, side, Long.MAX_VALUE, false);
    }

    public static long sendEnergy(
            SimpleEnergyStorage energyStorage,
            Level level,
            BlockPos targetPos,
            Direction side,
            long amount,
            boolean simulate
    ) {
        if (!level.isLoaded(targetPos)) {
            return 0;
        }

        BlockEntity tile = level.getBlockEntity(targetPos);
        if (tile instanceof IEnginePowerHandler engine) {
            return sendEnergyToEngine(energyStorage, engine, amount, simulate);
        }

        EnergyStorage target = EnergyStorage.SIDED.find(level, targetPos, side);
        if (target == null) {
            return 0;
        }

        try (Transaction transaction = Transaction.openOuter()) {
            long moved = EnergyStorageUtil.move(energyStorage, target, amount, transaction);
            if (!simulate) {
                transaction.commit();
            }
            return moved;
        }
    }

    private static long sendEnergyToEngine(
            SimpleEnergyStorage energyStorage,
            IEnginePowerHandler engine,
            long amount,
            boolean simulate
    ) {
        long extractable;
        try (Transaction probe = Transaction.openOuter()) {
            extractable = energyStorage.extract(amount, probe);
        }
        if (extractable <= 0) {
            return 0;
        }

        long received = engine.forceReceiveEnergy(extractable, simulate);
        if (!simulate && received > 0) {
            try (Transaction transaction = Transaction.openOuter()) {
                energyStorage.extract(received, transaction);
                transaction.commit();
            }
        }
        return received;
    }

    public static boolean canSendEnergy(SimpleEnergyStorage energyStorage, Level level, BlockPos targetPos, Direction side) {
        return sendEnergy(energyStorage, level, targetPos, side, Long.MAX_VALUE, true) > 0;
    }

    public static boolean isEnergyReceiverOrEngine(Direction side, @Nullable BlockEntity tile) {
        if (tile == null) {
            return false;
        }
        if (tile instanceof IEnginePowerHandler) {
            return true;
        }

        Level level = tile.getLevel();
        if (level == null || !level.isLoaded(tile.getBlockPos())) {
            return false;
        }

        EnergyStorage storage = EnergyStorage.SIDED.find(level, tile.getBlockPos(), side);
        return storage != null && storage.supportsInsertion();
    }
}
