package com.leon1236.reforestry.arboriculture.worldgen;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.utils.VecUtil;
import com.leon1236.reforestry.core.worldgen.FeatureBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.block.Block;
import com.leon1236.reforestry.arboriculture.blocks.BlockSapling;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;

import org.jetbrains.annotations.Nullable;
import java.util.*;

public abstract class FeatureArboriculture extends FeatureBase {
	protected static final int minPodHeight = 3;

	public static final ThreadLocal<Boolean> SKIP_EXTENDED_CHECKS = ThreadLocal.withInitial(() -> false);

	protected final ITreeGenData tree;

	protected FeatureArboriculture(ITreeGenData tree) {
		this.tree = tree;
	}

	@Override
	public IGenome getDefaultGenome() {
		return this.tree.getDefaultGenome();
	}

	@Override
	public boolean place(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos, boolean forced) {
		TreeBlockTypeLeaf leaf = new TreeBlockTypeLeaf(this.tree, genome);
		TreeBlockTypeLog wood = new TreeBlockTypeLog(this.tree, genome);

		preGenerate(genome, level, rand, pos);

		BlockPos genPos;
		if (forced) {
			genPos = pos;
		} else {

			genPos = getValidGrowthPos(level, pos);
		}

		if (genPos != null) {

			clearSaplings(level, genPos);

			ArrayList<BlockPos> branchEnds = new ArrayList<>();
			ArrayList<BlockPos> logOrigins = new ArrayList<>();

			generateTrunk(level, logOrigins, branchEnds, rand, wood, genPos);
			branchEnds.sort(VecUtil.TOP_DOWN_COMPARATOR);
			logOrigins.sort(VecUtil.TOP_DOWN_COMPARATOR);
			TreeContour.Impl contour = new TreeContour.Impl(branchEnds, logOrigins);

			generateLeaves(genome, level, rand, leaf, contour, genPos);
			generateExtras(genome, level, rand, genPos, contour);

			if (contour.boundingBox != null) {

				DiscreteVoxelShape voxelshapepart = updateLeaves(level, contour);

				SKIP_EXTENDED_CHECKS.set(true);
				StructureTemplate.updateShapeAtEdge(level, 3, voxelshapepart, contour.boundingBox.minX(), contour.boundingBox.minY(), contour.boundingBox.minZ());
				SKIP_EXTENDED_CHECKS.set(false);
			}
			return true;
		}

		return false;
	}

	public void preGenerate(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos startPos) {
	}

	private static DiscreteVoxelShape updateLeaves(LevelAccessor level, TreeContour.Impl contour) {
		BoundingBox pBox = contour.boundingBox;
		for (BlockPos trunk : contour.trunkOrigins) {
			pBox = BoundingBox.encapsulating(pBox, new BoundingBox(trunk));
		}
		contour.boundingBox = pBox;
		DiscreteVoxelShape discretevoxelshape = new BitSetDiscreteVoxelShape(pBox.getXSpan(), pBox.getYSpan(), pBox.getZSpan());
		ArrayList<HashSet<BlockPos>> list = new ArrayList<>();

		for (int j = 0; j < 7; ++j) {
			list.add(new HashSet<>());
		}

		for (BlockPos trunk : contour.trunkOrigins) {
			if (pBox.isInside(trunk)) {
				list.get(0).add(trunk.immutable());
				discretevoxelshape.fill(trunk.getX() - pBox.minX(), trunk.getY() - pBox.minY(), trunk.getZ() - pBox.minZ());
			}
		}

		for (BlockPos blockpos : contour.leavePositions) {
			if (pBox.isInside(blockpos)) {
				discretevoxelshape.fill(blockpos.getX() - pBox.minX(), blockpos.getY() - pBox.minY(), blockpos.getZ() - pBox.minZ());
			}
		}

		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		int k1 = 0;

		while (true) {
			while (k1 >= 7 || !list.get(k1).isEmpty()) {
				if (k1 >= 7) {
					return discretevoxelshape;
				}

				Iterator<BlockPos> iterator = list.get(k1).iterator();
				BlockPos blockpos1 = iterator.next();
				iterator.remove();
				if (pBox.isInside(blockpos1)) {
					if (k1 != 0) {
						BlockState blockstate = level.getBlockState(blockpos1);
						setBlockKnownShape(level, blockpos1, blockstate.setValue(BlockStateProperties.DISTANCE, k1));
					}

					discretevoxelshape.fill(blockpos1.getX() - pBox.minX(), blockpos1.getY() - pBox.minY(), blockpos1.getZ() - pBox.minZ());

					for (Direction direction : Direction.values()) {
						blockpos$mutableblockpos.setWithOffset(blockpos1, direction);
						if (pBox.isInside(blockpos$mutableblockpos)) {
							int k = blockpos$mutableblockpos.getX() - pBox.minX();
							int l = blockpos$mutableblockpos.getY() - pBox.minY();
							int i1 = blockpos$mutableblockpos.getZ() - pBox.minZ();
							if (!discretevoxelshape.isFull(k, l, i1)) {
								BlockState blockstate1 = level.getBlockState(blockpos$mutableblockpos);
								OptionalInt optionalint = LeavesBlock.getOptionalDistanceAt(blockstate1);
								if (!optionalint.isEmpty()) {
									int j1 = Math.min(optionalint.getAsInt(), k1 + 1);
									if (j1 < 7) {
										list.get(j1).add(blockpos$mutableblockpos.immutable());
										k1 = Math.min(k1, j1);
									}
								}
							}
						}
					}
				}
			}

			++k1;
		}
	}

	private static void setBlockKnownShape(LevelWriter level, BlockPos pos, BlockState state) {
		level.setBlock(pos, state, Block.UPDATE_NEIGHBORS | Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
	}

	protected abstract void generateTrunk(LevelAccessor level, List<BlockPos> logOrigins, List<BlockPos> branchCoords, RandomSource rand, TreeBlockTypeLog wood, BlockPos startPos);

	protected abstract void generateLeaves(IGenome genome, LevelAccessor level, RandomSource rand, TreeBlockTypeLeaf leaf, TreeContour contour, BlockPos startPos);

	protected abstract void generateExtras(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos startPos, TreeContour contour);

	@Nullable
	public abstract BlockPos getValidGrowthPos(LevelAccessor level, BlockPos pos);

	public void clearSaplings(LevelAccessor level, BlockPos genPos) {
		int treeGirth = getClearGirth();
		for (int x = 0; x < treeGirth; x++) {
			for (int z = 0; z < treeGirth; z++) {
				BlockPos saplingPos = genPos.offset(x, 0, z);
				if (level.getBlockState(saplingPos).getBlock() instanceof BlockSapling) {
					level.setBlock(saplingPos, Blocks.AIR.defaultBlockState(), 18);
				}
			}
		}
	}

	protected int getClearGirth() {
		return this.tree.getGirth(this.tree.getDefaultGenome());
	}
}