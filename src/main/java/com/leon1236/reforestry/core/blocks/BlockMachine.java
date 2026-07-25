package com.leon1236.reforestry.core.blocks;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.core.tiles.TileBase;
import com.leon1236.reforestry.core.tiles.TileUtil;

public abstract class BlockMachine<P extends Enum<P> & IBlockType> extends BaseEntityBlock {
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	public final P blockType;

	protected BlockMachine(P blockType, BlockBehaviour.Properties properties) {
		super(properties.strength(2.0f));
		this.blockType = blockType;
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
		blockType.getMachineProperties().setBlock(this);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
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
