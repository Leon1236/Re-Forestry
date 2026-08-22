package com.leon1236.reforestry.farming.farmlogic.crops;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.agriculture.ICrop;

public abstract class Crop implements ICrop {
	private final Level world;
	protected final BlockPos position;

	protected Crop(Level world, BlockPos position) {
		this.world = world;
		this.position = position;
	}

	protected abstract boolean isCrop(Level world, BlockPos pos);

	protected abstract List<ItemStack> harvestBlock(Level world, BlockPos pos);

	@Nullable
	@Override
	public List<ItemStack> harvest() {
		if (!isCrop(this.world, this.position)) {
			return null;
		}
		return harvestBlock(this.world, this.position);
	}

	@Override
	public BlockPos getPosition() {
		return this.position;
	}
}
