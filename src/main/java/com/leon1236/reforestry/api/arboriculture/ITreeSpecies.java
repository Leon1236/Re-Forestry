package com.leon1236.reforestry.api.arboriculture;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.genetics.ITree;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.core.IProductProducer;
import com.leon1236.reforestry.api.core.ISpecialtyProducer;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface ITreeSpecies extends IRegistryAlleleValue, ITreeGenData, ISpecies<ITree>, IProductProducer, ISpecialtyProducer {
	String genus();

	String species();

	boolean dominant();

	int escritoireColor();

	IWoodType woodType();

	String authority();

	ITreeGenerator getGenerator();

	ItemStack getDecorativeLeaves();

	TemperatureType getTemperature();

	HumidityType getHumidity();

	List<BlockState> getVanillaLeafStates();

	List<Item> getVanillaSaplingItems();

	float getRarity();

	default int getGermlingColor(ILifeStage stage, int renderPass) {
		return escritoireColor();
	}

	default boolean isFruitLeaf(LevelAccessor level, BlockPos pos) {
		return false;
	}

	@Override
	default List<? extends IProduct> getProducts() {
		return List.of();
	}

	@Override
	default List<? extends IProduct> getSpecialties() {
		return List.of();
	}
}
