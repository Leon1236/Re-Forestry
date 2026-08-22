package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureSweetgum extends FeatureBinnieTree {
	public FeatureSweetgum(ITreeGenData tree) {
		super(tree, 5, 1);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + 1;
		final float bottom = randBetween(rand, 1, 2);
		float width = this.height * randBetween(rand, 0.7f, 0.75f);
		if (width > 7.0f) {
			width = 7.0f;
		}
		final float coneHeight = leafSpawn - bottom;
		while (leafSpawn > bottom) {
			float radius = 1.0f - (leafSpawn - bottom) / coneHeight;
			radius *= 2.0f - radius;
			radius *= width;
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, radius);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, 0.7f * width);
	}
}
