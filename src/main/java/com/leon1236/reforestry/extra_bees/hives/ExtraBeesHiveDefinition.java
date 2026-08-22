package com.leon1236.reforestry.extra_bees.hives;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.apiculture.hives.IHiveDefinition;
import com.leon1236.reforestry.api.apiculture.hives.IHiveGen;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.core.ToleranceType;
import com.leon1236.reforestry.api.genetics.ClimateHelper;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.extra_bees.blocks.EnumExtraBeeHive;
import com.leon1236.reforestry.extra_bees.features.ExtraBeesBlocks;

public enum ExtraBeesHiveDefinition implements IHiveDefinition {
	WATER(EnumExtraBeeHive.WATER, 2.0f, new HiveGenWater()),
	ROCK(EnumExtraBeeHive.ROCK, 6.0f, new HiveGenRock()),
	NETHER(EnumExtraBeeHive.NETHER, 6.0f, new HiveGenNether()),
	MARBLE(EnumExtraBeeHive.MARBLE, 20.0f, new HiveGenMarble());

	private final EnumExtraBeeHive hiveType;
	private final float genChance;
	private final IHiveGen hiveGen;

	ExtraBeesHiveDefinition(EnumExtraBeeHive hiveType, float genChance, IHiveGen hiveGen) {
		this.hiveType = hiveType;
		this.genChance = genChance;
		this.hiveGen = hiveGen;
	}

	@Override
	public IHiveGen getHiveGen() {
		return hiveGen;
	}

	@Override
	public BlockState getBlockState() {
		return ExtraBeesBlocks.BEEHIVE.get(hiveType).block().defaultBlockState();
	}

	@Override
	public boolean isGoodBiome(Holder<Biome> biome) {
		if (hiveType == EnumExtraBeeHive.NETHER) {
			return biome.is(BiomeTags.IS_NETHER);
		}
		return true;
	}

	@Override
	public boolean isGoodHumidity(HumidityType humidity) {
		Identifier speciesId = hiveType.getSpeciesId();
		IBeeSpecies species = ApicultureGenetics.getSpecies(speciesId);
		IGenome genome = ApicultureGenetics.getDefaultGenome(speciesId);
		HumidityType idealHumidity = species.getHumidity();
		ToleranceType humidityTolerance = genome.getActiveAllele(BeeChromosomes.HUMIDITY_TOLERANCE).value();
		return ClimateHelper.isWithinLimits(humidity, idealHumidity, humidityTolerance);
	}

	@Override
	public boolean isGoodTemperature(TemperatureType temperature) {
		Identifier speciesId = hiveType.getSpeciesId();
		IBeeSpecies species = ApicultureGenetics.getSpecies(speciesId);
		IGenome genome = ApicultureGenetics.getDefaultGenome(speciesId);
		TemperatureType idealTemperature = species.getTemperature();
		ToleranceType temperatureTolerance = genome.getActiveAllele(BeeChromosomes.TEMPERATURE_TOLERANCE).value();
		return ClimateHelper.isWithinLimits(temperature, idealTemperature, temperatureTolerance);
	}

	@Override
	public float getGenChance() {
		return genChance;
	}

	@Override
	public void postGen(WorldGenLevel level, RandomSource rand, BlockPos pos) {
	}
}
