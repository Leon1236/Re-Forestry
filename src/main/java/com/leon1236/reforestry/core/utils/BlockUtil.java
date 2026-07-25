package com.leon1236.reforestry.core.utils;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public final class BlockUtil {
    private BlockUtil() {
    }

    public static boolean canReplace(BlockState blockState, LevelAccessor world, BlockPos pos) {
        return world.getBlockState(pos).canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    public static boolean canPlaceTree(BlockState state, LevelAccessor world, BlockPos pos) {
        BlockPos downPos = pos.below();
        BlockState belowState = world.getBlockState(downPos);
        return !(world.getBlockState(pos).canBeReplaced() && !state.getFluidState().isEmpty())
                && !belowState.is(BlockTags.LEAVES)
                && !belowState.is(BlockTags.LOGS);
    }

    public static boolean tryPlantCocoaPod(LevelAccessor world, BlockPos pos) {
        Direction facing = getValidPodFacing(world, pos, BlockTags.JUNGLE_LOGS);
        if (facing == null) {
            return false;
        }
        BlockState state = Blocks.COCOA.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, facing);
        world.setBlock(pos, state, 18);
        return true;
    }

    @Nullable
    public static Direction getValidPodFacing(LevelAccessor world, BlockPos pos, TagKey<Block> logTag) {
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            if (isValidPodLocation(world, pos, facing, logTag)) {
                return facing;
            }
        }
        return null;
    }

    public static boolean isValidPodLocation(LevelReader world, BlockPos pos, Direction direction, TagKey<Block> logTag) {
        BlockPos relative = pos.relative(direction);
        if (world instanceof Level level) {
            if (!level.isLoaded(relative)) {
                return false;
            }
        } else if (!world.hasChunk(
                SectionPos.blockToSectionCoord(relative.getX()),
                SectionPos.blockToSectionCoord(relative.getZ()))) {
            return false;
        }
        return world.getBlockState(relative).is(logTag);
    }

    public static void setBlockWithBreakSound(Level level, BlockPos pos, BlockState blockState, BlockState oldState) {
        if (level.setBlockAndUpdate(pos, blockState)) {
            sendDestroyEffects(level, pos, oldState);
        }
    }

    public static void sendDestroyEffects(Level level, BlockPos pos, BlockState state) {
        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));
    }

    public static BlockPos getPos(LootParams.Builder context) {
        Vec3 origin = context.getOptionalParameter(LootContextParams.ORIGIN);
        return origin != null ? BlockPos.containing(origin) : BlockPos.ZERO;
    }
}
