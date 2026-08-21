package com.leon1236.reforestry.api.plugin;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.ITreeSpecies;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.genetics.ITreeSpeciesType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.IGenomeBuilder;

public interface ITreeSpeciesBuilder extends ISpeciesBuilder<ITreeSpeciesType, ITreeSpecies, ITreeSpeciesBuilder> {
	ITreeSpeciesBuilder setAuthority(String authority);

	ITreeSpeciesBuilder setGenome(Consumer<IGenomeBuilder> genome);

	ITreeSpeciesBuilder addMutations(Consumer<IMutationsRegistration> mutations);

	ITreeSpeciesBuilder setTreeFeature(Function<ITreeGenData, Feature<NoneFeatureConfiguration>> factory);

	ITreeSpeciesBuilder setGenerator(ITreeGenerator generator);

	ITreeSpeciesBuilder addVanillaStates(Collection<BlockState> states);

	ITreeSpeciesBuilder addVanillaSapling(Item sapling);

	ITreeSpeciesBuilder setDecorativeLeaves(Supplier<ItemStack> stack);

	default ITreeSpeciesBuilder setDecorativeLeaves(ItemStack stack) {
		return setDecorativeLeaves(() -> stack);
	}

	ITreeSpeciesBuilder setWoodType(IWoodType woodType);

	ITreeSpeciesBuilder setRarity(float rarity);

	ITreeSpeciesBuilder setTemperature(TemperatureType temperature);

	ITreeSpeciesBuilder setHumidity(HumidityType humidity);

	@Override
	ITreeSpeciesBuilder setEscritoireColor(int color);

	@Override
	default ITreeSpeciesBuilder setEscritoireColor(TextColor color) {
		return setEscritoireColor(color.getValue());
	}

	@Nullable
	ITreeGenerator getGenerator();

	List<BlockState> getVanillaLeafStates();

	List<Item> getVanillaSaplingItems();

	ItemStack getDecorativeLeaves();

	float getRarity();

	TemperatureType getTemperature();

	HumidityType getHumidity();
}
