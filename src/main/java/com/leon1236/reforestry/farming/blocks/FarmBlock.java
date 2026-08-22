package com.leon1236.reforestry.farming.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;

import com.leon1236.reforestry.core.blocks.BlockStructure;
import com.leon1236.reforestry.farming.multiblock.TileFarmControl;
import com.leon1236.reforestry.farming.multiblock.TileFarmGearbox;
import com.leon1236.reforestry.farming.multiblock.TileFarmHatch;
import com.leon1236.reforestry.farming.multiblock.TileFarmPlain;
import com.leon1236.reforestry.farming.multiblock.TileFarmValve;

public class FarmBlock extends BlockStructure {
	public static final BooleanProperty BAND = BooleanProperty.create("band");

	private final EnumFarmBlockType type;
	private final EnumFarmMaterial farmMaterial;
	private final MapCodec<FarmBlock> codec;

	public FarmBlock(EnumFarmBlockType type, EnumFarmMaterial farmMaterial, Properties properties) {
		super(properties);
		this.type = type;
		this.farmMaterial = farmMaterial;
		this.codec = simpleCodec(props -> create(type, farmMaterial, props));
	}

	public static FarmBlock create(EnumFarmBlockType type, EnumFarmMaterial material, Properties properties) {
		if (type == EnumFarmBlockType.PLAIN) {
			return new Plain(material, properties);
		}
		return new FarmBlock(type, material, properties);
	}

	@Override
	protected MapCodec<? extends FarmBlock> codec() {
		return this.codec;
	}

	public EnumFarmBlockType getType() {
		return this.type;
	}

	public EnumFarmMaterial getFarmMaterial() {
		return this.farmMaterial;
	}

	@Override
	protected boolean isSignalSource(BlockState state) {
		return this.type == EnumFarmBlockType.CONTROL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return switch (this.type) {
			case GEARBOX -> new TileFarmGearbox(pos, state);
			case HATCH -> new TileFarmHatch(pos, state);
			case VALVE -> new TileFarmValve(pos, state);
			case CONTROL -> new TileFarmControl(pos, state);
			case PLAIN -> new TileFarmPlain(pos, state);
		};
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		ensureBlockEntity(level, pos, state);
		return super.useWithoutItem(state, level, pos, player, hit);
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
			@Nullable Orientation orientation, boolean movedByPiston) {
		ensureBlockEntity(level, pos, state);
	}

	private void ensureBlockEntity(Level level, BlockPos pos, BlockState state) {
		if (level.isClientSide() || level.getBlockEntity(pos) != null) {
			return;
		}
		BlockEntity created = newBlockEntity(pos, state);
		if (created != null) {
			level.setBlockEntity(created);
		}
	}

	public static class Plain extends FarmBlock {
		public Plain(EnumFarmMaterial farmMaterial, Properties properties) {
			super(EnumFarmBlockType.PLAIN, farmMaterial, properties);
			registerDefaultState(defaultBlockState().setValue(BAND, false));
		}

		@Override
		protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
			builder.add(BAND);
		}
	}
}
