package com.leon1236.reforestry.api.arboriculture;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IToolGrafter {
    float getSaplingModifier(ItemStack stack, Level level, Player player, BlockPos pos);
}
