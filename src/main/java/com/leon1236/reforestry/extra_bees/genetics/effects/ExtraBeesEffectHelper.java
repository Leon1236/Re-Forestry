package com.leon1236.reforestry.extra_bees.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.render.ParticleRender;
import com.leon1236.reforestry.core.utils.VecUtil;

final class ExtraBeesEffectHelper {
	private ExtraBeesEffectHelper() {
	}

	static BlockPos randomTerritoryPos(IGenome genome, IBeeHousing housing) {
		Vec3i area = BeeCanWork.getParticleArea(genome, housing);
		BlockPos randomPos = VecUtil.getRandomPositionInArea(housing.level().getRandom(), area);
		return randomPos.offset(housing.position()).offset(VecUtil.center(area));
	}

	static IEffectData doFx(IGenome genome, IEffectData storedData, IBeeHousing housing, ParticleTypesHolder particles) {
		Level level = housing.level();
		RandomSource random = level.getRandom();
		if (random.nextBoolean()) {
			IBeekeepingLogic beekeepingLogic = housing.getBeekeepingLogic();
			List<BlockPos> flowerPositions = beekeepingLogic.getFlowerPositions();
			ParticleRender.addBeeHiveFX(housing, genome, flowerPositions);
			return storedData;
		}
		Vec3 coords = housing.getBeeFXCoordinates();
		particles.spawn(level, coords.x, coords.y + 0.5, coords.z, random);
		return storedData;
	}

	@FunctionalInterface
	interface ParticleTypesHolder {
		void spawn(Level level, double x, double y, double z, RandomSource random);
	}

	static ParticleTypesHolder lightningFx() {
		return (level, x, y, z, random) -> {
			level.addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0, 0.05, 0);
			level.addParticle(ParticleTypes.CRIT, x, y, z, 0, 0.02, 0);
		};
	}

	static ParticleTypesHolder radioactiveFx() {
		return (level, x, y, z, random) -> {
			level.addParticle(ParticleTypes.SCRAPE, x, y, z, 0, 0.02, 0);
			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.01, 0);
		};
	}

	static ParticleTypesHolder meteorFx() {
		return (level, x, y, z, random) -> {
			level.addParticle(ParticleTypes.LAVA, x, y, z, 0, 0.05, 0);
			level.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0.05, 0);
		};
	}

	static ParticleTypesHolder foodFx() {
		return (level, x, y, z, random) ->
				level.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, 0, 0.05, 0);
	}

	static ParticleTypesHolder blindnessFx() {
		return (level, x, y, z, random) ->
				level.addParticle(ParticleTypes.SQUID_INK, x, y, z, 0, 0.02, 0);
	}

	static ParticleTypesHolder gravityFx() {
		return (level, x, y, z, random) ->
				level.addParticle(ParticleTypes.PORTAL, x, y, z, 0, 0.05, 0);
	}

	static ParticleTypesHolder witherFx() {
		return (level, x, y, z, random) ->
				level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0.02, 0);
	}

	static ParticleTypesHolder waterFx() {
		return (level, x, y, z, random) ->
				level.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0, 0, 0);
	}
}
