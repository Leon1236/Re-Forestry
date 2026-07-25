package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.plugin.IBeeSpeciesBuilder;
import com.leon1236.reforestry.api.plugin.IMutationsRegistration;
import com.leon1236.reforestry.core.genetics.mutations.MutationsRegistration;

public final class BeeSpeciesBuilder implements IBeeSpeciesBuilder {
    private final Identifier id;
    private final String genus;
    private final String species;
    private final boolean dominant;
    private final int outlineColor;
    private int bodyColor;
    private int stripesColor;
    private boolean secret = false;
    private boolean glint = false;
    private String authority = "";
    private TemperatureType temperature = TemperatureType.NORMAL;
    private HumidityType humidity = HumidityType.NORMAL;
    private final List<IBeeSpecies.Product> products = new ArrayList<>();
    private final List<IBeeSpecies.Product> specialties = new ArrayList<>();
    private Consumer<IGenomeBuilder> genome = builder -> {
    };
    private final MutationsRegistration mutations = new MutationsRegistration();

    BeeSpeciesBuilder(Identifier id, String genus, String species, boolean dominant, int outlineColor) {
        this.id = id;
        this.genus = genus;
        this.species = species;
        this.dominant = dominant;
        this.outlineColor = outlineColor;
        this.bodyColor = outlineColor;
        this.stripesColor = outlineColor;
    }

    @Override
    public BeeSpeciesBuilder setBodyColor(int color) {
        this.bodyColor = color;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setStripesColor(int color) {
        this.stripesColor = color;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setSecret(boolean secret) {
        this.secret = secret;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setGlint(boolean glint) {
        this.glint = glint;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setTemperature(TemperatureType temperature) {
        this.temperature = temperature;
        return this;
    }

    @Override
    public BeeSpeciesBuilder setHumidity(HumidityType humidity) {
        this.humidity = humidity;
        return this;
    }

    @Override
    public BeeSpeciesBuilder addProduct(Item item, float chance) {
        products.add(new IBeeSpecies.Product(item, chance));
        return this;
    }

    @Override
    public BeeSpeciesBuilder addProduct(Item item, int count, float chance) {
        products.add(new IBeeSpecies.Product(item, count, chance));
        return this;
    }

    @Override
    public BeeSpeciesBuilder addSpecialty(Item item, float chance) {
        specialties.add(new IBeeSpecies.Product(item, chance));
        return this;
    }

    @Override
    public BeeSpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome) {
        this.genome = genome;
        return this;
    }

    @Override
    public BeeSpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations) {
        mutations.accept(this.mutations);
        return this;
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

    IBeeSpecies buildSpecies() {
        return new BeeSpecies(id, genus, species, dominant, outlineColor, bodyColor, stripesColor, secret, glint,
                authority, temperature, humidity, List.copyOf(products), List.copyOf(specialties));
    }

    IGenome buildGenome(IRegistryAllele<IBeeSpecies> speciesAllele) {
        IGenomeBuilder builder = BeeChromosomes.KARYOTYPE.genomeBuilder().set(BeeChromosomes.SPECIES, speciesAllele);
        genome.accept(builder);
        return builder.build();
    }
}
