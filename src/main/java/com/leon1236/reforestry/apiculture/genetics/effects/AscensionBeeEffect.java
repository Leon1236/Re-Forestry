package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.utils.VecUtil;

public class AscensionBeeEffect extends PotionBeeEffect {
	public AscensionBeeEffect() {
		super(ForestryBeeEffects.ASCENSION, true, MobEffects.LEVITATION, 200);
	}

	@Override
	public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		super.doFX(genome, storedData, housing);
		RandomSource rand = housing.level().getRandom();
		Vec3i area = BeeCanWork.getParticleArea(genome, housing);
		BlockPos coordinates = housing.position().offset(VecUtil.center(area));
		housing.level().addParticle(ParticleTypes.END_ROD,
				coordinates.getX() + rand.nextFloat() * area.getX(),
				coordinates.getY() + rand.nextFloat() * area.getY(),
				coordinates.getZ() + rand.nextFloat() * area.getZ(),
				0D, 0.5D * rand.nextFloat(), 0D);
		return storedData;
	}
}
