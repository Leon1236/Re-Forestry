package com.leon1236.reforestry.core.plugin;

import java.util.ArrayList;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.circuits.CircuitHolder;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitLayout;
import com.leon1236.reforestry.api.plugin.ICircuitRegistration;
import com.leon1236.reforestry.core.circuits.CircuitLayout;

public final class CircuitRegistrationImpl implements ICircuitRegistration {
    private final ArrayList<CircuitLayout> layouts = new ArrayList<>();
    private final ArrayList<CircuitHolder> circuits = new ArrayList<>();

    @Override
    public void registerCircuit(String layoutId, ItemStack stack, ICircuit circuit) {
        this.circuits.add(new CircuitHolder(layoutId, stack, circuit));
    }

    @Override
    public void registerLayout(String layoutId, net.minecraft.resources.Identifier socketType) {
        this.layouts.add(new CircuitLayout(layoutId, socketType));
    }

    public ImmutableMap<String, ICircuitLayout> buildLayouts() {
        ImmutableMap.Builder<String, ICircuitLayout> builder = ImmutableMap.builder();
        for (CircuitLayout layout : this.layouts) {
            builder.put(layout.getId(), layout);
        }
        return builder.build();
    }

    public ImmutableMultimap<ICircuitLayout, CircuitHolder> buildCircuitHolders(ImmutableMap<String, ICircuitLayout> layoutsById) {
        ImmutableMultimap.Builder<ICircuitLayout, CircuitHolder> builder = ImmutableMultimap.builder();
        for (CircuitHolder holder : this.circuits) {
            ICircuitLayout layout = layoutsById.get(holder.layoutId());
            if (layout == null) {
                throw new IllegalStateException("No layout registered for circuit holder: " + holder.layoutId());
            }
            builder.put(layout, holder);
        }
        return builder.build();
    }

    public ImmutableMap<String, ICircuit> buildCircuits() {
        ImmutableMap.Builder<String, ICircuit> builder = ImmutableMap.builder();
        for (CircuitHolder holder : this.circuits) {
            builder.put(holder.circuit().getId(), holder.circuit());
        }
        return builder.buildOrThrow();
    }
}
