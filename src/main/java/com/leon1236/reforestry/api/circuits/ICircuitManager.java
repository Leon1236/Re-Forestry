package com.leon1236.reforestry.api.circuits;

import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface ICircuitManager {
    List<ICircuitLayout> getLayouts();

    @Nullable
    ICircuit getCircuit(ICircuitLayout layout, ItemStack stack);

    @Nullable
    ICircuit getCircuit(String circuitId);

    @Nullable
    ICircuitLayout getLayout(String layoutId);

    @Nullable
    ICircuitBoard getCircuitBoard(ItemStack stack);

    boolean isCircuitBoard(ItemStack stack);

    Collection<CircuitHolder> getCircuitHolders();
}
