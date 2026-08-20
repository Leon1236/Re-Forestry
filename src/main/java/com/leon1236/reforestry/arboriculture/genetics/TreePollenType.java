package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollenType;
import com.leon1236.reforestry.arboriculture.TreeUtil;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;
import com.leon1236.reforestry.core.config.ForestryConfig;

public final class TreePollenType implements IPollenType {
    public static final TreePollenType INSTANCE = new TreePollenType();

    private TreePollenType() {
    }

    @Override
    public boolean canPollinate(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.getGenome() != null) {
            return true;
        }

        BlockState state = level.getBlockState(pos);
        if (state.hasProperty(LeavesBlock.PERSISTENT) && state.getValue(LeavesBlock.PERSISTENT)) {
            return false;
        }

        IGenome individual = ArboricultureGenetics.getVanillaIndividual(state);
        if (individual == null) {
            return false;
        }

        ItemStack decorative = individual.getActiveAllele(TreeChromosomes.SPECIES).value().getDecorativeLeaves();
        return decorative.isEmpty() || !decorative.is(state.getBlock().asItem());
    }

    @Override
    public Optional<IGenome> tryCollectPollen(Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.getGenome() != null) {
            return Optional.of(leaves.getGenome());
        }
        IGenome vanilla = ArboricultureGenetics.getVanillaIndividual(level.getBlockState(pos));
        return vanilla == null ? Optional.empty() : Optional.of(vanilla);
    }

    @Override
    public boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random) {
        return tryPollinate(level, pos, pollen, random, false);
    }

    @Override
    public boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random, boolean convertVanilla) {
        boolean convert = convertVanilla && ForestryConfig.pollinateVanillaLeaves();
        TileLeaves leaves = TreeUtil.getOrCreateLeaves(level, pos, convert);
        return leaves != null && TreeUtil.tryMate(leaves, pollen);
    }
}
