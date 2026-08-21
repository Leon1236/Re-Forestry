package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpeciesType;
import com.leon1236.reforestry.api.plugin.IButterflySpeciesBuilder;
import com.leon1236.reforestry.api.plugin.ISpeciesBuilder;

public final class ButterflySpeciesBuilder implements IButterflySpeciesBuilder {
	private final Identifier id;
	private final String genus;
	private final String species;
	private boolean dominant;
	private int serumColor;
	private float rarity;
	private float flightDistance = 5.0f;
	private boolean nocturnal;
	private boolean moth;
	@Nullable
	private TagKey<Biome> spawnBiomes;
	private boolean glint;
	private boolean secret;
	private int complexity = 1;
	private String authority = "";
	private TemperatureType temperature = TemperatureType.NORMAL;
	private HumidityType humidity = HumidityType.NORMAL;
	private Consumer<IGenomeBuilder> genome = builder -> {
	};
	private final List<IProduct> products = new ArrayList<>();
	private final List<IProduct> caterpillarProducts = new ArrayList<>();
	private ISpeciesBuilder.ISpeciesFactory<IButterflySpeciesType, IButterflySpecies, IButterflySpeciesBuilder> factory;

	public ButterflySpeciesBuilder(Identifier id, String genus, String species, boolean dominant, int serumColor, float rarity) {
		this.id = id;
		this.genus = genus;
		this.species = species;
		this.dominant = dominant;
		this.serumColor = serumColor;
		this.rarity = rarity;
	}

	@Override
	public ButterflySpeciesBuilder setDominant(boolean dominant) {
		this.dominant = dominant;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome) {
		this.genome = genome;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setGlint(boolean glint) {
		this.glint = glint;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setTemperature(TemperatureType temperature) {
		this.temperature = temperature;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setHumidity(HumidityType humidity) {
		this.humidity = humidity;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setComplexity(int complexity) {
		this.complexity = complexity;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setEscritoireColor(int color) {
		this.serumColor = color;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setSecret(boolean secret) {
		this.secret = secret;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setAuthority(String authority) {
		this.authority = authority;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setFactory(ISpeciesBuilder.ISpeciesFactory<IButterflySpeciesType, IButterflySpecies, IButterflySpeciesBuilder> factory) {
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
		return serumColor;
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
	public ISpeciesBuilder.ISpeciesFactory<IButterflySpeciesType, IButterflySpecies, IButterflySpeciesBuilder> createSpeciesFactory() {
		return factory;
	}

	@Override
	public ButterflySpeciesBuilder setSerumColor(int color) {
		this.serumColor = color;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setFlightDistance(float flightDistance) {
		this.flightDistance = flightDistance;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setNocturnal(boolean nocturnal) {
		this.nocturnal = nocturnal;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setMoth(boolean moth) {
		this.moth = moth;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setSpawnBiomes(TagKey<Biome> biomeTag) {
		this.spawnBiomes = biomeTag;
		return this;
	}

	@Override
	public ButterflySpeciesBuilder setRarity(float rarity) {
		this.rarity = rarity;
		return this;
	}

	@Override
	public int getSerumColor() {
		return serumColor;
	}

	@Override
	public float getFlightDistance() {
		return flightDistance;
	}

	@Override
	public boolean isNocturnal() {
		return nocturnal;
	}

	@Override
	public boolean isMoth() {
		return moth;
	}

	@Override
	@Nullable
	public TagKey<Biome> getSpawnBiomes() {
		return spawnBiomes;
	}

	@Override
	public float getRarity() {
		return rarity;
	}

	@Override
	public List<IProduct> buildProducts() {
		return List.copyOf(products);
	}

	@Override
	public List<IProduct> buildCaterpillarProducts() {
		return List.copyOf(caterpillarProducts);
	}

	Identifier id() {
		return id;
	}
}
