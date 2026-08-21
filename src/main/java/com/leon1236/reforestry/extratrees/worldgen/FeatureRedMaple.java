package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureRedMaple extends FeatureBinnieTree {
	public FeatureRedMaple(ITreeGenData tree) {
		super(tree, 5, 2);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + 2;
		final float bottom = randBetween(rand, 1, 2);
		final float bottom2 = (this.height + bottom) / 2.0f;
		float width = 2.0f;
		generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, 0.4f * width);
		while (leafSpawn > bottom2) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width);
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width - 0.4f);
		}
		width += 0.6;
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width);
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn--, 0.0f, width - 0.4f);
		}
		if (leafSpawn == bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0.0f, leafSpawn, 0.0f, width - 0.6f);
		}
	}
}
