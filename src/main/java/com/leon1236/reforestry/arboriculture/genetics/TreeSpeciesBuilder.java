package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.plugin.IMutationsRegistration;
import com.leon1236.reforestry.api.plugin.ITreeSpeciesBuilder;
import com.leon1236.reforestry.arboriculture.worldgen.DefaultTreeGenerator;
import com.leon1236.reforestry.arboriculture.worldgen.FeatureTreeVanilla;
import com.leon1236.reforestry.core.genetics.mutations.MutationsRegistration;

public final class TreeSpeciesBuilder implements ITreeSpeciesBuilder {
    private final Identifier id;
    private final String genus;
    private final String species;
    private final boolean dominant;
    private final int escritoireColor;
    private IWoodType woodType;
    private String authority = "";
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
    private ItemStack decorativeLeaves = ItemStack.EMPTY;

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
    public TreeSpeciesBuilder setDecorativeLeaves(ItemStack stack) {
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
        return this.decorativeLeaves;
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
                rarity);
    }

    IGenome buildGenome(IRegistryAllele<ITreeSpecies> speciesAllele) {
        IGenomeBuilder builder = TreeChromosomes.KARYOTYPE.genomeBuilder().set(TreeChromosomes.SPECIES, speciesAllele);
        genome.accept(builder);
        return builder.build();
    }
}
