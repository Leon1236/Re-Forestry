package com.leon1236.reforestry.extra_bees.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import team.reborn.energy.api.EnergyStorage;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesBlocks;

public final class ExtraBeesEffectLogic {
	private ExtraBeesEffectLogic() {
	}

	public static void placeEctoplasm(Level level, BlockPos position) {
		if (level.getRandom().nextInt(100) >= 4) {
			return;
		}
		if (!level.isEmptyBlock(position)) {
			return;
		}
		BlockState below = level.getBlockState(position.below());
		if (!below.isSolidRender() && !below.is(ExtraBeesBlocks.ECTOPLASM.block())) {
			return;
		}
		level.setBlock(position, ExtraBeesBlocks.ECTOPLASM.block().defaultBlockState(), 3);
	}

	public static void doAcid(Level level, BlockPos pos) {
		if (level.getRandom().nextInt(100) >= 6) {
			return;
		}
		BlockState state = level.getBlockState(pos);
		if (state.is(Blocks.COBBLESTONE) || state.is(Blocks.STONE)) {
			level.setBlock(pos, Blocks.GRAVEL.defaultBlockState(), 3);
		} else if (state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK)) {
			level.setBlock(pos, Blocks.SAND.defaultBlockState(), 3);
		}
	}

	public static void spawnMob(ServerLevel level, BlockPos position, EntityType<? extends Mob> type) {
		if (level.getRandom().nextInt(200) >= 2) {
			return;
		}
		if (level.getNearestPlayer(position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5, 16.0, false) == null) {
			return;
		}
		level.sendParticles(net.minecraft.core.particles.ParticleTypes.SMOKE,
				position.getX() + level.getRandom().nextFloat(),
				position.getY() + level.getRandom().nextFloat(),
				position.getZ() + level.getRandom().nextFloat(),
				1, 0, 0, 0, 0);
		level.sendParticles(net.minecraft.core.particles.ParticleTypes.FLAME,
				position.getX() + level.getRandom().nextFloat(),
				position.getY() + level.getRandom().nextFloat(),
				position.getZ() + level.getRandom().nextFloat(),
				1, 0, 0, 0, 0);

		AABB nearby = new AABB(position).inflate(8.0, 4.0, 8.0);
		if (level.getEntities(type, nearby, entity -> true).size() >= 6) {
			return;
		}

		double posX = position.getX() + (level.getRandom().nextDouble() - level.getRandom().nextDouble()) * 4.0;
		double posY = position.getY() + level.getRandom().nextInt(3) - 1;
		double posZ = position.getZ() + (level.getRandom().nextDouble() - level.getRandom().nextDouble()) * 4.0;
		Mob entity = type.create(level, EntitySpawnReason.MOB_SUMMONED);
		if (entity == null) {
			return;
		}
		entity.snapTo(posX, posY, posZ, level.getRandom().nextFloat() * 360.0f, 0.0f);
		if (!entity.checkSpawnRules(level, EntitySpawnReason.MOB_SUMMONED) || !entity.checkSpawnObstruction(level)) {
			entity.discard();
			return;
		}
		entity.finalizeSpawn(level, level.getCurrentDifficultyAt(BlockPos.containing(posX, posY, posZ)),
				EntitySpawnReason.MOB_SUMMONED, null);
		level.addFreshEntity(entity);
		level.levelEvent(2004, position, 0);
	}

	public static void fillWater(Level level, BlockPos position) {
		if (level.getRandom().nextInt(120) > 1) {
			return;
		}
		Storage<FluidVariant> storage = FluidStorage.SIDED.find(level, position, Direction.UP);
		if (storage == null) {
			return;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			storage.insert(FluidVariant.of(net.minecraft.world.level.material.Fluids.WATER), 100 * 81L, transaction);
			transaction.commit();
		}
	}

	public static void insertPower(Level level, BlockPos position) {
		EnergyStorage storage = EnergyStorage.SIDED.find(level, position, Direction.UP);
		if (storage == null) {
			return;
		}
		try (Transaction transaction = Transaction.openOuter()) {
			storage.insert(5, transaction);
			transaction.commit();
		}
	}

	public static EntityType<? extends Mob> zombie() {
		return EntityTypes.ZOMBIE;
	}

	public static EntityType<? extends Mob> skeleton() {
		return EntityTypes.SKELETON;
	}

	public static EntityType<? extends Mob> creeper() {
		return EntityTypes.CREEPER;
	}

	public static boolean playerNearby(Level level, BlockPos pos, double distance) {
		Player player = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, distance, false);
		return player != null;
	}
}
