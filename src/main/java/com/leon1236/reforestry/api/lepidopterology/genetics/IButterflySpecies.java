package com.leon1236.reforestry.api.lepidopterology.genetics;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.core.IClimateSensitive;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface IButterflySpecies extends IRegistryAlleleValue, ISpecies<IButterfly>, IClimateSensitive {
	@Override
	IButterflySpeciesType getType();

	@Nullable
	TagKey<Biome> getSpawnBiomes();

	float getRarity();

	float getFlightDistance();

	boolean isNocturnal();

	boolean isMoth();

	List<? extends IProduct> getButterflyLoot();

	List<? extends IProduct> getCaterpillarProducts();

	int getSerumColor();

	@Override
	default int getEscritoireColor() {
		return getSerumColor();
	}
}
