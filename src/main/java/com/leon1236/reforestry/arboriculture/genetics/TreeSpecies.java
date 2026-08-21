package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.genetics.ITree;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.AllelePair;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.ITaxon;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.tiles.TileLeaves;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;
import com.leon1236.reforestry.core.genetics.Taxon;

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
        float rarity,
        boolean secret,
        boolean glint,
        int complexity) implements ITreeSpecies {
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

    @Override
    public String getTranslationKey() {
        return "allele.reforestry.tree_species." + id.getPath();
    }

    @Override
    public String getBinomial() {
        if (genus.isEmpty()) {
            return species;
        }
        return Character.toUpperCase(genus.charAt(0)) + genus.substring(1) + " " + species;
    }

    @Override
    public String getSpeciesName() {
        return species;
    }

    @Override
    public ITaxon getGenus() {
        ITaxon taxon = com.leon1236.reforestry.api.IForestryApi.INSTANCE.getGeneticManager().getTaxonSafe(genus);
        return taxon != null ? taxon : Taxon.nameOnly(genus);
    }

    @Override
    public ISpeciesType<? extends ITreeSpecies, ITree> getType() {
        return TreeSpeciesType.INSTANCE;
    }

    @Override
    public boolean isSecret() {
        return secret;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

    @Override
    public ITree createIndividual(Map<IChromosome<?>, IAllele> alleles) {
        return createIndividual(getDefaultGenome().copyWith(alleles));
    }

    @Override
    public ITree createIndividualFromPairs(Map<IChromosome<?>, AllelePair<?>> allelePairs) {
        return createIndividual(getDefaultGenome().copyWithPairs(allelePairs));
    }

    @Override
    public ITree createIndividual(IGenome genome) {
        if (genome.karyotype() != TreeChromosomes.KARYOTYPE) {
            throw new IllegalArgumentException("Genome karyotype does not match tree species");
        }
        return new Tree(genome);
    }

    @Override
    public boolean hasGlint() {
        return glint;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public int getEscritoireColor() {
        return escritoireColor;
    }

    @Override
    public void addTooltip(ITree individual, List<Component> tooltip) {
        GeneticsTooltips.addHybridTooltip(tooltip::add, individual.getGenome(), TreeChromosomes.SPECIES, "for.trees.hybrid");
        if (!individual.isAnalyzed()) {
            tooltip.add(Component.literal("<").append(Component.translatable("for.gui.unknown")).append(">")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
