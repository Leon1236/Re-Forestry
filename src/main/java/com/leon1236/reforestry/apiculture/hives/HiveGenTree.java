package com.leon1236.reforestry.apiculture.hives;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.core.utils.BlockUtil;

public enum HiveGenTree implements IHiveGen {
    INSTANCE;

    @Override
    public boolean canReplace(BlockState blockState, WorldGenLevel world, BlockPos pos) {
        return BlockUtil.canReplace(blockState, world, pos);
    }

    @Override
    public boolean isValidLocation(WorldGenLevel world, BlockPos pos) {
        BlockPos posAbove = pos.above();
        BlockState blockStateAbove = world.getBlockState(posAbove);
        if (!IHiveGen.isTreeBlock(blockStateAbove)) {
            return false;
        }
        BlockPos posBelow = pos.below();
        BlockState blockStateBelow = world.getBlockState(posBelow);
        return canReplace(blockStateBelow, world, posBelow);
    }

    @Override
    @Nullable
    public BlockPos getPosForHive(WorldGenLevel level, int posX, int posZ) {
        ChunkAccess chunk = level.getChunk(posX >> 4, posZ >> 4);
        int height = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, posX & 15, posZ & 15) - 1;
        if (height <= chunk.getMinY()) {
            return null;
        }

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX, height, posZ);
        BlockState state = chunk.getBlockState(pos);
        if (!IHiveGen.isTreeBlock(state)) {
            return null;
        }

        do {
            pos.move(Direction.DOWN);
            state = chunk.getBlockState(pos);
        } while (IHiveGen.isTreeBlock(state));

        return pos.immutable();
    }
}
