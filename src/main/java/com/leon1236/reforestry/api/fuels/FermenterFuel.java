package com.leon1236.reforestry.api.fuels;

import net.minecraft.world.item.ItemStack;

public record FermenterFuel(ItemStack item, int fermentPerCycle, int burnDuration) {
}
