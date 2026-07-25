package com.leon1236.reforestry.arboriculture.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.worldgen.SimpleTreeGenerator;

public class TileSapling extends TileTreeContainer {
    private static final String NBT_TIMES_TICKED = "TimesTicked";

    private int timesTicked = 0;

    public TileSapling(BlockPos pos, BlockState state) {
        super(ArboricultureTiles.SAPLING.type(), pos, state);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt(NBT_TIMES_TICKED, timesTicked);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        timesTicked = input.getIntOr(NBT_TIMES_TICKED, 0);
    }

    @Override
    public void onBlockTick(Level level, BlockPos pos, BlockState state, RandomSource random) {
        timesTicked++;
        tryGrow(random, false);
    }

    private int getRequiredMaturity() {
        IGenome genome = getGenome();
        return genome == null ? Integer.MAX_VALUE : genome.getActiveAllele(TreeChromosomes.MATURATION).value();
    }

    public boolean canAcceptBoneMeal(RandomSource random) {
        IGenome genome = getGenome();
        if (genome == null) {
            return false;
        }
        if (timesTicked < getRequiredMaturity()) {
            return true;
        }
        return SimpleTreeGenerator.findGrowthOrigin(level, genome, getBlockPos()) != null;
    }

    public void tryGrow(RandomSource random, boolean boneMealed) {
        IGenome genome = getGenome();
        if (genome == null) {
            return;
        }

        int maturity = getRequiredMaturity();
        if (timesTicked < maturity) {
            if (boneMealed) {
                timesTicked = maturity;
            }
            return;
        }

        SimpleTreeGenerator.grow(level, genome, random, getBlockPos());
    }
}
