package com.leon1236.reforestry.core.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import com.leon1236.reforestry.core.features.CoreBlocks;

public class BlockBogEarth extends Block {
    private static final int MATURITY_DELIMITER = 3;

    public static final IntegerProperty MATURITY = IntegerProperty.create("maturity", 0, MATURITY_DELIMITER);

    public BlockBogEarth(Properties properties) {
        super(properties.randomTicks().strength(0.5f).sound(SoundType.GRAVEL));
        registerDefaultState(getStateDefinition().any().setValue(MATURITY, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(MATURITY);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(13) != 0) {
            return;
        }
        int maturity = state.getValue(MATURITY);
        if (!isMoistened(level, pos)) {
            return;
        }
        if (maturity < MATURITY_DELIMITER - 1) {
            level.setBlock(pos, state.setValue(MATURITY, maturity + 1), UPDATE_CLIENTS);
        } else {
            level.setBlock(pos, CoreBlocks.PEAT.block().defaultBlockState(), UPDATE_CLIENTS);
        }
    }

    private static boolean isMoistened(Level level, BlockPos pos) {
        for (BlockPos waterPos : BlockPos.betweenClosed(pos.offset(-2, -2, -2), pos.offset(2, 2, 2))) {
            if (level.getBlockState(waterPos).getBlock() == Blocks.WATER) {
                return true;
            }
        }
        return false;
    }
}
