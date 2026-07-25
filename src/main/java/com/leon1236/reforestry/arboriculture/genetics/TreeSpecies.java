package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.arboriculture.ITreeGenerator;
import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

record TreeSpecies(
        Identifier id,
        String genus,
        String species,
        boolean dominant,
        int escritoireColor,
        IWoodType woodType,
        String authority,
        TemperatureType temperature,
        HumidityType humidity,
        ITreeGenerator generator,
        List<BlockState> vanillaLeafStates,
        List<Item> vanillaSaplingItems,
        ItemStack decorativeLeaves,
        float rarity) implements ITreeSpecies {
    @Override
    public ITreeGenerator getGenerator() {
        return generator;
    }

    @Override
    public ItemStack getDecorativeLeaves() {
        return decorativeLeaves;
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
    public List<BlockState> getVanillaLeafStates() {
        return vanillaLeafStates;
    }

    @Override
    public List<Item> getVanillaSaplingItems() {
        return vanillaSaplingItems;
    }

    @Override
    public float getRarity() {
        return rarity;
    }
}
