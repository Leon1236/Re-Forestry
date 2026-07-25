package com.leon1236.reforestry.core.fluids;

import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

public class FilteredFluidStorage extends SingleFluidStorage {
    private final long capacity;
    private final Predicate<FluidVariant> filter;
    private final Runnable onChange;

    public FilteredFluidStorage(long capacity, Predicate<FluidVariant> filter, Runnable onChange) {
        this.capacity = capacity;
        this.filter = filter;
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
    protected void onFinalCommit() {
        this.onChange.run();
    }

    public long getTankCapacity() {
        return this.capacity;
    }

    public static Predicate<FluidVariant> any() {
        return variant -> true;
    }

    public static Predicate<FluidVariant> only(Fluid... fluids) {
        Set<Fluid> allowed = Set.of(fluids);
        return variant -> allowed.contains(variant.getFluid());
    }
}
