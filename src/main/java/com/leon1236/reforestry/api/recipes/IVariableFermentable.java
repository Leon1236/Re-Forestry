package com.leon1236.reforestry.api.recipes;

import net.minecraft.world.item.ItemStack;

public interface IVariableFermentable {
    float getFermentationModifier(ItemStack stack);
}
