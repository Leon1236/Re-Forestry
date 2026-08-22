package com.leon1236.reforestry.sorting.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.sorting.features.SortingTiles;
import com.leon1236.reforestry.sorting.tiles.TileGeneticFilter;

public class BlockGeneticFilter extends BaseEntityBlock {
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");

	private static final VoxelShape BOX_CENTER = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
	private static final VoxelShape BOX_DOWN = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 5.0D, 12.0D);
	private static final VoxelShape BOX_UP = Block.box(4.0D, 11.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	private static final VoxelShape BOX_NORTH = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 5.0D);
	private static final VoxelShape BOX_SOUTH = Block.box(4.0D, 4.0D, 11.0D, 12.0D, 12.0D, 16.0D);
	private static final VoxelShape BOX_WEST = Block.box(0.0D, 4.0D, 4.0D, 5.0D, 12.0D, 12.0D);
	private static final VoxelShape BOX_EAST = Block.box(11.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);

	private final MapCodec<BlockGeneticFilter> codec;

	public BlockGeneticFilter(BlockBehaviour.Properties properties) {
		super(properties.strength(0.25f, 3.0f).dynamicShape().noOcclusion());
		this.codec = simpleCodec(BlockGeneticFilter::new);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(NORTH, false)
				.setValue(EAST, false)
				.setValue(SOUTH, false)
				.setValue(WEST, false)
				.setValue(UP, false)
				.setValue(DOWN, false));
	}

	@Override
	protected MapCodec<? extends BlockGeneticFilter> codec() {
		return this.codec;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		VoxelShape shape = BOX_CENTER;
		if (state.getValue(DOWN)) {
			shape = Shapes.or(shape, BOX_DOWN);
		}
		if (state.getValue(UP)) {
			shape = Shapes.or(shape, BOX_UP);
		}
		if (state.getValue(NORTH)) {
			shape = Shapes.or(shape, BOX_NORTH);
		}
		if (state.getValue(SOUTH)) {
			shape = Shapes.or(shape, BOX_SOUTH);
		}
		if (state.getValue(WEST)) {
			shape = Shapes.or(shape, BOX_WEST);
		}
		if (state.getValue(EAST)) {
			shape = Shapes.or(shape, BOX_EAST);
		}
		return shape;
	}

	@Override
	protected BlockState updateShape(
			BlockState state,
			LevelReader level,
			ScheduledTickAccess ticks,
			BlockPos pos,
			Direction directionToNeighbour,
			BlockPos neighbourPos,
			BlockState neighbourState,
			RandomSource random
	) {
		TileGeneticFilter geneticFilter = TileUtil.getTile(level, pos, TileGeneticFilter.class);
		if (geneticFilter == null) {
			return defaultBlockState();
		}
		return state.setValue(NORTH, geneticFilter.isConnected(Direction.NORTH))
				.setValue(EAST, geneticFilter.isConnected(Direction.EAST))
				.setValue(SOUTH, geneticFilter.isConnected(Direction.SOUTH))
				.setValue(WEST, geneticFilter.isConnected(Direction.WEST))
				.setValue(UP, geneticFilter.isConnected(Direction.UP))
				.setValue(DOWN, geneticFilter.isConnected(Direction.DOWN));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		TileGeneticFilter tile = TileUtil.getTile(level, pos, TileGeneticFilter.class);
		if (tile == null || !tile.isUsableByPlayer(player)) {
			return InteractionResult.PASS;
		}
		if (!level.isClientSide()) {
			player.openMenu(tile);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected InteractionResult useItemOn(
			ItemStack stack,
			BlockState state,
			Level level,
			BlockPos pos,
			Player player,
			InteractionHand hand,
			BlockHitResult hitResult
	) {
		return useWithoutItem(state, level, pos, player, hitResult).consumesAction()
				? InteractionResult.SUCCESS
				: InteractionResult.TRY_WITH_EMPTY_HAND;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof Container container) {
			Containers.dropContents(level, pos, container);
		}
		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
		Containers.updateNeighboursAfterDestroy(state, level, pos);
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileGeneticFilter(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> actualType) {
		if (level.isClientSide() || actualType != SortingTiles.GENETIC_FILTER.type()) {
			return null;
		}
		return (BlockEntityTicker<T>) (lvl, blockPos, blockState, tile) -> {
			if (tile instanceof TileGeneticFilter filter) {
				TileGeneticFilter.serverTick(lvl, blockPos, blockState, filter);
			}
		};
	}
}
