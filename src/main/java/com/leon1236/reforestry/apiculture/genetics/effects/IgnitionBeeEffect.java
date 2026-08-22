package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;

public class IgnitionBeeEffect extends ThrottledBeeEffect {
	private static final int ignitionChance = 50;
	private static final int fireDuration = 500;

	public IgnitionBeeEffect() {
		super(ForestryBeeEffects.IGNITION, false, 20, false, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		List<LivingEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);
		for (LivingEntity entity : entities) {
			int chance = ignitionChance;
			int duration = fireDuration;

			int count = BeeManager.getArmorApiaristHelper().wearsItems(entity, this, true);
			if (count > 3) {
				continue;
			} else if (count > 2) {
				chance = 5;
				duration = 50;
			} else if (count > 1) {
				chance = 20;
				duration = 200;
			} else if (count > 0) {
				chance = 35;
				duration = 350;
			}

			if (level.getRandom().nextInt(1000) >= chance) {
				continue;
			}

			entity.igniteForSeconds(duration);
		}

		return storedData;
	}

	@Override
	public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		if (level.getRandom().nextInt(2) != 0) {
			return super.doFX(genome, storedData, housing);
		}
		Vec3 beeFXCoordinates = housing.getBeeFXCoordinates();
		level.addParticle(ParticleTypes.FLAME, beeFXCoordinates.x, beeFXCoordinates.y + 0.5, beeFXCoordinates.z, 0, 0.05, 0);
		level.addParticle(ParticleTypes.SMOKE, beeFXCoordinates.x, beeFXCoordinates.y + 0.5, beeFXCoordinates.z, 0, 0.05, 0);
		return storedData;
	}
}
