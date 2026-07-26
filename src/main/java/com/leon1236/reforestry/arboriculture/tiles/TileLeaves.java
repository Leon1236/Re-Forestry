package com.leon1236.reforestry.arboriculture.tiles;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.api.core.ISpectacleBlock;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.genetics.IFruit;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.genetics.TreeMating;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;

public class TileLeaves extends TileTreeContainer implements ISpectacleBlock {
    private static final String NBT_MATE_GENOME = "MateGenome";
    private static final String NBT_RIPENING_TIME = "RipeningTime";
    private static final String NBT_IS_FRUIT_LEAF = "IsFruitLeaf";

    @Nullable
    private IGenome mateGenome;
    private boolean isFruitLeaf;
    private int ripeningTime;

    public TileLeaves(BlockPos pos, BlockState state) {
        super(ArboricultureTiles.LEAVES.type(), pos, state);
    }

    @Override
    public void setGenome(IGenome genome) {
        super.setGenome(genome);
        rollFruitLeaf();
    }

    @Nullable
    public IGenome getMateGenome() {
        return mateGenome;
    }

    public void setMateGenome(IGenome mateGenome) {
        this.mateGenome = mateGenome;
        markUpdated();
    }

    public boolean isPollinated() {
        return mateGenome != null;
    }

    @Override
    public boolean isHighlighted(Player player) {
        return isPollinated();
    }

    @Override
    public Object getRenderData() {
        IGenome genome = getGenome();
        Identifier species = genome == null ? null : genome.getActiveAllele(TreeChromosomes.SPECIES).value().id();
        if (genome == null || !isFruitLeaf || level == null) {
            return new LeafRenderData(species, isPollinated(), false, null, 0xFFFFFF);
        }
        IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        return new LeafRenderData(species, isPollinated(), true,
                fruit.getSprite(genome, level, getBlockPos(), ripeningTime),
                fruit.getColour(genome, level, getBlockPos(), ripeningTime));
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (mateGenome != null) {
            output.store(NBT_MATE_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec(), mateGenome);
        }
        output.putInt(NBT_RIPENING_TIME, ripeningTime);
        output.putBoolean(NBT_IS_FRUIT_LEAF, isFruitLeaf);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        mateGenome = input.read(NBT_MATE_GENOME, TreeChromosomes.KARYOTYPE.genomeCodec()).orElse(null);
        ripeningTime = input.getIntOr(NBT_RIPENING_TIME, 0);
        isFruitLeaf = input.getBooleanOr(NBT_IS_FRUIT_LEAF, false);
    }

    private void rollFruitLeaf() {
        IGenome genome = getGenome();
        if (genome == null) {
            isFruitLeaf = false;
            return;
        }
        IFruit fruit = genome.getActiveAllele(TreeChromosomes.FRUIT).value();
        isFruitLeaf = fruit.isFruitLeaf() && level != null
                && fruit.getFruitChance(genome) >= level.getRandom().nextFloat();
    }

    @Override
    public void onBlockTick(Level level, BlockPos pos, BlockState state, RandomSource random) {
        IGenome genome = getGenome();
        if (genome == null) {
            return;
        }

        if (hasFruit() && ripeningTime < getRipeningPeriod()) {
            float sappiness = genome.getActiveAllele(TreeChromosomes.SAPPINESS).value();
            if (random.nextFloat() < sappiness) {
                ripeningTime++;
                markUpdated();
            }
        }
    }

    private int getRipeningPeriod() {
        IGenome genome = getGenome();
        return genome == null ? Integer.MAX_VALUE : genome.getActiveAllele(TreeChromosomes.FRUIT).value().getRipeningPeriod();
    }

    public boolean hasFruit() {
        return isFruitLeaf;
    }

    public float getRipeness() {
        int period = getRipeningPeriod();
        if (period <= 0) {
            return 1.0f;
        }
        return Math.min(1.0f, ripeningTime / (float) period);
    }

    public List<ItemStack> pickFruit() {
        IGenome genome = getGenome();
        if (genome == null || !hasFruit() || level == null) {
            return List.of();
        }
        List<ItemStack> stacks = genome.getActiveAllele(TreeChromosomes.FRUIT).value().getFruits(genome, level, ripeningTime);
        ripeningTime = 0;
        markUpdated();
        return stacks;
    }

    public IGenome resolveSaplingGenome(RandomSource random) {
        IGenome genome = getGenome();
        if (genome == null || mateGenome == null || level == null) {
            return genome;
        }
        TreeMating.MatingResult result = random.nextBoolean()
                ? TreeMating.resolveOffspringGenome(genome, mateGenome, level, getBlockPos(), random)
                : TreeMating.resolveOffspringGenome(mateGenome, genome, level, getBlockPos(), random);
        result.mutation().ifPresent(this::onMutationDiscovered);
        onSpeciesDiscovered(result.genome().getActiveAllele(TreeChromosomes.SPECIES).value());
        return result.genome();
    }

    private void onMutationDiscovered(Mutation mutation) {
    }

    private void onSpeciesDiscovered(ITreeSpecies species) {
    }
}
