package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureBanana extends FeatureBinnieTree {
	public FeatureBanana(ITreeGenData tree) {
		super(tree, 6, 1);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		int leafSpawn = this.height + 1;

		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 1.5f);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn, 0, this.girth + 1);
	}
}
