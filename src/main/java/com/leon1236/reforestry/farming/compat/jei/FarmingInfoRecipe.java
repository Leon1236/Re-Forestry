package com.leon1236.reforestry.farming.compat.jei;

import com.leon1236.reforestry.api.agriculture.IFarmType;
import com.leon1236.reforestry.api.circuits.ICircuit;

import net.minecraft.world.item.ItemStack;

public record FarmingInfoRecipe(ItemStack tube, IFarmType properties, ICircuit circuit) {
}
