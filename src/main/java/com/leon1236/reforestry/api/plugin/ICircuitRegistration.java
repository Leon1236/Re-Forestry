package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.circuits.ICircuit;

public interface ICircuitRegistration {
    void registerCircuit(String layoutId, ItemStack stack, ICircuit circuit);

    void registerLayout(String layoutId, Identifier socketType);
}
