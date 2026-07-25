package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.utils.VecUtil;

public class GlacialBeeEffect extends ThrottledBeeEffect {
    public GlacialBeeEffect() {
        super(ForestryBeeEffects.GLACIAL, false, 200, true, false);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        Level level = housing.level();

        if (housing.temperature().isWarmerOrEqual(TemperatureType.WARM)) {
            return storedData;
        }

        Vec3i area = BeeCanWork.getParticleArea(genome, housing);
        BlockPos centerPos = housing.position().offset(VecUtil.center(area));

        for (int i = 0; i < 10; i++) {
            BlockPos posBlock = VecUtil.getRandomPositionInArea(level.getRandom(), area).offset(centerPos);
            if (level.isLoaded(posBlock) && level.getBlockState(posBlock).is(Blocks.WATER)
                    && level.isEmptyBlock(posBlock.above())) {
                level.setBlockAndUpdate(posBlock, Blocks.ICE.defaultBlockState());
            }
        }

        return storedData;
    }
}
