package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

record TreeSpecies(
        Identifier id,
        String genus,
        String species,
        boolean dominant,
        int escritoireColor,
        IWoodType woodType,
        String authority,
        TemperatureType temperature,
        HumidityType humidity,
        ITreeGenerator generator,
        List<BlockState> vanillaLeafStates,
        List<Item> vanillaSaplingItems,
        Supplier<ItemStack> decorativeLeaves,
        float rarity) implements ITreeSpecies {
    @Override
    public ITreeGenerator getGenerator() {
        return generator;
    }

    @Override
    public ItemStack getDecorativeLeaves() {
        return decorativeLeaves.get();
    }

    @Override
    public TemperatureType getTemperature() {
        return temperature;
    }

    @Override
    public HumidityType getHumidity() {
        return humidity;
    }

    @Override
    public List<BlockState> getVanillaLeafStates() {
        return vanillaLeafStates;
    }

    @Override
    public List<Item> getVanillaSaplingItems() {
        return vanillaSaplingItems;
    }

    @Override
    public float getRarity() {
        return rarity;
    }

    @Override
    public int getGirth(IGenome genome) {
        return genome.getActiveAllele(TreeChromosomes.GIRTH).value();
    }

    @Override
    public float getHeightModifier(IGenome genome) {
        return genome.getActiveAllele(TreeChromosomes.HEIGHT).value();
    }

    @Nullable
    @Override
    public BlockPos getGrowthPos(IGenome genome, LevelAccessor level, BlockPos pos, int expectedGirth, int expectedHeight) {
        return TreeGrowthHelper.getGrowthPos(level, genome, pos, expectedGirth, expectedHeight);
    }

    @Override
    public boolean setLeaves(IGenome genome, LevelAccessor level, BlockPos pos, RandomSource random, boolean convertBlockEntity) {
        if (convertBlockEntity) {
            BlockState state = ArboricultureBlocks.LEAVES.block().defaultBlockState();
            boolean placed = level.setBlock(pos, state, 19);
            if (!placed) {
                return false;
            }
            if (level.getBlockEntity(pos) instanceof TileLeaves leaves) {
                leaves.setGenome(genome);
                return true;
            }
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 19);
            return false;
        }
        return getGenerator().setLeaves(genome, level, pos, random);
    }

    @Override
    public boolean setLogBlock(IGenome genome, LevelAccessor level, BlockPos pos, Direction facing) {
        return getGenerator().setLogBlock(genome, level, pos, facing);
    }

    @Override
    public boolean trySpawnFruitBlock(IGenome genome, LevelAccessor level, RandomSource rand, BlockPos pos) {
        return genome.getActiveAllele(TreeChromosomes.FRUIT).value().trySpawnFruitBlock(genome, level, rand, pos);
    }

    @Override
    public IGenome getDefaultGenome() {
        return ArboricultureGenetics.getDefaultGenome(id);
    }
}
