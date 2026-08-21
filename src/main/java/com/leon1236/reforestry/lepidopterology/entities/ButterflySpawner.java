package com.leon1236.reforestry.lepidopterology.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.lepidopterology.genetics.ButterflySpeciesType;

public final class ButterflySpawner {
	private ButterflySpawner() {
	}

	public static boolean onRandomLeafTick(IGenome treeGenome, Level world, RandomSource rand, BlockPos pos) {
		if (!(world instanceof ServerLevel serverLevel)
				|| !Boolean.TRUE.equals(serverLevel.getGameRules().get(GameRules.SPAWN_MOBS))
				|| ForestryConfig.disableButterflySpawning()) {
			return false;
		}

		float sappiness = treeGenome.getActiveAllele(TreeChromosomes.SAPPINESS).value();
		float yield = treeGenome.getActiveAllele(TreeChromosomes.YIELD).value();
		if (rand.nextFloat() >= sappiness * yield) {
			return false;
		}

		IButterfly spawn = ButterflySpeciesType.INSTANCE.createRandomIndividual(rand);
		IButterflySpecies activeSpecies = spawn.getSpecies();
		if (rand.nextFloat() >= activeSpecies.getRarity() * 0.5f) {
			return false;
		}

		if (EntityButterfly.isMaxButterflyCluster(Vec3.atCenterOf(pos), world)) {
			return false;
		}

		if (!spawn.canSpawn(world, pos.getX(), pos.getY(), pos.getZ())) {
			return false;
		}

		if (world.isEmptyBlock(pos.north())) {
			attemptButterflySpawn(world, spawn, pos.north());
		} else if (world.isEmptyBlock(pos.south())) {
			attemptButterflySpawn(world, spawn, pos.south());
		} else if (world.isEmptyBlock(pos.west())) {
			attemptButterflySpawn(world, spawn, pos.west());
		} else if (world.isEmptyBlock(pos.east())) {
			attemptButterflySpawn(world, spawn, pos.east());
		}

		return false;
	}

	private static void attemptButterflySpawn(Level world, IButterfly butterfly, BlockPos pos) {
		ButterflySpeciesType.INSTANCE.spawnButterflyInWorld(world, butterfly.copy(), pos.getX(), pos.getY() + 0.1f,
				pos.getZ());
	}
}
