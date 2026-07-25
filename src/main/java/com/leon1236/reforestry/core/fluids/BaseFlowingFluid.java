package com.leon1236.reforestry.core.fluids;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FlowingFluid;

public abstract class BaseFlowingFluid extends FlowingFluid {
    private final Holder holder;

    protected BaseFlowingFluid(Holder holder) {
        this.holder = holder;
    }

    @Override
    public Fluid getFlowing() {
        return this.holder.flowing.get();
    }

    @Override
    public Fluid getSource() {
        return this.holder.source.get();
    }

    @Override
    public Item getBucket() {
        return this.holder.bucket.get();
    }

    @Override
    protected boolean canConvertToSource(ServerLevel level) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    @Override
    public int getDropOff(LevelReader level) {
        return 1;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 5;
    }

    @Override
    public BlockState createLegacyBlock(FluidState fluidState) {
        return this.holder.block.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(fluidState));
    }

    @Override
    public boolean isSame(Fluid other) {
        return other == this.holder.source.get() || other == this.holder.flowing.get();
    }

    @Override
    public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction) {
        return false;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    public static final class Source extends BaseFlowingFluid {
        public Source(Holder holder) {
            super(holder);
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return true;
        }
    }

    public static final class Flowing extends BaseFlowingFluid {
        public Flowing(Holder holder) {
            super(holder);
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState fluidState) {
            return fluidState.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState fluidState) {
            return false;
        }
    }

    public static final class Holder {
        Supplier<? extends Fluid> source;
        Supplier<? extends Fluid> flowing;
        Supplier<? extends Block> block;
        Supplier<? extends Item> bucket;
    }
}
