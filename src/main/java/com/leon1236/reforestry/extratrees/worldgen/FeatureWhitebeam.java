package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureWhitebeam extends FeatureBinnieTree {
	public FeatureWhitebeam(ITreeGenData tree) {
		super(tree, 5, 3);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		int leafSpawn = this.height + 1;
		final float bottom = randBetween(rand, 2, 3);
		final float width = this.height * randBetween(rand, 0.5f, 0.6f);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.4f * width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.6f * width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.8f * width);
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, randBetween(rand, 0.95f, 1.05f) * width);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, 0.7f * width);
	}
}
