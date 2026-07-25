package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IGenome;

public class DummyFruit implements IFruit {
    private final Identifier id;
    private final boolean dominant;

    public DummyFruit(Identifier id, boolean dominant) {
        this.id = id;
        this.dominant = dominant;
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Override
    public boolean isFruitLeaf() {
        return false;
    }

    @Override
    public int getColour(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime) {
        return 0xffffff;
    }

    @Override
    public int getDecorativeColor() {
        return 0xffffff;
    }

    @Override
    public float getFruitChance(IGenome genome) {
        return genome.getActiveAllele(TreeChromosomes.YIELD).value() * 2.5f;
    }

    @Override
    public int getRipeningPeriod() {
        return 0;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public List<Product> getSpecialties() {
        return List.of();
    }

    @Override
    public List<ItemStack> getFruits(IGenome genome, Level level, int ripeningTime) {
        return List.of();
    }

    @Nullable
    @Override
    public Identifier getSprite(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime) {
        return getDecorativeSprite();
    }

    @Override
    public boolean requiresFruitBlocks() {
        return false;
    }

    @Override
    public boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos) {
        return false;
    }
}
