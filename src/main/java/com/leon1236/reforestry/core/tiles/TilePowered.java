package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import com.leon1236.reforestry.api.circuits.IMachineUpgradable;
import com.leon1236.reforestry.api.core.ForestryError;
import com.leon1236.reforestry.api.core.IErrorLogic;
import com.leon1236.reforestry.core.energy.EnergyHelper;

public abstract class TilePowered extends TileBase implements IPowerHandler, IMachineUpgradable {
    protected static final int WORK_TICK_INTERVAL = 5;

    private final SimpleEnergyStorage energyStorage;

    private int ticksPerWorkCycle = 4;
    private int energyPerWorkCycle;
    private int workCounter;
    private int noPowerTime;
    protected float speedMultiplier = 1.0f;
    protected float powerMultiplier = 1.0f;
    protected double outputMultiplier = 1.0;

    protected TilePowered(BlockEntityType<?> type, BlockPos pos, BlockState state, long capacity, long maxReceive) {
        super(type, pos, state);
        this.energyStorage = new SimpleEnergyStorage(capacity, maxReceive, 0) {
            @Override
            protected void onFinalCommit() {
                setChanged();
            }
        };
    }

    @Override
    public SimpleEnergyStorage getEnergyManager() {
        return this.energyStorage;
    }

    public int getWorkCounter() {
        return this.workCounter;
    }

    public void setTicksPerWorkCycle(int ticksPerWorkCycle) {
        this.ticksPerWorkCycle = ticksPerWorkCycle;
        this.workCounter = 0;
    }

    public int getTicksPerWorkCycle() {
        return Math.round(this.ticksPerWorkCycle / this.speedMultiplier);
    }

    public void setEnergyPerWorkCycle(int energyPerWorkCycle) {
        this.energyPerWorkCycle = energyPerWorkCycle;
    }

    public int getEnergyPerWorkCycle() {
        return Math.round(this.energyPerWorkCycle * this.powerMultiplier);
    }

    protected double getOutputMultiplier() {
        return this.outputMultiplier;
    }

    @Override
    public void applyMachineUpgrade(double speedChange, double powerChange, double outputChange) {
        this.speedMultiplier += speedChange;
        this.powerMultiplier += powerChange;
        this.outputMultiplier *= outputChange;
        this.workCounter = 0;
    }

    @Override
    public void removeMachineUpgrade(double speedChange, double powerChange, double outputChange) {
        this.speedMultiplier -= speedChange;
        this.powerMultiplier -= powerChange;
        this.outputMultiplier /= outputChange;
        this.workCounter = 0;
    }

    public abstract boolean hasWork();

    protected abstract boolean workCycle();

    public void doWork() {
        if (!updateOnInterval(WORK_TICK_INTERVAL)) {
            return;
        }

        IErrorLogic errorLogic = getErrorLogic();

        boolean disabled = isRedstoneActivated();
        errorLogic.setCondition(disabled, ForestryError.DISABLED_BY_REDSTONE);
        if (disabled) {
            return;
        }

        if (!hasWork()) {
            return;
        }

        int ticksPerWorkCycle = getTicksPerWorkCycle();
        int energyPerWorkCycle = getEnergyPerWorkCycle();

        if (this.workCounter < ticksPerWorkCycle) {
            boolean consumedEnergy = EnergyHelper.consumeEnergyToDoWork(this.energyStorage, ticksPerWorkCycle, energyPerWorkCycle);
            if (consumedEnergy) {
                errorLogic.setCondition(false, ForestryError.NO_POWER);
                this.workCounter++;
                this.noPowerTime = 0;
                setChanged();
            } else {
                this.noPowerTime++;
                if (this.noPowerTime > 4) {
                    errorLogic.setCondition(true, ForestryError.NO_POWER);
                }
            }
        }

        if (this.workCounter >= ticksPerWorkCycle) {
            if (workCycle()) {
                this.workCounter = 0;
            }
        }
    }

    public int getProgressScaled(int pixels) {
        int ticksPerWorkCycle = getTicksPerWorkCycle();
        if (ticksPerWorkCycle == 0) {
            return 0;
        }
        return this.workCounter * pixels / ticksPerWorkCycle;
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.energyStorage.amount = input.getLongOr("Energy", 0L);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putLong("Energy", this.energyStorage.amount);
    }
}
