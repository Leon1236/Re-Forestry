package com.leon1236.reforestry.arboriculture.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.worldgen.TreeGenHelper;

public class TreeSpawner implements ITreeSpawner {
    @Override
    public int spawn(CommandSourceStack source, ITreeSpecies species, Player player) {
        Vec3 look = player.getLookAngle();

        int x = (int) Math.round(player.getX() + 3 * look.x);
        int y = (int) Math.round(player.getY());
        int z = (int) Math.round(player.getZ() + 3 * look.z);
        BlockPos pos = new BlockPos(x, y, z);

        ServerLevel level = source.getLevel();
        TreeGenHelper.generateTree(species, null, level, level.getRandom(), pos);
        return 1;
    }
}
