package com.leon1236.reforestry.api.recipes;

import net.minecraft.world.item.ItemStack;

public interface ISqueezerContainerRecipe extends ISqueezerRecipe {
    ItemStack getEmptyContainer();
}
