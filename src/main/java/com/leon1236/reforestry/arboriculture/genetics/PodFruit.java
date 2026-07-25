package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IGenome;

public class PodFruit extends Fruit {
    private final String podType;

    public PodFruit(Identifier id, boolean dominant, String podType, List<Product> products) {
        super(id, dominant, 2, products);
        this.podType = podType;
    }

    public String getPodType() {
        return podType;
    }

    @Override
    public boolean requiresFruitBlocks() {
        return true;
    }

    @Override
    public boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos) {
        return false;
    }
}
