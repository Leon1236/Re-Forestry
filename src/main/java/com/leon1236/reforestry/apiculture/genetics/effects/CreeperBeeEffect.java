package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.genetics.EffectData;

public class CreeperBeeEffect extends ThrottledBeeEffect {
	private static final int explosionChance = 50;
	private static final byte defaultForce = 12;
	private static final byte indexExplosionTimer = 1;
	private static final byte indexExplosionForce = 2;

	public CreeperBeeEffect() {
		super(ForestryBeeEffects.CREEPER, true, 20, false, true);
	}

	@Override
	public IEffectData validateStorage(IEffectData storedData) {
		if (!(storedData instanceof EffectData data) || data.getIntSize() < 3) {
			return new EffectData(3, 0);
		}
		return storedData;
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level world = housing.level();
		BlockPos housingCoords = housing.position();

		if (storedData.getInteger(indexExplosionTimer) > 0) {
			progressExplosion(storedData, world, housingCoords);
			return storedData;
		}

		List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
		for (Player player : players) {
			int chance = explosionChance;
			storedData.setInteger(indexExplosionForce, defaultForce);

			int count = BeeManager.armorApiaristHelper.wearsItems(player, this, true);
			if (count > 3) {
				continue;
			} else if (count > 2) {
				chance = 5;
				storedData.setInteger(indexExplosionForce, 6);
			} else if (count > 1) {
				chance = 20;
				storedData.setInteger(indexExplosionForce, 8);
			} else if (count > 0) {
				chance = 35;
				storedData.setInteger(indexExplosionForce, 10);
			}

			if (world.getRandom().nextInt(1000) >= chance) {
				continue;
			}

			float pitch = (1.0F + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2F) * 0.7F;
			world.playSound(null, housingCoords.getX(), housingCoords.getY(), housingCoords.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, pitch);
			storedData.setInteger(indexExplosionTimer, 2);
		}

		return storedData;
	}

	private static void progressExplosion(IEffectData storedData, Level world, BlockPos pos) {
		int explosionTimer = storedData.getInteger(indexExplosionTimer);
		explosionTimer--;
		storedData.setInteger(indexExplosionTimer, explosionTimer);

		if (explosionTimer > 0) {
			return;
		}

		world.explode(null, pos.getX(), pos.getY(), pos.getZ(), storedData.getInteger(indexExplosionForce), false, Level.ExplosionInteraction.NONE);
	}
}
