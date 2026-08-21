package com.leon1236.reforestry.extratrees.worldgen;

import java.util.List;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureTree;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLog;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import com.leon1236.reforestry.core.worldgen.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public class FeatureShrub extends FeatureTree {
	public FeatureShrub(ITreeGenData tree) {
		super(tree, 1, 1, 1);
	}

	@Override
	public void generateTrunk(LevelAccessor level, List<BlockPos> logOrigins, List<BlockPos> branchCoords, RandomSource rand, TreeBlockTypeLog wood, BlockPos startPos) {
		FeatureHelper.generateTreeTrunk(level, logOrigins, rand, wood, startPos, this.height, this.girth, 0, 0, null, 0);
	}

	@Override
	protected void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos) {
		float leafSpawn = this.height;
		FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
		FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth + 1.0f, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
		int i = 0;
		while (leafSpawn >= 0.0f) {
			FeatureHelper.generateCylinderFromTreeStartPos(level, leaf, startPos.offset(0, (int) leafSpawn--, 0), this.girth, this.girth + 2.0f + i, 1, FeatureHelper.EnumReplaceMode.SOFT, contour);
			i++;
		}
	}
}
