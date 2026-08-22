package com.leon1236.reforestry.api.agriculture;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IFarmable {
	boolean isSaplingAt(Level level, BlockPos pos, BlockState state);

	@Nullable
	ICrop getCropAt(Level level, BlockPos pos, BlockState state);

	boolean isGermling(ItemStack stack);

	default void addGermlings(Consumer<ItemStack> accumulator) {
	}

	default void addProducts(Consumer<ItemStack> accumulator) {
	}

	boolean isWindfall(ItemStack stack);

	boolean plantSaplingAt(Player player, ItemStack germling, Level level, BlockPos pos);
}
