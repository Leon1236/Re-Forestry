package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;
import com.leon1236.reforestry.core.render.ParticleRender;
import com.leon1236.reforestry.core.utils.VecUtil;

public class SnowingBeeEffect extends ThrottledBeeEffect {
    public SnowingBeeEffect() {
        super(ForestryBeeEffects.SNOWING, false, 20, true, true);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        Level level = housing.level();

        if (housing.temperature().isWarmerOrEqual(TemperatureType.WARM)) {
            return storedData;
        }

        Vec3i area = BeeCanWork.getParticleArea(genome, housing);
        BlockPos randomPos = VecUtil.getRandomPositionInArea(level.getRandom(), area);
        BlockPos posBlock = randomPos.offset(housing.position()).offset(VecUtil.center(area));

        if (level.isLoaded(posBlock)) {
            BlockState state = level.getBlockState(posBlock);
            Block block = state.getBlock();
            if ((!state.isAir() && block != Blocks.SNOW) || !Blocks.SNOW.defaultBlockState().canSurvive(level, posBlock)) {
                return storedData;
            }

            if (block == Blocks.SNOW) {
                int layers = state.getValue(SnowLayerBlock.LAYERS);
                if (layers < 7) {
                    level.setBlockAndUpdate(posBlock, state.setValue(SnowLayerBlock.LAYERS, layers + 1));
                } else {
                    level.setBlockAndUpdate(posBlock, Blocks.SNOW.defaultBlockState());
                }
            } else if (state.canBeReplaced()) {
                level.setBlockAndUpdate(posBlock, Blocks.SNOW.defaultBlockState());
            }
        }

        return storedData;
    }

    @Override
    public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        Level level = housing.level();
        if (level.getRandom().nextInt(3) == 0) {
            Vec3i area = BeeCanWork.getParticleArea(genome, housing);
            BlockPos spawn = VecUtil.getRandomPositionInArea(level.getRandom(), area)
                    .offset(housing.position())
                    .offset(VecUtil.center(area));
            ParticleRender.addEntitySnowFX(level, spawn.getX(), spawn.getY(), spawn.getZ());
            return storedData;
        }
        return super.doFX(genome, storedData, housing);
    }
}
