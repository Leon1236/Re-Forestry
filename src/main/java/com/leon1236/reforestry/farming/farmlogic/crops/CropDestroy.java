package com.leon1236.reforestry.farming.farmlogic.crops;

import java.util.Iterator;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.core.utils.BlockUtil;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public class CropDestroy extends Crop {
	protected final BlockState blockState;
	@Nullable
	protected final BlockState replantState;
	protected final ItemStack germling;

	public CropDestroy(Level world, BlockState blockState, BlockPos position, @Nullable BlockState replantState) {
		this(world, blockState, position, replantState, ItemStack.EMPTY);
	}

	public CropDestroy(Level world, BlockState blockState, BlockPos position, @Nullable BlockState replantState, ItemStack germling) {
		super(world, position);
		this.blockState = blockState;
		this.replantState = replantState;
		this.germling = germling;
	}

	@Override
	protected boolean isCrop(Level world, BlockPos pos) {
		return world.getBlockState(pos).is(this.blockState.getBlock());
	}

	@Override
	protected List<ItemStack> harvestBlock(Level level, BlockPos pos) {
		List<ItemStack> harvested = Block.getDrops(this.blockState, (ServerLevel) level, pos, level.getBlockEntity(pos));
		if (!(harvested instanceof ObjectArrayList)) {
			harvested = ObjectArrayList.wrap(harvested.toArray(ItemStack[]::new));
		}
		boolean removedSeed = this.germling.isEmpty();
		Iterator<ItemStack> dropIterator = harvested.iterator();
		while (dropIterator.hasNext()) {
			ItemStack next = dropIterator.next();

			if (!removedSeed && ItemStack.isSameItem(next, this.germling)) {
				next.shrink(1);
				if (next.isEmpty()) {
					dropIterator.remove();
				}
				removedSeed = true;
			}
		}

		if (this.replantState != null) {
			BlockUtil.sendDestroyEffects(level, pos, this.blockState);
			level.setBlock(pos, this.replantState, Block.UPDATE_CLIENTS);
		} else {
			level.destroyBlock(pos, false);
		}

		return harvested;
	}

	@Override
	public String toString() {
		return String.format("CropDestroy [ position: [ %s ]; block: %s ]", this.position, this.blockState);
	}
}
