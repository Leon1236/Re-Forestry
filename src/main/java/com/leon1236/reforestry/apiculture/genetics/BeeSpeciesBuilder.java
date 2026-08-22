package com.leon1236.reforestry.apiculture.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.apiculture.IBeeJubilance;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpeciesType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.plugin.IBeeSpeciesBuilder;
import com.leon1236.reforestry.api.plugin.IMutationsRegistration;
import com.leon1236.reforestry.api.plugin.ISpeciesBuilder;
import com.leon1236.reforestry.core.genetics.SpeciesGenomeHelper;
import com.leon1236.reforestry.core.genetics.mutations.MutationsRegistration;

public final class BeeSpeciesBuilder implements IBeeSpeciesBuilder {
	private final Identifier id;
	private final String genus;
	private final String species;
	private boolean dominant;
	private int outlineColor;
	private int bodyColor;
	private int stripesColor;
	private boolean secret = false;
	private boolean glint = false;
	private String authority = "";
	private TemperatureType temperature = TemperatureType.NORMAL;
	private HumidityType humidity = HumidityType.NORMAL;
	private int complexity = 1;
	private final List<IBeeSpecies.Product> products = new ArrayList<>();
	private final List<IBeeSpecies.Product> specialties = new ArrayList<>();
	private IBeeJubilance jubilance = DefaultBeeJubilance.INSTANCE;
	private Consumer<IGenomeBuilder> genome = builder -> {
	};
	private final MutationsRegistration mutations = new MutationsRegistration();
	private ISpeciesBuilder.ISpeciesFactory<IBeeSpeciesType, com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies, IBeeSpeciesBuilder> factory;

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
	public BeeSpeciesBuilder setOutline(int color) {
		this.outlineColor = color;
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
	public BeeSpeciesBuilder setDominant(boolean dominant) {
		this.dominant = dominant;
		return this;
	}

	@Override
	public BeeSpeciesBuilder setComplexity(int complexity) {
		this.complexity = complexity;
		return this;
	}

	@Override
	public BeeSpeciesBuilder setEscritoireColor(int color) {
		this.outlineColor = color;
		return this;
	}

	@Override
	public BeeSpeciesBuilder setFactory(ISpeciesBuilder.ISpeciesFactory<IBeeSpeciesType, com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies, IBeeSpeciesBuilder> factory) {
		this.factory = factory;
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
		return addSpecialty(item, 1, chance);
	}

	@Override
	public BeeSpeciesBuilder addSpecialty(Item item, int count, float chance) {
		specialties.add(new IBeeSpecies.Product(item, count, chance));
		return this;
	}

	@Override
	public BeeSpeciesBuilder setJubilance(IBeeJubilance jubilance) {
		this.jubilance = jubilance;
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
	public TemperatureType getTemperature() {
		return temperature;
	}

	@Override
	public HumidityType getHumidity() {
		return humidity;
	}

	@Override
	public int getComplexity() {
		return complexity;
	}

	@Override
	public int getEscritoireColor() {
		return outlineColor;
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
	public ISpeciesBuilder.ISpeciesFactory<IBeeSpeciesType, com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies, IBeeSpeciesBuilder> createSpeciesFactory() {
		if (factory != null) {
			return factory;
		}
		return (id, type, defaultGenome, builder) -> ((BeeSpeciesBuilder) builder).buildSpecies();
	}

	@Override
	public List<IProduct> buildProducts() {
		return List.copyOf(products);
	}

	@Override
	public List<IProduct> buildSpecialties() {
		return List.copyOf(specialties);
	}

	@Override
	public int getBody() {
		return bodyColor;
	}

	@Override
	public int getStripes() {
		return stripesColor;
	}

	@Override
	public int getOutline() {
		return outlineColor;
	}

	@Override
	public IBeeJubilance getJubilance() {
		return jubilance;
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
				authority, temperature, humidity, List.copyOf(products), List.copyOf(specialties), jubilance, complexity);
	}

	IGenome buildGenome(IRegistryAllele<IBeeSpecies> speciesAllele) {
		IGenomeBuilder builder = SpeciesGenomeHelper.createDefaultGenomeBuilder(
				BeeChromosomes.KARYOTYPE, genus, BeeChromosomes.SPECIES, speciesAllele);
		genome.accept(builder);
		return builder.build();
	}
}
