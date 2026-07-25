package com.leon1236.reforestry.core.circuits;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.circuits.CircuitHolder;
import com.leon1236.reforestry.api.circuits.ICircuit;
import com.leon1236.reforestry.api.circuits.ICircuitBoard;
import com.leon1236.reforestry.api.circuits.ICircuitLayout;
import com.leon1236.reforestry.api.circuits.ICircuitManager;
import com.leon1236.reforestry.core.circuits.ItemCircuitBoard;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class CircuitManager implements ICircuitManager {
    private final ImmutableMultimap<ICircuitLayout, CircuitHolder> circuitHolders;
    private final ImmutableMap<String, ICircuitLayout> layoutsById;
    private final ImmutableMap<String, ICircuit> circuitsById;

    public CircuitManager(ImmutableMultimap<ICircuitLayout, CircuitHolder> circuitHolders,
                          ImmutableMap<String, ICircuitLayout> layoutsById,
                          ImmutableMap<String, ICircuit> circuitsById) {
        this.circuitHolders = circuitHolders;
        this.layoutsById = layoutsById;
        this.circuitsById = circuitsById;
    }

    @Override
    public List<ICircuitLayout> getLayouts() {
        return this.layoutsById.values().asList();
    }

    @Nullable
    @Override
    public ICircuit getCircuit(ICircuitLayout layout, ItemStack stack) {
        for (CircuitHolder holder : this.circuitHolders.get(layout)) {
            if (ItemStack.isSameItem(holder.stack(), stack)) {
                return holder.circuit();
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ICircuit getCircuit(String circuitId) {
        return this.circuitsById.get(circuitId);
    }

    @Nullable
    @Override
    public ICircuitLayout getLayout(String layoutId) {
        return this.layoutsById.get(layoutId);
    }

    @Nullable
    @Override
    public ICircuitBoard getCircuitBoard(ItemStack stack) {
        if (!isCircuitBoard(stack)) {
            return null;
        }
        return new CircuitBoard(CircuitBoard.readTag(stack));
    }

    @Override
    public boolean isCircuitBoard(ItemStack stack) {
        return stack.getItem() instanceof ItemCircuitBoard;
    }

    @Override
    public Collection<CircuitHolder> getCircuitHolders() {
        return this.circuitHolders.values();
    }
}
