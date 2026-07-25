package com.leon1236.reforestry.core.fluids;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;

public final class MultiFluidTank extends CombinedStorage<FluidVariant, FilteredFluidStorage> {
    private final List<String> names;

    private MultiFluidTank(List<String> names, List<FilteredFluidStorage> tanks) {
        super(tanks);
        this.names = names;
    }

    public FilteredFluidStorage tank(String name) {
        int index = this.names.indexOf(name);
        if (index < 0) {
            throw new IllegalArgumentException("Unknown tank: " + name);
        }
        return this.parts.get(index);
    }

    public FilteredFluidStorage tank(int index) {
        return this.parts.get(index);
    }

    public int tankCount() {
        return this.parts.size();
    }

    public void readValue(ValueInput input) {
        for (int i = 0; i < this.names.size(); i++) {
            this.parts.get(i).readValue(input.childOrEmpty(this.names.get(i)));
        }
    }

    public void writeValue(ValueOutput output) {
        for (int i = 0; i < this.names.size(); i++) {
            this.parts.get(i).writeValue(output.child(this.names.get(i)));
        }
    }

    public static Builder builder(Runnable onChange) {
        return new Builder(onChange);
    }

    public static final class Builder {
        private final Runnable onChange;
        private final List<String> names = new ArrayList<>();
        private final List<FilteredFluidStorage> tanks = new ArrayList<>();

        private Builder(Runnable onChange) {
            this.onChange = onChange;
        }

        public Builder tank(String name, long capacity) {
            return tank(name, capacity, FilteredFluidStorage.any());
        }

        public Builder tank(String name, long capacity, Predicate<FluidVariant> filter) {
            this.names.add(name);
            this.tanks.add(new FilteredFluidStorage(capacity, filter, this.onChange));
            return this;
        }

        public MultiFluidTank build() {
            return new MultiFluidTank(List.copyOf(this.names), List.copyOf(this.tanks));
        }
    }
}
