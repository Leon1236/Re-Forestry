package com.leon1236.reforestry.api.items;

import net.minecraft.world.item.ItemStack;

public interface ICraftingPlan {
    ItemStack planUsed(ItemStack plan, ItemStack result);
}
