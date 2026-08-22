package com.leon1236.reforestry.arboriculture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;

public final class TreeUtil {
    private TreeUtil() {
    }

    @Nullable
    public static IGenome getTreeSafe(Level level, BlockPos pos) {
        if (!level.isLoaded(pos)) {
            return null;
        }

        if (level.getBlockEntity(pos) instanceof TileLeaves leaves) {
            return leaves.getGenome();
        }

        BlockState state = level.getBlockState(pos);
        return ArboricultureGenetics.getVanillaIndividual(state);
    }

    @Nullable
    public static TileLeaves getOrCreateLeaves(Level level, BlockPos pos, boolean convertVanilla) {
        if (!level.isLoaded(pos)) {
            return null;
        }
        if (level.getBlockEntity(pos) instanceof TileLeaves leaves) {
            return leaves;
        }
        if (!convertVanilla) {
            return null;
        }

        IGenome genome = getTreeSafe(level, pos);
        if (genome == null) {
            return null;
        }

        ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
        species.setLeaves(genome, level, pos, level.getRandom(), true);

        if (level.getBlockEntity(pos) instanceof TileLeaves leaves) {
            return leaves;
        }
        return null;
    }

    public static boolean tryMate(TileLeaves leaves, IGenome pollen) {
        if (leaves.isPollinated()) {
            return false;
        }
        IGenome tree = leaves.getGenome();
        if (canMate(tree, pollen)) {
            leaves.setMateGenome(pollen);
            return true;
        }
        return false;
    }

    public static boolean canMate(@Nullable IGenome leaves, IGenome pollen) {
        return leaves != null
                && (ModuleArboriculture.doSelfPollination || !leaves.isSameAlleles(pollen));
    }
}
