package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureCommonAlder extends FeatureBinnieTree {
	public FeatureCommonAlder(ITreeGenData tree) {
		super(tree, 5, 2);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height + 1;
		float bottom = randBetween(rand, 1, 2);

		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 0.5f);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 1.75f);
		while(leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 2.25f);
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 2);
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn, 0, this.girth + 1.75f);
	}
}
