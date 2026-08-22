package com.leon1236.reforestry.core.blocks;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import com.leon1236.reforestry.core.tiles.TileNaturalistChest;
import com.leon1236.reforestry.core.tiles.TileUtil;

public class BlockNaturalistChest extends BlockMachine<NaturalistChestBlockType> {
	private static final VoxelShape CHEST_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	private final MapCodec<BlockNaturalistChest> codec;

	public BlockNaturalistChest(NaturalistChestBlockType type, BlockBehaviour.Properties properties) {
		super(type, properties.sound(SoundType.WOOD).noOcclusion());
		this.codec = simpleCodec(props -> new BlockNaturalistChest(type, props));
	}

	@Override
	protected MapCodec<? extends BlockNaturalistChest> codec() {
		return codec;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return CHEST_SHAPE;
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide()) {
			TileNaturalistChest tile = TileUtil.getTile(level, pos, TileNaturalistChest.class);
			if (tile != null) {
				Containers.dropContents(level, pos, tile);
			}
		}
		return super.playerWillDestroy(level, pos, state, player);
	}
}
