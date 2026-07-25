package com.leon1236.reforestry.arboriculture.worldgen;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.WoodAccess;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;
import com.leon1236.reforestry.arboriculture.tiles.TileSapling;

/**
 * A generic, species-agnostic tree shape (straight trunk + round canopy) used
 * for every species until real per-species shapes (CE's ~45 hand-authored
 * Feature classes) are ported. Deliberately simple - the growth loop
 * (sapling -> logs+leaves placed) is real, the exact silhouette is not.
 */
public final class SimpleTreeGenerator {
    private SimpleTreeGenerator() {
    }

    public static int computeHeight(IGenome genome) {
        float heightMultiplier = genome.getActiveAllele(TreeChromosomes.HEIGHT).value();
        return Math.clamp(Math.round(5 * heightMultiplier), 3, 14);
    }

    public static int getGirth(IGenome genome) {
        return genome.getActiveAllele(TreeChromosomes.GIRTH).value();
    }

    @Nullable
    public static BlockPos findGrowthOrigin(LevelAccessor level, IGenome genome, BlockPos saplingPos) {
        int girth = getGirth(genome);
        int height = computeHeight(genome);

        int offset = girth - 1;
        for (int x = -offset; x <= 0; x++) {
            for (int z = -offset; z <= 0; z++) {
                BlockPos origin = saplingPos.offset(x, 0, z);
                if (hasSaplingSquare(level, genome, origin, girth) && hasRoom(level, origin, girth, height)) {
                    return origin;
                }
            }
        }
        return null;
    }

    private static boolean hasSaplingSquare(LevelAccessor level, IGenome genome, BlockPos origin, int girth) {
        for (int x = 0; x < girth; x++) {
            for (int z = 0; z < girth; z++) {
                BlockPos pos = origin.offset(x, 0, z);
                if (!isMatchingSapling(level, genome, pos)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isMatchingSapling(LevelAccessor level, IGenome genome, BlockPos pos) {
        if (!(level.getBlockEntity(pos) instanceof TileSapling sapling)) {
            return false;
        }
        IGenome saplingGenome = sapling.getGenome();
        return saplingGenome != null
                && saplingGenome.getActiveAllele(TreeChromosomes.SPECIES).value() == genome.getActiveAllele(TreeChromosomes.SPECIES).value();
    }

    private static boolean hasRoom(LevelAccessor level, BlockPos origin, int girth, int height) {
        for (int x = 0; x < girth; x++) {
            for (int y = 1; y <= height + 3; y++) {
                for (int z = 0; z < girth; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (!state.canBeReplaced() && !state.is(BlockTags.LEAVES)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean grow(Level level, IGenome genome, RandomSource random, BlockPos saplingPos) {
        BlockPos origin = findGrowthOrigin(level, genome, saplingPos);
        if (origin == null) {
            return false;
        }

        int girth = getGirth(genome);
        int height = computeHeight(genome);
        ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
        IWoodType woodType = species.woodType();
        boolean fireproof = genome.getActiveAllele(TreeChromosomes.FIREPROOF).value();

        for (int x = 0; x < girth; x++) {
            for (int z = 0; z < girth; z++) {
                level.removeBlock(origin.offset(x, 0, z), false);
            }
        }

        BlockState log = WoodAccess.INSTANCE.getBlockState(woodType, WoodBlockKind.LOG, fireproof)
                .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
        for (int x = 0; x < girth; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < girth; z++) {
                    level.setBlock(origin.offset(x, y, z), log, 3);
                }
            }
        }

        BlockPos trunkTop = origin.offset(girth / 2, height, girth / 2);
        int canopyRadius = Math.max(2, girth);
        BlockState leavesTemplate = ArboricultureBlocks.LEAVES.block().defaultBlockState();
        List<BlockPos> placedLeaves = new ArrayList<>();
        for (int dy = -Math.max(2, girth + 1); dy <= 1; dy++) {
            int y = trunkTop.getY() + dy;
            float radius = dy <= -1 ? canopyRadius : Math.max(1, canopyRadius - 1);
            for (int dx = Math.round(-radius); dx <= Math.round(radius); dx++) {
                for (int dz = Math.round(-radius); dz <= Math.round(radius); dz++) {
                    if (dx * dx + dz * dz > radius * radius + 1) {
                        continue;
                    }
                    BlockPos pos = new BlockPos(trunkTop.getX() + dx, y, trunkTop.getZ() + dz);
                    BlockState existing = level.getBlockState(pos);
                    if (!existing.canBeReplaced() && !existing.is(BlockTags.LEAVES)) {
                        continue;
                    }
                    level.setBlock(pos, withUpdatedDistance(leavesTemplate, level, pos), 3);
                    if (level.getBlockEntity(pos) instanceof TileLeaves tileLeaves) {
                        tileLeaves.setGenome(genome);
                    }
                    placedLeaves.add(pos.immutable());
                }
            }
        }

        for (BlockPos pos : placedLeaves) {
            BlockState current = level.getBlockState(pos);
            if (!(current.getBlock() instanceof LeavesBlock)) {
                continue;
            }
            BlockState updated = withUpdatedDistance(current, level, pos);
            if (updated != current) {
                level.setBlock(pos, updated, 3);
            }
        }

        return true;
    }

    private static BlockState withUpdatedDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int distance = LeavesBlock.DECAY_DISTANCE;
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        for (Direction direction : Direction.values()) {
            neighborPos.setWithOffset(pos, direction);
            distance = Math.min(distance, LeavesBlock.getOptionalDistanceAt(level.getBlockState(neighborPos)).orElse(LeavesBlock.DECAY_DISTANCE) + 1);
            if (distance == 1) {
                break;
            }
        }
        return state.setValue(LeavesBlock.DISTANCE, distance);
    }
}
