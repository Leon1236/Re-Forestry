package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IFruit extends IRegistryAlleleValue {
    boolean isDominant();

    boolean isFruitLeaf();

    int getColour(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime);

    int getDecorativeColor();

    float getFruitChance(IGenome genome);

    int getRipeningPeriod();

    List<Product> getProducts();

    List<Product> getSpecialties();

    List<ItemStack> getFruits(IGenome genome, Level level, int ripeningTime);

    @Nullable
    Identifier getSprite(IGenome genome, BlockGetter world, BlockPos pos, int ripeningTime);

    @Nullable
    default Identifier getDecorativeSprite() {
        return null;
    }

    boolean requiresFruitBlocks();

    boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos);

    record Product(Item item, float chance) {
    }
}
