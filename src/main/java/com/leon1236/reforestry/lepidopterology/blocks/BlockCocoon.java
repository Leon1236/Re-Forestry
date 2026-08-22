package com.leon1236.reforestry.lepidopterology.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.api.lepidopterology.genetics.ButterflyLifeStage;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.lepidopterology.tiles.TileCocoon;

public class BlockCocoon extends Block implements EntityBlock {
	public static final MapCodec<BlockCocoon> CODEC = simpleCodec(BlockCocoon::new);
	public static final VoxelShape BOUNDING_BOX = Block.box(5f, 5f, 5f, 11f, 16F, 11f);
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);

	public BlockCocoon(Properties properties) {
		super(properties.randomTicks().sound(SoundType.GRAVEL));
		registerDefaultState(getStateDefinition().any().setValue(AGE, 0));
	}

	@Override
	protected MapCodec<? extends BlockCocoon> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
		if (world.getBlockEntity(pos) instanceof TileCocoon tileCocoon && !tileCocoon.isRemoved()) {
			tileCocoon.onBlockTick();
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileCocoon(pos, state, false);
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos,
			Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (direction != Direction.UP || !neighborState.isAir()) {
			return state;
		}
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		if (!(level.getBlockEntity(pos) instanceof TileCocoon tile)) {
			return ItemStack.EMPTY;
		}
		IButterfly caterpillar = tile.getCaterpillar();
		if (caterpillar == null) {
			return ItemStack.EMPTY;
		}
		return caterpillar.createStack(ButterflyLifeStage.COCOON);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return BOUNDING_BOX;
	}
}
