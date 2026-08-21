package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureDouglasFir extends FeatureBinnieTree {
	public FeatureDouglasFir(ITreeGenData tree) {
		super(tree, 7, 3);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		int leafSpawn = this.height + 1;
		int patchyBottom = (int) (this.height / 2.5F);
		int bottom = 3 + rand.nextInt(2);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 2);
		while (leafSpawn > patchyBottom) {
			if (rand.nextFloat() < 0.45F) {
				generateCylinder(level, rand, leaf, contour, startPos, (this.girth - 1) * (rand.nextBoolean() ? -1 : 1), leafSpawn, (this.girth - 1) * (rand.nextBoolean() ? -1 : 1), this.girth + 0.75f);
			}
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 2.9f);
	}
		while (leafSpawn > bottom) {
			generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 2.3f - (rand.nextInt(2)));
		}
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn--, 0, this.girth + 1);
		generateCylinder(level, rand, leaf, contour, startPos, 0, leafSpawn, 0, this.girth - 0.2f);
	}
}
