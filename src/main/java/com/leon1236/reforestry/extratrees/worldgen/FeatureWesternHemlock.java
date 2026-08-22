package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureWesternHemlock extends FeatureBinnieTree {
	public FeatureWesternHemlock(ITreeGenData tree) {
		super(tree, 7, 3);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + this.girth * 4;
		float bottom = randBetween(rand, 2, 3);
		final float coneHeight = leafSpawn - bottom;
		float width = (this.girth * this.height / 3);
		if (width > 9) {
			width = 9;
		}
		while (leafSpawn > bottom) {
			float radius = 1.0f - (leafSpawn - bottom) / coneHeight;
			radius *= width;
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, radius);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + width * 0.7f);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn, 0, this.girth + width * 0.4f);
	}
}
