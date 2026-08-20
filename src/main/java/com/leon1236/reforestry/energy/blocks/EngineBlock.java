package com.leon1236.reforestry.energy.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.core.blocks.IMachineProperties;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.items.ItemWrench;
import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.core.tiles.TileUtil;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;

public class EngineBlock extends BaseEntityBlock {
	private static final VoxelShape[] SHAPE_FOR_DIRECTIONS = new VoxelShape[6];

	public static final EnumProperty<Direction> VERTICAL_FACING = EnumProperty.create(
			"facing",
			Direction.class,
			Direction.NORTH,
			Direction.EAST,
			Direction.SOUTH,
			Direction.WEST,
			Direction.DOWN,
			Direction.UP);

	static {
		SHAPE_FOR_DIRECTIONS[Direction.EAST.ordinal()] = Shapes.or(
				Block.box(0, 0, 0, 6, 16, 16),
				Block.box(6, 2, 2, 10, 14, 14),
				Block.box(10, 4, 4, 16, 12, 12));
		SHAPE_FOR_DIRECTIONS[Direction.WEST.ordinal()] = Shapes.or(
				Block.box(0, 4, 4, 6, 12, 12),
				Block.box(6, 2, 2, 10, 14, 14),
				Block.box(10, 0, 0, 16, 16, 16));
		SHAPE_FOR_DIRECTIONS[Direction.SOUTH.ordinal()] = Shapes.or(
				Block.box(0, 0, 0, 16, 16, 6),
				Block.box(2, 2, 6, 14, 14, 10),
				Block.box(4, 4, 10, 12, 12, 16));
		SHAPE_FOR_DIRECTIONS[Direction.NORTH.ordinal()] = Shapes.or(
				Block.box(4, 4, 0, 12, 12, 6),
				Block.box(2, 2, 6, 14, 14, 10),
				Block.box(0, 0, 10, 16, 16, 16));
		SHAPE_FOR_DIRECTIONS[Direction.UP.ordinal()] = Shapes.or(
				Block.box(0, 0, 0, 16, 6, 16),
				Block.box(2, 6, 2, 14, 10, 14),
				Block.box(4, 10, 4, 12, 16, 12));
		SHAPE_FOR_DIRECTIONS[Direction.DOWN.ordinal()] = Shapes.or(
				Block.box(0, 10, 0, 16, 16, 16),
				Block.box(2, 6, 2, 14, 10, 14),
				Block.box(4, 0, 4, 12, 6, 12));
	}

	public final EngineBlockType blockType;
	private final MapCodec<EngineBlock> codec;

	public EngineBlock(EngineBlockType blockType, BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.METAL).strength(2.0f).noOcclusion());
		this.blockType = blockType;
		this.codec = simpleCodec(props -> new EngineBlock(blockType, props));
		registerDefaultState(stateDefinition.any().setValue(VERTICAL_FACING, Direction.UP));
		blockType.getMachineProperties().setBlock(this);
	}

	@Override
	protected MapCodec<? extends EngineBlock> codec() {
		return codec;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(VERTICAL_FACING);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_FOR_DIRECTIONS[state.getValue(VERTICAL_FACING).ordinal()];
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		Direction facing = state.getValue(VERTICAL_FACING);
		int steps = switch (rotation) {
			case NONE -> 0;
			case CLOCKWISE_90 -> 1;
			case CLOCKWISE_180 -> 2;
			case COUNTERCLOCKWISE_90 -> 3;
		};
		if (steps == 0) {
			return state;
		}
		Direction[] values = Direction.values();
		return state.setValue(VERTICAL_FACING, values[(facing.ordinal() + steps) % values.length]);
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(VERTICAL_FACING)));
	}

	private static boolean isOrientedAtEnergyReceiver(LevelAccessor world, BlockPos pos, Direction orientation) {
		BlockPos offsetPos = pos.relative(orientation);
		if (world instanceof Level level && !level.isLoaded(offsetPos)) {
			return false;
		}
		BlockEntity tile = TileUtil.getTile(world, offsetPos);
		return EnergyHelper.isEnergyReceiverOrEngine(orientation.getOpposite(), tile);
	}

	private static BlockState rotateTowardReceiver(BlockState state, LevelAccessor world, BlockPos pos) {
		Direction blockFacing = state.getValue(VERTICAL_FACING);
		Direction[] values = Direction.values();
		for (int i = blockFacing.ordinal() + 1; i <= blockFacing.ordinal() + values.length; i++) {
			Direction orientation = values[i % values.length];
			if (isOrientedAtEnergyReceiver(world, pos, orientation)) {
				return state.setValue(VERTICAL_FACING, orientation);
			}
		}
		return state;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction orientation = context.getClickedFace().getOpposite();
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		if (isOrientedAtEnergyReceiver(world, pos, orientation)) {
			return defaultBlockState().setValue(VERTICAL_FACING, orientation);
		}
		return rotateTowardReceiver(
				defaultBlockState().setValue(VERTICAL_FACING, context.getHorizontalDirection()),
				world,
				pos);
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
		EngineBlockEntity tile = TileUtil.getTile(level, pos, EngineBlockEntity.class);
		return tile == null ? 0 : tile.calculateRedstone();
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
		return getMachineProperties().createTileEntity(pos, state);
	}

	@Nullable
	@Override
	@SuppressWarnings("unchecked")
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> actualType) {
		if (actualType != getMachineProperties().getTeType()) {
			return null;
		}
		return (BlockEntityTicker<T>) (level.isClientSide()
				? getMachineProperties().getClientTicker()
				: getMachineProperties().getServerTicker());
	}

	private IMachineProperties<?> getMachineProperties() {
		return blockType.getMachineProperties();
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
		TileBase tile = TileUtil.getTile(level, pos, TileBase.class);
		if (tile == null || !tile.isUsableByPlayer(player)) {
			return InteractionResult.PASS;
		}

		if (stack.getItem() instanceof ItemWrench) {
			return InteractionResult.PASS;
		}

		if (!player.isShiftKeyDown()
				&& FluidHelper.interactWithTank(player, hand, level, pos, hitResult.getDirection())) {
			return InteractionResult.SUCCESS;
		}

		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		tile.openGui(player);
		return InteractionResult.SUCCESS;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		TileBase tile = TileUtil.getTile(level, pos, TileBase.class);
		if (tile == null || !tile.isUsableByPlayer(player)) {
			return InteractionResult.PASS;
		}

		tile.openGui(player);
		return InteractionResult.SUCCESS;
	}
}
