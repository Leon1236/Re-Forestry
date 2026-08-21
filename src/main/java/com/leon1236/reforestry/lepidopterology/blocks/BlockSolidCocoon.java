package com.leon1236.reforestry.lepidopterology.blocks;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.lepidopterology.tiles.TileCocoon;

public class BlockSolidCocoon extends Block implements EntityBlock {
	public static final MapCodec<BlockSolidCocoon> CODEC = simpleCodec(BlockSolidCocoon::new);

	public BlockSolidCocoon(Properties properties) {
		super(properties.strength(0.5F).randomTicks().sound(SoundType.GRAVEL));
		registerDefaultState(getStateDefinition().any().setValue(BlockCocoon.AGE, 0));
	}

	@Override
	protected MapCodec<? extends BlockSolidCocoon> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockCocoon.AGE);
	}

	@Override
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state,
			@Nullable BlockEntity blockEntity, ItemStack itemStack) {
		if (blockEntity instanceof TileCocoon cocoon) {
			List<ItemStack> drops = cocoon.getCocoonDrops();
			for (ItemStack stack : drops) {
				Block.popResource(level, pos, stack);
			}
		}
		super.playerDestroy(level, player, pos, state, blockEntity, itemStack);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileCocoon(pos, state, true);
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
	protected VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return BlockCocoon.BOUNDING_BOX;
	}
}
