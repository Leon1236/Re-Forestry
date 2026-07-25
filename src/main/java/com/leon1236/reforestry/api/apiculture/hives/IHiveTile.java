package com.leon1236.reforestry.api.apiculture.hives;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IHiveTile {
    void calmBees();

    boolean isAngry();

    void onAttack(Level world, BlockPos pos, Player player);

    void onBroken(Level world, BlockPos pos, Player player, boolean canHarvest);
}
