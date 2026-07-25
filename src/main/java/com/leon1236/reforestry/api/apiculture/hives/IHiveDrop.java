package com.leon1236.reforestry.api.apiculture.hives;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IHiveDrop {
    IGenome createGenome(BlockGetter level, BlockPos pos);

    List<ItemStack> getExtraItems(BlockGetter level, BlockPos pos, int fortune);

    double getChance(BlockGetter level, BlockPos pos, int fortune);

    double getIgnobleChance(BlockGetter level, BlockPos pos, int fortune);
}
