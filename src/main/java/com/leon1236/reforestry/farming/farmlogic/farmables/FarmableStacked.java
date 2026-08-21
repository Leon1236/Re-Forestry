package com.leon1236.reforestry.farming.farmlogic.farmables;

import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmable;
import com.leon1236.reforestry.core.utils.BlockUtil;
import com.leon1236.reforestry.farming.farmlogic.crops.CropDestroy;

public class FarmableStacked implements IFarmable {
	protected final ItemStack germling;
	protected final Block cropBlock;
	protected final int matureHeight;
	protected final ItemStack fruit;

	public FarmableStacked(ItemStack germling, Block cropBlock, int matureHeight) {
		this(germling, germling, cropBlock, matureHeight);
	}

	public FarmableStacked(ItemStack germling, ItemStack fruit, Block cropBlock, int matureHeight) {
		this.germling = germling;
		this.fruit = fruit;
		this.cropBlock = cropBlock;
		this.matureHeight = matureHeight;
	}

	@Override
	public boolean isSaplingAt(Level level, BlockPos pos, BlockState state) {
		return state.getBlock() == this.cropBlock;
	}

	@Override
	public ICrop getCropAt(Level level, BlockPos pos, BlockState state) {
		BlockPos cropPos = pos.offset(0, this.matureHeight - 1, 0);
		if (!level.isLoaded(cropPos)) {
			return null;
		}
		state = level.getBlockState(cropPos);
		if (state.getBlock() != this.cropBlock) {
			return null;
		}

		return new CropDestroy(level, state, cropPos, null);
	}

	@Override
	public boolean isGermling(ItemStack stack) {
		return ItemStack.isSameItem(this.germling, stack);
	}

	@Override
	public void addGermlings(Consumer<ItemStack> accumulator) {
		accumulator.accept(this.germling);
	}

	@Override
	public void addProducts(Consumer<ItemStack> accumulator) {
		accumulator.accept(this.fruit);
	}

	@Override
	public boolean plantSaplingAt(Player player, ItemStack germling, Level level, BlockPos pos) {
		return BlockUtil.setBlockWithPlaceSound(level, pos, this.cropBlock.defaultBlockState());
	}

	@Override
	public boolean isWindfall(ItemStack stack) {
		return false;
	}
}
