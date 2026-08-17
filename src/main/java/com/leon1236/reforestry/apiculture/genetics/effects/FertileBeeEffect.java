package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.BeeCanWork;

public class FertileBeeEffect extends ThrottledBeeEffect {
	private static final int MAX_BLOCK_FIND_TRIES = 5;

	public FertileBeeEffect() {
		super(ForestryBeeEffects.FERTILE, false, 6, true, false);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		Level level = housing.level();
		BlockPos housingCoordinates = housing.position();
		Vec3i area = BeeCanWork.getParticleArea(genome, housing);

		int blockX = getRandomOffset(level.getRandom(), housingCoordinates.getX(), area.getX());
		int blockZ = getRandomOffset(level.getRandom(), housingCoordinates.getZ(), area.getZ());
		int blockMaxY = housingCoordinates.getY() + area.getY() / 2 + 1;
		int blockMinY = housingCoordinates.getY() - area.getY() / 2 - 1;

		for (int attempt = 0; attempt < MAX_BLOCK_FIND_TRIES; ++attempt) {
			if (level.getChunkSource().getChunkNow(blockX >> 4, blockZ >> 4) != null) {
				if (tryTickColumn(level, blockX, blockZ, blockMaxY, blockMinY)) {
					break;
				}
				blockX = getRandomOffset(level.getRandom(), housingCoordinates.getX(), area.getX());
				blockZ = getRandomOffset(level.getRandom(), housingCoordinates.getZ(), area.getZ());
			}
		}

		return storedData;
	}

	private static int getRandomOffset(RandomSource random, int centrePos, int offset) {
		return centrePos + random.nextInt(Math.max(1, offset)) - offset / 2;
	}

	private static boolean tryTickColumn(Level level, int x, int z, int maxY, int minY) {
		for (int y = maxY; y >= minY; --y) {
			BlockPos pos = new BlockPos(x, y, z);
			if (!level.isLoaded(pos)) {
				continue;
			}
			BlockState state = level.getBlockState(pos);
			Block block = state.getBlock();
			if (state.isRandomlyTicking() && block instanceof BonemealableBlock) {
				level.scheduleTick(pos, block, 5);
				return true;
			}
		}
		return false;
	}
}
