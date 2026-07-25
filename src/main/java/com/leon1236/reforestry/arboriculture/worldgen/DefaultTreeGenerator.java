package com.leon1236.reforestry.arboriculture.worldgen;

import java.util.Objects;
import java.util.function.Function;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.WoodAccess;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public final class DefaultTreeGenerator implements ITreeGenerator {
    private final Function<ITreeGenData, Feature<NoneFeatureConfiguration>> factory;
    private final IWoodType woodType;

    public DefaultTreeGenerator(Function<ITreeGenData, Feature<NoneFeatureConfiguration>> factory, IWoodType woodType) {
        this.factory = Objects.requireNonNull(factory);
        this.woodType = Objects.requireNonNull(woodType);
    }

    @Override
    public Feature<NoneFeatureConfiguration> getTreeFeature(ITreeGenData tree) {
        return this.factory.apply(tree);
    }

    @Override
    public boolean setLogBlock(IGenome genome, LevelAccessor level, BlockPos pos, Direction facing) {
        boolean fireproof = genome.getActiveAllele(TreeChromosomes.FIREPROOF).value();
        BlockState logBlock = WoodAccess.INSTANCE.getBlockState(this.woodType, WoodBlockKind.LOG, fireproof);
        Direction.Axis axis = facing.getAxis();
        return level.setBlock(pos, logBlock.setValue(RotatedPillarBlock.AXIS, axis), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
    }

    @Override
    public boolean setLeaves(IGenome genome, LevelAccessor level, BlockPos pos, RandomSource rand) {
        ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
        boolean isDefaultGenome = genome.isSameAlleles(ArboricultureGenetics.getDefaultGenome(species.id()));
        if (isDefaultGenome && level instanceof WorldGenLevel) {
            return this.woodType.setDefaultLeaves(level, pos, genome, rand, null);
        }

        BlockState leaves = ArboricultureBlocks.LEAVES.block().defaultBlockState();
        boolean placed = level.setBlock(pos, leaves, 19);
        if (!placed) {
            return false;
        }
        if (!(level.getBlockEntity(pos) instanceof TileLeaves tileLeaves)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 19);
            return false;
        }
        tileLeaves.setGenome(genome);
        return true;
    }
}
