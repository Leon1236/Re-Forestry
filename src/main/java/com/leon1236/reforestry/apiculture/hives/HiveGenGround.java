package com.leon1236.reforestry.apiculture.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.core.utils.BlockUtil;

public class HiveGenGround implements IHiveGen {
    private final TagKey<Block> blocks;

    public HiveGenGround(TagKey<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    @Nullable
    public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
        int groundY = level.getHeight(getHeightmapType(), posX, posZ);
        int minBuildHeight = level.getMinY();
        if (groundY == minBuildHeight) {
            return null;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX, groundY, posZ);
        BlockState blockState = level.getBlockState(pos);
        while (canReplace(blockState, level, pos)) {
            pos.move(Direction.DOWN);
            if (pos.getY() <= minBuildHeight) {
                return null;
            }
            blockState = level.getBlockState(pos);
        }

        return pos.above();
    }

    public Heightmap.Types getHeightmapType() {
        return Heightmap.Types.WORLD_SURFACE_WG;
    }

    @Override
    public boolean canReplace(BlockState blockState, WorldGenLevel world, BlockPos pos) {
        return IHiveGen.isTreeBlock(blockState) || BlockUtil.canReplace(blockState, world, pos);
    }

    @Override
    public boolean isValidLocation(WorldGenLevel world, BlockPos pos) {
        return world.getBlockState(pos.below()).is(blocks);
    }
}
