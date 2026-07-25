package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;

import com.leon1236.reforestry.api.apiculture.hives.IHiveDrop;
import com.leon1236.reforestry.api.genetics.IGenome;

public class HiveDrop implements IHiveDrop {
    private final Identifier speciesId;
    private final double chance;
    private final Supplier<List<ItemStack>> bonus;
    private final double ignobleChance;

    public HiveDrop(double chance, Identifier speciesId, Supplier<List<ItemStack>> bonus, float ignobleChance) {
        this.speciesId = speciesId;
        this.chance = chance;
        this.bonus = bonus;
        this.ignobleChance = ignobleChance;
    }

    @Override
    public IGenome createGenome(BlockGetter level, BlockPos pos) {
        return ApicultureGenetics.getDefaultGenome(speciesId);
    }

    @Override
    public List<ItemStack> getExtraItems(BlockGetter level, BlockPos pos, int fortune) {
        ArrayList<ItemStack> result = new ArrayList<>();
        for (ItemStack stack : bonus.get()) {
            result.add(stack.copy());
        }
        return result;
    }

    @Override
    public double getChance(BlockGetter level, BlockPos pos, int fortune) {
        return chance;
    }

    @Override
    public double getIgnobleChance(BlockGetter level, BlockPos pos, int fortune) {
        return ignobleChance;
    }
}
