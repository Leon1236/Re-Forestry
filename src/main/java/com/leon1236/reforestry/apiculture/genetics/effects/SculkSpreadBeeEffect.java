package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.utils.VecUtil;

public class SculkSpreadBeeEffect extends ThrottledBeeEffect {
	public SculkSpreadBeeEffect() {
		super(ForestryBeeEffects.SCULK, false, 200, true, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		if (level.isClientSide()) {
			return storedData;
		}
		RandomSource random = level.getRandom();
		Vec3i area = BeeCanWork.getParticleArea(genome, housing);
		BlockPos randomPos = VecUtil.getRandomPositionInArea(random, area);
		BlockPos posBlock = randomPos.offset(housing.position()).offset(VecUtil.center(area));

		if (level.isLoaded(posBlock)) {
			BlockState state = level.getBlockState(posBlock);
			if (state.isAir() && !level.getBlockState(posBlock.below()).isAir()) {
				SculkSpreader spreader = SculkSpreader.createLevelSpreader();
				spreader.addCursors(posBlock, random.nextInt(5) + 1);
				spreader.updateCursors(level, housing.position(), random, true);
				spreader.updateCursors(level, housing.position(), random, true);
				spreader.updateCursors(level, housing.position(), random, true);
				spreader.updateCursors(level, housing.position(), random, true);
				spreader.updateCursors(level, housing.position(), random, true);
			} else if (state.getBlock() == Blocks.SCULK_SHRIEKER) {
				level.setBlockAndUpdate(posBlock.above(), Blocks.SCULK_SHRIEKER.defaultBlockState().setValue(SculkShriekerBlock.CAN_SUMMON, true));
			}
		}

		return storedData;
	}
}
