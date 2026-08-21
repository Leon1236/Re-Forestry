package com.leon1236.reforestry.api.plugin;

import java.util.List;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpeciesType;

public interface IButterflySpeciesBuilder extends ISpeciesBuilder<IButterflySpeciesType, IButterflySpecies, IButterflySpeciesBuilder> {
	IButterflySpeciesBuilder setSerumColor(int color);

	default IButterflySpeciesBuilder setSerumColor(TextColor color) {
		return setSerumColor(color.getValue());
	}

	IButterflySpeciesBuilder setFlightDistance(float flightDistance);

	IButterflySpeciesBuilder setNocturnal(boolean nocturnal);

	IButterflySpeciesBuilder setMoth(boolean moth);

	IButterflySpeciesBuilder setSpawnBiomes(TagKey<Biome> biomeTag);

	IButterflySpeciesBuilder setRarity(float rarity);

	IButterflySpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations);

	int getSerumColor();

	float getFlightDistance();

	boolean isNocturnal();

	boolean isMoth();

	@Nullable
	TagKey<Biome> getSpawnBiomes();

	float getRarity();

	List<IProduct> buildProducts();

	List<IProduct> buildCaterpillarProducts();
}
