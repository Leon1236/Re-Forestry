package com.leon1236.reforestry.arboriculture.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.worldgen.TreeGenHelper;

public class ForestSpawner implements ITreeSpawner {
    @Override
    public int spawn(CommandSourceStack source, ITreeSpecies species, Player player) {
        Vec3 look = player.getLookAngle();

        int x = (int) Math.round(player.getX() + 16 * look.x);
        int y = (int) Math.round(player.getY());
        int z = (int) Math.round(player.getZ() + 16 * look.z);
        ServerLevel level = (ServerLevel) player.level();
        RandomSource random = level.getRandom();

        for (int i = 0; i < 16; i++) {
            int spawnX = x + random.nextInt(32) - 16;
            int spawnZ = z + random.nextInt(32) - 16;
            BlockPos pos = new BlockPos(spawnX, y, spawnZ);

            TreeGenHelper.generateTree(species, null, level, random, pos);
        }

        return 1;
    }
}
