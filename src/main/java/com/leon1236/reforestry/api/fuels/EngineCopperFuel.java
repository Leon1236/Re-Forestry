package com.leon1236.reforestry.api.fuels;

import net.minecraft.world.item.ItemStack;

public record EngineCopperFuel(ItemStack fuel, int powerPerCycle, int burnDuration) {
}
