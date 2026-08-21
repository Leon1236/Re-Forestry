package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureSwampGum extends FeatureBinnieTree {
	public FeatureSwampGum(ITreeGenData tree) {
		super(tree, 14, 3);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		int leafSpawn = this.height + 2;
		int weakerBottom = (int) (this.height * (0.5 + rand.nextFloat() * 0.3F));
		int bottom = (int) (this.height * (0.45 + rand.nextFloat() * 0.2F));
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 0.75f);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 1.75f);
		while (leafSpawn > weakerBottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 1f + (1.75f * rand.nextFloat()));
		}
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 0.75f + (1.25f * rand.nextFloat()));
		}
		for (int i = 0; i < 7; ++i) {
			if (rand.nextFloat() > 0.45) {
				leafSpawn--;
				continue;
			}
		generateSphere(level, leaf, contour, startPos, rand.nextInt(this.girth) * (rand.nextBoolean() ? -1 : 1), leafSpawn--, rand.nextInt(this.girth) * (rand.nextBoolean() ? -1 : 1), 1);
	}
	}
}
