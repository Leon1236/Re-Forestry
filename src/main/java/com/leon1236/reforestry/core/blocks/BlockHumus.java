package com.leon1236.reforestry.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BlockHumus extends Block {
    private static final int DEGRADE_STEPS = 3;

    public static final IntegerProperty DEGRADE = IntegerProperty.create("degrade", 0, DEGRADE_STEPS);

    public BlockHumus(Properties properties) {
        super(properties.randomTicks().strength(0.5f).sound(SoundType.GRAVEL));
        registerDefaultState(getStateDefinition().any().setValue(DEGRADE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(DEGRADE);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(140) != 0) {
            return;
        }
        if (isEnrooted(level, pos)) {
            degradeSoil(level, pos);
        }
    }

    private static boolean isEnrooted(Level level, BlockPos pos) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                BlockPos neighbor = pos.offset(i, 1, j);
                BlockState state = level.getBlockState(neighbor);
                if (state.is(BlockTags.LOGS) || state.getBlock() instanceof BonemealableBlock) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void degradeSoil(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        int degrade = state.getValue(DEGRADE) + 1;
        if (degrade >= DEGRADE_STEPS) {
            level.setBlock(pos, Blocks.SAND.defaultBlockState(), UPDATE_CLIENTS);
        } else {
            level.setBlock(pos, state.setValue(DEGRADE, degrade), UPDATE_CLIENTS);
        }
    }
}
