package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.pollen.IPollenType;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public final class TreePollenType implements IPollenType {
    public static final TreePollenType INSTANCE = new TreePollenType();

    private TreePollenType() {
    }

    @Override
    public boolean canPollinate(Level level, BlockPos pos) {
        return level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.getGenome() != null;
    }

    @Override
    public Optional<IGenome> tryCollectPollen(Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves && leaves.getGenome() != null) {
            return Optional.of(leaves.getGenome());
        }
        return Optional.empty();
    }

    @Override
    public boolean tryPollinate(Level level, BlockPos pos, IGenome pollen, RandomSource random) {
        if (!(level.getBlockEntity(pos) instanceof TileLeaves leaves)) {
            return false;
        }
        IGenome genome = leaves.getGenome();
        if (genome == null || leaves.isPollinated() || genome.isSameAlleles(pollen)) {
            return false;
        }
        leaves.setMateGenome(pollen);
        return true;
    }
}
