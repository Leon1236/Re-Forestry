package com.leon1236.reforestry.api.genetics.pollen;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IPollenType {
    default Identifier id() {
        return com.leon1236.reforestry.ReForestry.id("pollen");
    }

    boolean canPollinate(Level level, BlockPos pos);

    Optional<IGenome> tryCollectPollen(Level level, BlockPos pos, RandomSource random);

    boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random);

    default boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random, boolean convertVanilla) {
        return tryPollinate(level, pos, pollen, random);
    }

    default ItemStack createStack(IGenome genome) {
        return ItemStack.EMPTY;
    }
}
