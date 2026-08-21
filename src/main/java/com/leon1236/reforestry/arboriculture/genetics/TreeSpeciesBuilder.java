package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeSpeciesType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.plugin.IMutationsRegistration;
import com.leon1236.reforestry.api.plugin.ISpeciesBuilder;
import com.leon1236.reforestry.api.plugin.ITreeSpeciesBuilder;
import com.leon1236.reforestry.arboriculture.worldgen.DefaultTreeGenerator;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureTreeVanilla;
import com.leon1236.reforestry.core.genetics.mutations.MutationsRegistration;

public final class TreeSpeciesBuilder implements ITreeSpeciesBuilder {
    private final Identifier id;
    private final String genus;
    private final String species;
    private boolean dominant;
    private int escritoireColor;
    private IWoodType woodType;
    private String authority = "";
    private boolean glint = false;
    private boolean secret = false;
    private int complexity = 1;
    private Consumer<IGenomeBuilder> genome = builder -> {
    };
    private final MutationsRegistration mutations = new MutationsRegistration();
    @Nullable
    private ITreeGenerator generator = null;
    private float rarity = 0.0f;
    private TemperatureType temperature = TemperatureType.NORMAL;
    private HumidityType humidity = HumidityType.NORMAL;
    private final HashSet<BlockState> vanillaStates = new HashSet<>();
    private final HashSet<Item> vanillaItems = new HashSet<>();
    private Supplier<ItemStack> decorativeLeaves = () -> ItemStack.EMPTY;
    private ISpeciesBuilder.ISpeciesFactory<ITreeSpeciesType, com.leon1236.reforestry.api.arboriculture.ITreeSpecies, ITreeSpeciesBuilder> factory;

    TreeSpeciesBuilder(Identifier id, String genus, String species, boolean dominant, int escritoireColor, IWoodType woodType) {
        this.id = id;
        this.genus = genus;
        this.species = species;
        this.dominant = dominant;
        this.escritoireColor = escritoireColor;
        this.woodType = woodType;
    }

    @Override
    public TreeSpeciesBuilder setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome) {
        this.genome = genome;
        return this;
    }

    @Override
    public TreeSpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations) {
        mutations.accept(this.mutations);
        return this;
    }

    @Override
    public TreeSpeciesBuilder setTreeFeature(Function<ITreeGenData, Feature<NoneFeatureConfiguration>> factory) {
        if (this.woodType == null) {
            throw new IllegalStateException("Must call setWoodType before setTreeFeature");
        }
        return setGenerator(new DefaultTreeGenerator(factory, this.woodType));
    }

    @Override
    public TreeSpeciesBuilder setGenerator(ITreeGenerator generator) {
        this.generator = generator;
        return this;
    }

    @Override
    public TreeSpeciesBuilder addVanillaStates(Collection<BlockState> states) {
        this.vanillaStates.addAll(states);
        return this;
    }

    @Override
    public TreeSpeciesBuilder addVanillaSapling(Item sapling) {
        this.vanillaItems.add(sapling);
        return this;
    }

    @Override
    public TreeSpeciesBuilder setDecorativeLeaves(Supplier<ItemStack> stack) {
        this.decorativeLeaves = stack;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setWoodType(IWoodType woodType) {
        this.woodType = woodType;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setRarity(float rarity) {
        if (rarity < 0f || rarity > 1f) {
            throw new IllegalArgumentException("Tree species rarity must be between 0 and 1.");
        }
        this.rarity = rarity;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setTemperature(TemperatureType temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setHumidity(HumidityType humidity) {
        this.humidity = humidity;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setDominant(boolean dominant) {
        this.dominant = dominant;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setGlint(boolean glint) {
        this.glint = glint;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setSecret(boolean secret) {
        this.secret = secret;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setComplexity(int complexity) {
        this.complexity = complexity;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setEscritoireColor(int color) {
        this.escritoireColor = color;
        return this;
    }

    @Override
    public TreeSpeciesBuilder setFactory(ISpeciesBuilder.ISpeciesFactory<ITreeSpeciesType, com.leon1236.reforestry.api.arboriculture.ITreeSpecies, ITreeSpeciesBuilder> factory) {
        this.factory = factory;
        return this;
    }

    @Override
    public String getGenus() {
        return genus;
    }

    @Override
    public String getSpecies() {
        return species;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Override
    public IGenome buildGenome(IGenomeBuilder builder) {
        genome.accept(builder);
        return builder.build();
    }

    @Override
    public boolean hasGlint() {
        return glint;
    }

    @Override
    public int getComplexity() {
        return complexity;
    }

    @Override
    public int getEscritoireColor() {
        return escritoireColor;
    }

    @Override
    public boolean isSecret() {
        return secret;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public ISpeciesBuilder.ISpeciesFactory<ITreeSpeciesType, com.leon1236.reforestry.api.arboriculture.ITreeSpecies, ITreeSpeciesBuilder> createSpeciesFactory() {
        if (factory != null) {
            return factory;
        }
        return (id, type, defaultGenome, builder) -> ((TreeSpeciesBuilder) builder).buildSpecies();
    }

    @Nullable
    @Override
    public ITreeGenerator getGenerator() {
        return this.generator;
    }

    @Override
    public List<BlockState> getVanillaLeafStates() {
        return List.copyOf(this.vanillaStates);
    }

    @Override
    public List<Item> getVanillaSaplingItems() {
        return List.copyOf(this.vanillaItems);
    }

    @Override
    public ItemStack getDecorativeLeaves() {
        return this.decorativeLeaves.get();
    }

    @Override
    public float getRarity() {
        return this.rarity;
    }

    @Override
    public TemperatureType getTemperature() {
        return this.temperature;
    }

    @Override
    public HumidityType getHumidity() {
        return this.humidity;
    }

    boolean dominant() {
        return dominant;
    }

    Identifier id() {
        return id;
    }

    MutationsRegistration mutations() {
        return mutations;
    }

    ITreeSpecies buildSpecies() {
        ITreeGenerator resolvedGenerator = this.generator;
        if (resolvedGenerator == null) {
            resolvedGenerator = new DefaultTreeGenerator(FeatureTreeVanilla::new, this.woodType);
        }
        return new TreeSpecies(
                id,
                genus,
                species,
                dominant,
                escritoireColor,
                woodType,
                authority,
                temperature,
                humidity,
                resolvedGenerator,
                getVanillaLeafStates(),
                getVanillaSaplingItems(),
                decorativeLeaves,
                rarity,
                secret,
                glint,
                complexity);
    }

    IGenome buildGenome(IRegistryAllele<ITreeSpecies> speciesAllele) {
        IGenomeBuilder builder = TreeChromosomes.KARYOTYPE.genomeBuilder().set(TreeChromosomes.SPECIES, speciesAllele);
        genome.accept(builder);
        return builder.build();
    }
}
