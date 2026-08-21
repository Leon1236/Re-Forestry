package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureRoseGum extends FeatureBinnieTree {
	public FeatureRoseGum(ITreeGenData tree) {
		super(tree, 9, 3);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + 2;
		final float bottom = randBetween(rand, 0.4f, 0.5f) * this.height;
		float width = this.height * randBetween(rand, 0.05f, 0.1f);
		if (width < 1.5f) {
			width = 1.5f;
		}
		this.bushiness = 0.5f;
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.4f * width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.7f * width);
		this.bushiness = 0.1f;
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, randBetween(rand, 0.9f, 1.1f) * width);
		}
	}
}
