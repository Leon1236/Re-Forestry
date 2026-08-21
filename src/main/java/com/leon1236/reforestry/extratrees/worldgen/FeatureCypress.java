package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureCypress extends FeatureBinnieTree {
	public FeatureCypress(ITreeGenData tree) {
		super(tree, 6, 2);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + 2;
		final float bottom = 1.0f;
		float width = this.height * randBetween(rand, 0.15f, 0.2f);
		if (width > 7.0f) {
			width = 7.0f;
		}
		final float coneHeight = leafSpawn - bottom;
		while (leafSpawn > bottom) {
			float radius = 1.0f - (leafSpawn - bottom) / coneHeight;
			radius *= width - 1.0f;
			++radius;
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, radius);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.7f * width);
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, 0.4f * width);
	}
}
