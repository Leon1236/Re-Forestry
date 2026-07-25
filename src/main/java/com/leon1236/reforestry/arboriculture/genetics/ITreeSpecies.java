package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ITreeGenData;
import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;

public interface ITreeSpecies extends IRegistryAlleleValue, ITreeGenData {
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
}
