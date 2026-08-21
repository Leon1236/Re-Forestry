package com.leon1236.reforestry.core.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
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
    private int syncedProgress;
    private int syncedEnergyStored;
    private int syncedEnergyCapacity;
    private int syncedEnergyMaxReceive;
    private int syncedEnergyUsage;
    protected float speedMultiplier = 1.0f;
    protected float powerMultiplier = 1.0f;
    protected double outputMultiplier = 1.0;

    private final ContainerData progressData = new ContainerData() {
        @Override
        public int get(int index) {
            Level level = getLevel();
            if (level != null && level.isClientSide()) {
                return syncedProgress;
            }
            return getProgressScaled(100);
        }

        @Override
        public void set(int index, int value) {
            syncedProgress = value;
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    private final ContainerData energyData = new ContainerData() {
        @Override
        public int get(int index) {
            Level level = getLevel();
            boolean client = level != null && level.isClientSide();
            return switch (index) {
                case 0 -> client ? syncedEnergyStored : (int) energyStorage.amount;
                case 1 -> client ? syncedEnergyCapacity : (int) energyStorage.capacity;
                case 2 -> client ? syncedEnergyMaxReceive : (int) energyStorage.maxInsert;
                case 3 -> client ? syncedEnergyUsage : getCurrentEnergyUsage();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> syncedEnergyStored = value;
                case 1 -> syncedEnergyCapacity = value;
                case 2 -> syncedEnergyMaxReceive = value;
                case 3 -> syncedEnergyUsage = value;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

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

    public ContainerData getProgressData() {
        return this.progressData;
    }

    public ContainerData getEnergyData() {
        return this.energyData;
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

    public int getCurrentEnergyUsage() {
        if (!hasWork()) {
            return 0;
        }
        int ticks = getTicksPerWorkCycle();
        if (ticks <= 0) {
            return 0;
        }
        return (int) Math.ceil(getEnergyPerWorkCycle() / (double) ticks);
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

    public void doWork(boolean advanceTick) {
        if (advanceTick) {
            advanceTicks();
        }
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
            this.noPowerTime = 0;
            errorLogic.setCondition(false, ForestryError.NO_POWER);
            return;
        }

        int ticksPerWorkCycle = getTicksPerWorkCycle();
        int energyPerWorkCycle = getEnergyPerWorkCycle();

        if (ticksPerWorkCycle <= 0) {
            return;
        }

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
