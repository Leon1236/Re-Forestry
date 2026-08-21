package com.leon1236.reforestry.extratrees.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureTree;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLeaf;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import com.leon1236.reforestry.core.worldgen.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

public abstract class FeatureBinnieTree extends FeatureTree {
	protected float bushiness;

	protected FeatureBinnieTree(ITreeGenData tree, int baseHeight, int heightVariation) {
		super(tree, baseHeight, heightVariation);
		this.bushiness = 0.0f;
	}

	protected FeatureBinnieTree(ITreeGenData tree, int baseHeight, int heightVariation, int minHeightOverride) {
		super(tree, baseHeight, heightVariation, minHeightOverride);
		this.bushiness = 0.0f;
	}

	protected int randBetween(RandomSource rand, int a, int b) {
		return a + rand.nextInt(Math.max(1, b - a));
	}

	protected float randBetween(RandomSource rand, float a, float b) {
		return a + rand.nextFloat() * (b - a);
	}

	protected void generateCylinder(
			LevelAccessor level,
			RandomSource rand,
			TreeBlockTypeLeaf leaf,
			TreeContour contour,
			BlockPos startPos,
			float centerX,
			float centerY,
			float centerZ,
			float radius) {
		float centerOffset = (this.girth - 1) / 2.0f;
		float cx = centerX + centerOffset;
		float cz = centerZ + centerOffset;
		int startX = (int) (cx - radius);
		int startZ = (int) (cz - radius);
		int area = (int) (radius * 2.0f + 1.0f);
		int y = (int) centerY;
		for (int x = startX; x < startX + area; ++x) {
			for (int z = startZ; z < startZ + area; ++z) {
				double dx = x - cx;
				double dz = z - cz;
				double distance = Math.sqrt(dx * dx + dz * dz);
				if (distance <= radius + 0.01) {
					if (distance < radius - 0.5f || rand.nextFloat() >= this.bushiness) {
						FeatureHelper.addBlock(
								level,
								startPos.offset(x, y, z),
								leaf,
								FeatureHelper.EnumReplaceMode.AIR,
								contour);
					}
				}
			}
		}
	}

	protected void generateSphere(
			LevelAccessor level,
			TreeBlockTypeLeaf leaf,
			TreeContour contour,
			BlockPos startPos,
			float centerX,
			float centerY,
			float centerZ,
			int radius) {
		float centerOffset = (this.girth - 1) / 2.0f;
		float cx = centerX + centerOffset;
		float cy = centerY;
		float cz = centerZ + centerOffset;
		int startX = (int) (cx - radius);
		int startY = (int) (cy - radius);
		int startZ = (int) (cz - radius);
		int area = radius * 2 + 1;
		for (int x = startX; x < startX + area; ++x) {
			for (int y = startY; y < startY + area; ++y) {
				for (int z = startZ; z < startZ + area; ++z) {
					double dx = x - cx;
					double dy = y - cy;
					double dz = z - cz;
					if (Math.sqrt(dx * dx + dy * dy + dz * dz) <= radius + 0.01) {
						FeatureHelper.addBlock(
								level,
								startPos.offset(x, y, z),
								leaf,
								FeatureHelper.EnumReplaceMode.AIR,
								contour);
					}
				}
			}
		}
	}
}
