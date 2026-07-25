package com.leon1236.reforestry.api.fuels;

import com.google.common.base.Preconditions;

import net.minecraft.world.item.ItemStack;

public record MoistenerFuel(ItemStack resource, ItemStack product, int stage, int moistenerValue) {
    public MoistenerFuel {
        Preconditions.checkArgument(!resource.isEmpty());
        Preconditions.checkArgument(!product.isEmpty());
    }
}
