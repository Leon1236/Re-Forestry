package com.leon1236.reforestry.api.circuits;

import net.minecraft.world.item.ItemStack;

public record CircuitHolder(String layoutId, ItemStack stack, ICircuit circuit) {
}
