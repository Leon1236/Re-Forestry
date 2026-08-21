package com.leon1236.reforestry.core.worldgen;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.worldgen.ITreeBlockType;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockType;
import com.leon1236.reforestry.arboriculture.worldgen.TreeBlockTypeLog;
import com.leon1236.reforestry.arboriculture.worldgen.TreeContour;
import com.leon1236.reforestry.core.utils.VecUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FeatureHelper {
	public static boolean addBlock(LevelAccessor world, BlockPos pos, ITreeBlockType type, EnumReplaceMode replaceMode) {
		return addBlock(world, pos, type, replaceMode, TreeContour.EMPTY);
	}

	public static boolean addBlock(LevelAccessor world, BlockPos pos, ITreeBlockType type, EnumReplaceMode replaceMode, TreeContour contour) {
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(pos.getX()), SectionPos.blockToSectionCoord(pos.getZ()))) {
			return false;
		}

		BlockState blockState = world.getBlockState(pos);
		if (replaceMode.canReplace(blockState, world, pos)) {
			type.setBlock(world, pos);
			contour.addLeaf(pos);
			return true;
		}
		return false;
	}

	public static void generateCylinderFromTreeStartPos(LevelAccessor world, ITreeBlockType block, BlockPos startPos, int girth, float radius, int height, EnumReplaceMode replace, TreeContour contour) {
		generateCylinderFromPos(world, block, startPos.offset(girth / 2, 0, girth / 2), radius, 1f, height, replace, contour);
	}

	public static void generateCylinderFromPos(LevelAccessor world, ITreeBlockType block, BlockPos center, float radius, int height, EnumReplaceMode replace, TreeContour contour) {
		generateCylinderFromPos(world, block, center, radius, 1f, height, replace, contour);
	}

	public static void generateCylinderFromTreeStartPos(LevelAccessor world, ITreeBlockType block, BlockPos startPos, int girth, float radius, float radiusMult, int height, EnumReplaceMode replace, TreeContour contour) {
		generateCylinderFromPos(world, block, startPos.offset(girth / 2, 0, girth / 2), radius, radiusMult, height, replace, contour);
	}

	public static void generateCylinderFromPos(LevelAccessor world, ITreeBlockType block, BlockPos center, float radius, float radiusMult, int height, EnumReplaceMode replace, TreeContour contour) {
		BlockPos start = BlockPos.containing(center.getX() - radius, center.getY(), center.getZ() - radius);
		for (int x = 0; x < radius * 2 + 1; x++) {
			for (int y = height - 1; y >= 0; y--) {
				for (int z = 0; z < radius * 2 + 1; z++) {
					BlockPos position = start.offset(x, y, z);
					Vec3i treeCenter = new Vec3i(center.getX(), position.getY(), center.getZ());
					if (position.distSqr(treeCenter) <= ((radius * radius) + 0.01) * radiusMult) {
						Direction direction = VecUtil.direction(position, treeCenter);
						block.setDirection(direction);
						if (addBlock(world, position, block, replace)) {
							contour.addLeaf(position);
						}
					}
				}
			}
		}
	}

	public static void generateCylinderFromPosWithChance(LevelAccessor world, ITreeBlockType block, BlockPos center, float radius, float radiusMult, int height, EnumReplaceMode replace, TreeContour contour, RandomSource rand, float failChance) {
		BlockPos start = BlockPos.containing(center.getX() - radius, center.getY(), center.getZ() - radius);

		float maxDistSqr = ((radius * radius) + 0.01f) * radiusMult;
		float chance = failChance - (float) Math.floor(failChance);
		float randDist = maxDistSqr - (float) (Math.ceil(failChance) * Math.ceil(failChance));

		for (int x = 0; x < radius * 2 + 1; x++) {
			for (int y = height - 1; y >= 0; y--) {
				for (int z = 0; z < radius * 2 + 1; z++) {
					BlockPos position = start.offset(x, y, z);
					Vec3i treeCenter = new Vec3i(center.getX(), position.getY(), center.getZ());

					float curDistSqr = (float) position.distSqr(treeCenter);

					if (curDistSqr <= maxDistSqr) {

						if (
							failChance <= 0 ||
								curDistSqr <= randDist ||
								(

									curDistSqr > randDist && chance <= rand.nextFloat()
								)
						) {
							Direction direction = VecUtil.direction(position, treeCenter);
							block.setDirection(direction);
							if (addBlock(world, position, block, replace)) {
								contour.addLeaf(position);
							}
						}
					}
				}
			}
		}
	}

	public static void generateCircleFromTreeStartPos(LevelAccessor world, RandomSource rand, BlockPos startPos, int girth, float radius, int width, int height, ITreeBlockType block, float chance, EnumReplaceMode replace, TreeContour contour) {
		generateCircle(world, rand, startPos.offset(girth / 2, 0, girth / 2), radius, width, height, block, chance, replace, contour);
	}

	public static void generateCircle(LevelAccessor world, RandomSource rand, BlockPos center, float radius, int width, int height, ITreeBlockType block, float chance, EnumReplaceMode replace, TreeContour contour) {
		BlockPos start = BlockPos.containing(center.getX() - radius, center.getY(), center.getZ() - radius);
		BlockPos area = BlockPos.containing(radius * 2 + 1, height, radius * 2 + 1);

		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		for (int x = start.getX(); x < start.getX() + area.getX(); x++) {
			for (int y = start.getY() + area.getY() - 1; y >= start.getY(); y--) {
				for (int z = start.getZ(); z < start.getZ() + area.getZ(); z++) {

					if (rand.nextFloat() > chance) {
						continue;
					}

					double distance = mutablePos.set(x, y, z).distToLowCornerSqr(center.getX(), y, center.getZ());
					if ((radius - width - 0.01) * (radius - width - 0.01) < distance && distance <= (radius + 0.01) * (radius + 0.01)) {
						if (addBlock(world, mutablePos, block, replace)) {
							contour.addLeaf(mutablePos);
						}
					}
				}
			}
		}
	}

	public static void generateSphereFromTreeStartPos(LevelAccessor world, BlockPos startPos, int girth, int radius, ITreeBlockType block, EnumReplaceMode replace, TreeContour contour) {
		generateSphere(world, startPos.offset(girth / 2, 0, girth / 2), radius, block, replace, contour);
	}

	public static void generateSphere(LevelAccessor world, BlockPos center, int radius, ITreeBlockType block, EnumReplaceMode replace, TreeContour contour) {
		Vec3i start = new Vec3i(center.getX() - radius, center.getY() - radius, center.getZ() - radius);
		Vec3i area = new Vec3i(radius * 2 + 1, radius * 2 + 1, radius * 2 + 1);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		for (int x = start.getX(); x < start.getX() + area.getX(); x++) {
			for (int y = start.getY() + area.getY() - 1; y >= start.getY(); y--) {
				for (int z = start.getZ(); z < start.getZ() + area.getZ(); z++) {
					if (center.closerThan(mutablePos.set(x, y, z), radius + 0.01)) {
						if (addBlock(world, mutablePos, block, replace)) {
							contour.addLeaf(mutablePos);
						}
					}
				}
			}
		}
	}

	public static void generateEllipsoid(LevelAccessor world, BlockPos center, float radiusX, float radiusY, float radiusZ, ITreeBlockType block, EnumReplaceMode replace, TreeContour contour) {
		generateEllipsoid(world, center, radiusX, radiusY, radiusZ, 1, block, replace, contour);
	}

	public static void generateEllipsoid(LevelAccessor world, BlockPos center, float radiusX, float radiusY, float radiusZ, float radiusMult, ITreeBlockType block, EnumReplaceMode replace, TreeContour contour) {
		Vec3i start = new Vec3i(center.getX() - Math.round(radiusX), center.getY() - Math.round(radiusY), center.getZ() - Math.round(radiusZ));
		Vec3i area = new Vec3i((int) radiusX * 2 + 1, (int) radiusY * 2 + 1, (int) radiusZ * 2 + 1);

		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

		for (int x = start.getX() - 1; x <= start.getX() + area.getX(); x++) {
			for (int y = start.getY() + area.getY() + 1; y > start.getY(); y--) {
				for (int z = start.getZ() - 1; z <= start.getZ() + area.getZ(); z++) {

					if ((((x - center.getX()) * (x - center.getX())) / (radiusX * radiusX)
						+ ((y - center.getY()) * (y - center.getY())) / (radiusY * radiusY)
						+ ((z - center.getZ()) * (z - center.getZ())) / (radiusZ * radiusZ)) <= 1.00 * radiusMult) {

						mutablePos.set(x, y, z);
						if (addBlock(world, mutablePos, block, replace)) {
							contour.addLeaf(mutablePos);
						}
					}
				}
			}
		}
	}

	public static void generateLine(LevelAccessor world, BlockPos start, BlockPos end, float thicknessStart, float thicknessEnd, ITreeBlockType leaf, EnumReplaceMode replace, TreeContour contour) {

		float dx = end.getX() - start.getX();
		float dy = end.getY() - start.getY();
		float dz = end.getZ() - start.getZ();

		float length = (float) Math.sqrt(start.distSqr(end));
		if (length == 0) return;

		Vec3 step = new Vec3(
			dx / length,
			dy / length,
			dz / length
		);
		float stepDist = (float) step.length();

		Vec3 prog = new Vec3(0, 0, 0);
		BlockPos.MutableBlockPos mutablePos = start.mutable();

		for (float d = 0; d <= length; d += stepDist) {

			float completion = d / length;
			float thickness = thicknessStart + (thicknessEnd - thicknessStart) * completion;

			mutablePos = mutablePos.set(
				(int) (start.getX() + prog.x),
				(int) (start.getY() + prog.y),
				(int) (start.getZ() + prog.z)
			);
			generateEllipsoid(world, mutablePos, thickness, thickness, thickness, 1.5f + (completion * 0.5f), leaf, replace, contour);

			prog = prog.add(step);
		}

	}

	public static void generateTreeTrunk(
		LevelAccessor level,
		List<BlockPos> logOrigins,
		RandomSource rand,
		ITreeBlockType wood,
		BlockPos startPos,
		int height,
		int girth,
		int yStart,
		float vinesChance,
		@Nullable Direction leanDirection,
		float leanScale
	) {

		final int leanStartY = (int) Math.floor(height * 0.33f);
		int prevXOffset = 0;
		int prevZOffset = 0;

		int leanX = 0;
		int leanZ = 0;

		if (leanDirection != null) {
			leanX = leanDirection.getStepX();
			leanZ = leanDirection.getStepZ();
		}

		for (int x = 0; x < girth; x++) {
			for (int z = 0; z < girth; z++) {
				for (int y = height - 1; y >= yStart; y--) {
					float lean;
					if (y < leanStartY) {
						lean = 0;
					} else {
						lean = leanScale * (y - leanStartY) / (height - leanStartY);
					}
					int xOffset = (int) Math.floor(leanX * lean);
					int zOffset = (int) Math.floor(leanZ * lean);

					if (xOffset != prevXOffset || zOffset != prevZOffset) {
						prevXOffset = xOffset;
						prevZOffset = zOffset;
						if (y > 0) {
							if (leanDirection != null) {
								wood.setDirection(leanDirection);
							}
							addBlock(level, startPos.offset(x + xOffset, y - 1, z + zOffset), wood, EnumReplaceMode.ALL);
							wood.setDirection(Direction.UP);
						}
					}

					BlockPos pos = startPos.offset(x + xOffset, y, z + zOffset);
					addBlock(level, pos, wood, EnumReplaceMode.ALL);
					addVines(level, rand, pos, vinesChance);

					if (x == 0 && z == 0)
						logOrigins.add(pos);
				}
			}
		}
	}

	public static void generateTreeTrunk(
		LevelAccessor level,
		List<BlockPos> logOrigins,
		List<BlockPos> branchEnds,
		RandomSource rand,
		ITreeBlockType wood,
		BlockPos startPos,
		int height,
		int girth,
		int yStart,
		float vinesChance,
		float taper
	) {

		int taperStart = yStart + (int) (height * taper);

		for (int y = height - 1; y >= yStart; y--) {

			int midX = startPos.getX() + (girth / 2);
			int midZ = startPos.getZ() + (girth / 2);

			float taperAmount = (float) (y - taperStart) / (height - taperStart);

			for (int x = 0; x < girth; x++) {
				for (int z = 0; z < girth; z++) {
					BlockPos pos = startPos.offset(x, y, z);

					float dist = (float) Math.pow(pos.getX() - midX, 2) + (float) Math.pow(pos.getZ() - midZ, 2);
					float max = (float) Math.pow(girth * (1f - taperAmount), 2);

					if (y <= taperStart || dist <= max) {

						addBlock(level, pos, wood, EnumReplaceMode.ALL);
						addVines(level, rand, pos, vinesChance);
					}

					if (x == 0 && z == 0)
						logOrigins.add(pos);
				}
			}
		}
	}

	protected static void addVines(LevelAccessor world, RandomSource rand, BlockPos pos, float chance) {
		if (chance <= 0) {
			return;
		}

		if (rand.nextFloat() < chance) {
			BlockState blockState = Blocks.VINE.defaultBlockState().setValue(VineBlock.EAST, true);
			addBlock(world, pos.west(), new TreeBlockType(blockState), EnumReplaceMode.AIR);
		}
		if (rand.nextFloat() < chance) {
			BlockState blockState = Blocks.VINE.defaultBlockState().setValue(VineBlock.WEST, true);
			addBlock(world, pos.east(), new TreeBlockType(blockState), EnumReplaceMode.AIR);
		}
		if (rand.nextFloat() < chance) {
			BlockState blockState = Blocks.VINE.defaultBlockState().setValue(VineBlock.SOUTH, true);
			addBlock(world, pos.north(), new TreeBlockType(blockState), EnumReplaceMode.AIR);
		}
		if (rand.nextFloat() < chance) {
			BlockState blockState = Blocks.VINE.defaultBlockState().setValue(VineBlock.NORTH, true);
			addBlock(world, pos.south(), new TreeBlockType(blockState), EnumReplaceMode.AIR);
		}
	}

	public static void generatePods(IGenome genome, LevelAccessor world, RandomSource rand, BlockPos startPos, int height, int minHeight, int girth, TreeContour contour, EnumReplaceMode replaceMode) {
		for (BlockPos logPos : contour.getTrunkOrigins()) {

			int relativeY = logPos.getY() - startPos.getY();
			if (relativeY < minHeight) {
				continue;
			}

			for (int x = 0; x < girth; x++) {
				for (int z = 0; z < girth; z++) {

					if ((girth > 2) && (x > 0 && x < girth - 1) && (z > 0 && z < girth - 1)) {
						continue;
					}

					trySpawnFruitBlock(genome, world, rand, logPos.offset(x + 1, 0, z), replaceMode);
					trySpawnFruitBlock(genome, world, rand, logPos.offset(x - 1, 0, z), replaceMode);
					trySpawnFruitBlock(genome, world, rand, logPos.offset(x, 0, z + 1), replaceMode);
					trySpawnFruitBlock(genome, world, rand, logPos.offset(x, 0, z - 1), replaceMode);
				}
			}
		}
	}

	private static void trySpawnFruitBlock(IGenome genome, LevelAccessor world, RandomSource rand, BlockPos pos, EnumReplaceMode replaceMode) {
		BlockState blockState = world.getBlockState(pos);
		if (replaceMode.canReplace(blockState, world, pos)) {
			genome.getActiveAllele(TreeChromosomes.SPECIES).value().trySpawnFruitBlock(genome, world, rand, pos);
		}
	}

	public static void generateSupportStems(ITreeBlockType wood, LevelAccessor world, RandomSource rand, BlockPos startPos, int height, int girth, float chance, float maxHeight) {

		final int min = -1;

		for (int x = min; x <= girth; x++) {
			for (int z = min; z <= girth; z++) {

				if ((x == min && z == min) || (x == girth && z == girth) || (x == min && z == girth) || (x == girth && z == min)) {
					continue;
				}

				int stemHeight = rand.nextInt(Math.round(height * maxHeight));
				if (rand.nextFloat() < chance) {
					for (int y = 0; y < stemHeight; y++) {
						addBlock(world, startPos.offset(x, y, z), wood, EnumReplaceMode.SOFT);
					}
				}
			}
		}
	}

	public static Set<BlockPos> generateBranches(final LevelAccessor world, final RandomSource rand, final ITreeBlockType wood, final BlockPos startPos, final int girth, final float spreadY, final float spreadXZ, int radius, final int count, final float chance) {
		Set<BlockPos> branchEnds = new HashSet<>();
		if (radius < 1) {
			radius = 1;
		}

		for (final Direction branchDirection : Direction.Plane.HORIZONTAL) {
			wood.setDirection(branchDirection);

			BlockPos branchStart = startPos;

			int offsetX = branchDirection.getStepX();
			int offsetZ = branchDirection.getStepZ();
			if (offsetX > 0) {
				branchStart = branchStart.offset(girth - offsetX, 0, 0);
			}
			if (offsetZ > 0) {
				branchStart = branchStart.offset(0, 0, girth - offsetZ);
			}

			boolean firstStep = true;

			for (int i = 0; i < count; i++) {
				if (rand.nextFloat() > chance) {
					continue;
				}
				int y = 0;
				int x = 0;
				int z = 0;

				BlockPos branchEnd = null;

				boolean xDir = rand.nextBoolean();
				boolean zDir = rand.nextBoolean();

				float yForce = 0;
				float xzForce = 0;

				for (int r = 0; r < radius; r++) {

					if ((rand.nextFloat() < spreadY || yForce >= 1) && !firstStep) {

						y++;
						wood.setDirection(Direction.UP);

						if (yForce >= 1)
							yForce = yForce % 1;
						else
							yForce = 0;

					} else {

						if (!firstStep)
							yForce += spreadY;

						firstStep = false;

						if (rand.nextFloat() < spreadXZ || xzForce >= 1) {

							if (xzForce >= 1)
								xzForce = xzForce % 1;
							else
								xzForce = 0;

							if (branchDirection.getAxis() == Direction.Axis.Z) {
								if (xDir) {
									x++;
								} else {
									x--;
								}
								wood.setDirection(Direction.EAST);
							} else if (branchDirection.getAxis() == Direction.Axis.X) {
								if (zDir) {
									z++;
								} else {
									z--;
								}
								wood.setDirection(Direction.SOUTH);
							}
						} else {
							x += offsetX;
							z += offsetZ;
							wood.setDirection(branchDirection);

							xzForce += spreadXZ;
						}
					}

					BlockPos pos = branchStart.offset(x, y, z);
					if (addBlock(world, pos, wood, EnumReplaceMode.SOFT)) {
						branchEnd = pos;
					} else {
						break;
					}
				}

				if (branchEnd != null) {
					branchEnds.add(branchEnd);
				}
			}
		}

		return branchEnds;
	}

	public static TreeBlockType getWoodFromLog(TreeBlockTypeLog log, ForestryWoodType woodType) {
		if (log.getGenome().getActiveAllele(TreeChromosomes.FIREPROOF).value()) {
			return new TreeBlockType(ArboricultureBlocks.WOOD_FIREPROOF.get(woodType).block().defaultBlockState());
		}
		return new TreeBlockType(ArboricultureBlocks.WOOD.get(woodType).block().defaultBlockState());
	}

	public enum EnumReplaceMode {
		AIR {
			@Override
			public boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos) {
				return world.isEmptyBlock(pos);
			}
		},
		ALL {
			@Override
			public boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos) {
				return true;
			}
		},
		SOFT {
			@Override
			public boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos) {
				if (world instanceof Level) {
					BlockPlaceContext context = new DirectionalPlaceContext((Level) world, pos, Direction.DOWN, ItemStack.EMPTY, Direction.UP);
					return blockState.canBeReplaced(context);
				}
				return blockState.canBeReplaced();
			}
		};

		public abstract boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos);
	}

	public static class DirectionHelper {
		public static final Direction[] VALUES = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};

		public static Direction getRandom(RandomSource random) {
			return VALUES[random.nextInt(VALUES.length)];
		}

		public static Direction getRandomOther(RandomSource random, Direction direction) {
			List<Direction> directions = new java.util.ArrayList<>(Arrays.asList(VALUES));
			directions.remove(direction);
			return directions.get(random.nextInt(directions.size()));
		}
	}
}
