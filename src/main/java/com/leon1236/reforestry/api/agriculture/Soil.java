package com.leon1236.reforestry.api.agriculture;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public record Soil(ItemStack resource, BlockState soilState) {
}
