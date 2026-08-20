package com.leon1236.reforestry.core.fluids;

import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

public class FilteredFluidStorage extends SingleFluidStorage {
    private long capacity;
    private final Predicate<FluidVariant> filter;
    private final boolean canExtract;
    private final Runnable onChange;

    public FilteredFluidStorage(long capacity, Predicate<FluidVariant> filter, Runnable onChange) {
        this(capacity, filter, true, onChange);
    }

    public FilteredFluidStorage(long capacity, Predicate<FluidVariant> filter, boolean canExtract, Runnable onChange) {
        this.capacity = capacity;
        this.filter = filter;
        this.canExtract = canExtract;
        this.onChange = onChange;
    }

    @Override
    protected long getCapacity(FluidVariant variant) {
        return this.capacity;
    }

    @Override
    protected boolean canInsert(FluidVariant variant) {
        return this.filter.test(variant);
    }

    @Override
    protected boolean canExtract(FluidVariant variant) {
        return this.canExtract;
    }

    @Override
    protected void onFinalCommit() {
        this.onChange.run();
    }

    public long getTankCapacity() {
        return this.capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = Math.max(0, capacity);
    }

    public long drainInternal(long maxAmount) {
        if (this.variant.isBlank() || this.amount <= 0 || maxAmount <= 0) {
            return 0;
        }
        long drained = Math.min(this.amount, maxAmount);
        this.amount -= drained;
        if (this.amount <= 0) {
            this.amount = 0;
            this.variant = FluidVariant.blank();
        }
        this.onChange.run();
        return drained;
    }

    public void setFluid(FluidVariant variant, long amount) {
        if (variant.isBlank() || amount <= 0) {
            this.variant = FluidVariant.blank();
            this.amount = 0;
        } else {
            this.variant = variant;
            this.amount = amount;
        }
        this.onChange.run();
    }

    public static Predicate<FluidVariant> any() {
        return variant -> true;
    }

    public static Predicate<FluidVariant> only(Fluid... fluids) {
        Set<Fluid> allowed = Set.of(fluids);
        return variant -> allowed.contains(variant.getFluid());
    }
}
