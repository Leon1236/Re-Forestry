package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureAspen extends FeatureBinnieTree {
	public FeatureAspen(ITreeGenData tree) {
		super(tree, 5, 2);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height;
		final float bottom = randBetween(rand, this.height / 2, this.height / 2 + 1) + 1;
		float width = this.height * randBetween(rand, 0.25f, 0.35f);
		if (width < 2.0f) {
			width = 2.0f;
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.5f * width);
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, randBetween(rand, 0.9f, 1.1f) * width);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.8f * width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, 0.4f * width);
	}
}
