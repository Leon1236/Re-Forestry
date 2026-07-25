package com.leon1236.reforestry.api.fuels;

import net.minecraft.world.item.ItemStack;

public record RainSubstrate(ItemStack item, int duration, float speed, boolean reverse) {
    public RainSubstrate(ItemStack item, float speed) {
        this(item, 0, speed, true);
    }

    public RainSubstrate(ItemStack item, int duration, float speed) {
        this(item, duration, speed, false);
    }
}
