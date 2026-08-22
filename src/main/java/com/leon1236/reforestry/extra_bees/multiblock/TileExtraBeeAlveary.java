package com.leon1236.reforestry.extra_bees.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.multiblock.IAlvearyComponent;
import com.leon1236.reforestry.api.multiblock.IMultiblockController;
import com.leon1236.reforestry.apiculture.multiblock.MultiblockLogicAlveary;
import com.leon1236.reforestry.core.multiblock.MultiblockTileEntityForestry;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;

public abstract class TileExtraBeeAlveary extends MultiblockTileEntityForestry<MultiblockLogicAlveary>
		implements IAlvearyComponent<MultiblockLogicAlveary> {
	private final BlockExtraBeeAlvearyType type;

	protected TileExtraBeeAlveary(BlockEntityType<?> blockEntityType, BlockExtraBeeAlvearyType type, BlockPos pos,
			BlockState state) {
		super(blockEntityType, pos, state, new MultiblockLogicAlveary());
		this.type = type;
	}

	public BlockExtraBeeAlvearyType getAlvearyType() {
		return type;
	}

	@Override
	public void onMachineAssembled(IMultiblockController multiblockController, BlockPos minCoord, BlockPos maxCoord) {
	}

	@Override
	public void onMachineBroken() {
		setChanged();
	}

	public Component getDisplayName() {
		return getBlockState().getBlock().getName();
	}

	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}
}
