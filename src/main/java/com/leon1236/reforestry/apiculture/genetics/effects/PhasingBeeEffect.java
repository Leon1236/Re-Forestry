package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.fox.Fox;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;

public class PhasingBeeEffect extends ThrottledBeeEffect {
	public PhasingBeeEffect() {
		super(ForestryBeeEffects.PHASING, true, 40, true, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		if (!(housing.level() instanceof ServerLevel level)) {
			return storedData;
		}
		RandomSource random = level.getRandom();
		List<LivingEntity> list = getEntitiesInRange(genome, housing, LivingEntity.class);

		for (LivingEntity entity : list) {
			int count = BeeManager.getArmorApiaristHelper().wearsItems(entity, this, true);
			if (count >= 4) {
				continue;
			}

			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();

			for (int i = 0; i < 16; i++) {
				double targetX = x + (random.nextDouble() - 0.5) * 16;
				double targetY = Mth.clamp(y + (double) (random.nextInt(16) - 8), level.getMinY(), level.getMaxY());
				double targetZ = z + (random.nextDouble() - 0.5) * 16;

				if (entity.isPassenger()) {
					entity.stopRiding();
				}

				Vec3 vec3 = entity.position();
				level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));

				if (entity.randomTeleport(targetX, targetY, targetZ, true)) {
					SoundEvent sound = entity instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
					level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 1f, 1f);
					entity.playSound(sound, 1f, 1f);
					break;
				}
			}
		}
		return storedData;
	}

	@Override
	public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		level.addParticle(ParticleTypes.PORTAL,
				housing.position().getX() + 0.5,
				housing.position().getY() + 0.5 + level.getRandom().nextDouble() * 2,
				housing.position().getZ() + 0.5,
				level.getRandom().nextGaussian(), 0, level.getRandom().nextGaussian());
		return storedData;
	}
}
