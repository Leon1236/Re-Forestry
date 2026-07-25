package com.leon1236.reforestry.apiculture.hives;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class HiveGenCaveCeiling implements IHiveGen {
    private final TagKey<Block> blocks;
    private final TagKey<Block> extraReplaceable;

    public HiveGenCaveCeiling(TagKey<Block> blocks, TagKey<Block> extraReplaceable) {
        this.blocks = blocks;
        this.extraReplaceable = extraReplaceable;
    }

    @Override
    @Nullable
    public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
        return null;
    }

    @Override
    @Nullable
    public BlockPos getPosForHive(WorldGenLevel level, RandomSource rand, int posX, int posZ) {
        int groundY = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, posX, posZ);
        int minBuildHeight = level.getMinY();
        if (groundY == minBuildHeight) {
            return null;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX, groundY, posZ);
        ArrayList<BlockPos> validPos = new ArrayList<>();

        BlockState blockState = level.getBlockState(pos);
        while (pos.getY() > minBuildHeight) {
            if (blockState.is(blocks)) {
                BlockPos below = pos.below();
                if (canReplace(level.getBlockState(below), level, below)) {
                    validPos.add(below);
                }
            }
            pos.move(Direction.DOWN);
            blockState = level.getBlockState(pos);
        }

        if (validPos.isEmpty()) {
            return null;
        }
        return validPos.get(validPos.size() > 1 ? rand.nextInt(validPos.size()) : 0);
    }

    @Override
    public boolean isValidLocation(WorldGenLevel level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(blocks);
    }

    @Override
    public boolean canReplace(BlockState blockState, WorldGenLevel level, BlockPos pos) {
        return BlockUtil.canReplace(blockState, level, pos) || blockState.is(extraReplaceable);
    }
}
