package com.leon1236.reforestry.farming.farmlogic.farmables;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.agriculture.ICrop;
import com.leon1236.reforestry.api.agriculture.IFarmable;

public class FarmableInfo implements IFarmable {
	private final List<ItemStack> germlings;
	private final List<ItemStack> products;

	public FarmableInfo(List<ItemStack> germlings, List<ItemStack> products) {
		this.germlings = List.copyOf(germlings);
		this.products = List.copyOf(products);
	}

	@Override
	public boolean isSaplingAt(Level level, BlockPos pos, BlockState state) {
		return false;
	}

	@Override
	@Nullable
	public ICrop getCropAt(Level level, BlockPos pos, BlockState state) {
		return null;
	}

	@Override
	public boolean isGermling(ItemStack stack) {
		for (ItemStack germling : this.germlings) {
			if (ItemStack.isSameItem(germling, stack)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGermlings(Consumer<ItemStack> accumulator) {
		for (ItemStack germling : this.germlings) {
			accumulator.accept(germling);
		}
	}

	@Override
	public void addProducts(Consumer<ItemStack> accumulator) {
		for (ItemStack product : this.products) {
			accumulator.accept(product);
		}
	}

	@Override
	public boolean isWindfall(ItemStack stack) {
		return false;
	}

	@Override
	public boolean plantSaplingAt(Player player, ItemStack germling, Level level, BlockPos pos) {
		return false;
	}
}
