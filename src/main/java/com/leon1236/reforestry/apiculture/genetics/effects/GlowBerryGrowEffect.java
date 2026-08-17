package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.utils.VecUtil;

public class GlowBerryGrowEffect extends ThrottledBeeEffect {
	public GlowBerryGrowEffect() {
		super(ForestryBeeEffects.GLOW_BERRY_GROW, false, 200, true, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		if (level.isClientSide()) {
			return storedData;
		}

		Vec3i area = BeeCanWork.getParticleArea(genome, housing);
		BlockPos center = housing.position().offset(VecUtil.center(area));
		BlockPos pos = VecUtil.getRandomPositionInArea(level.getRandom(), area).offset(center);
		if (!level.isLoaded(pos)) {
			return storedData;
		}

		BlockState current = level.getBlockState(pos);
		if (!current.is(BlockTags.CAVE_VINES) || !current.hasProperty(CaveVines.BERRIES) || current.getValue(CaveVines.BERRIES)) {
			return storedData;
		}

		level.setBlockAndUpdate(pos, current.setValue(CaveVines.BERRIES, true));
		return storedData;
	}
}
