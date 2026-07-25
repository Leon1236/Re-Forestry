package com.leon1236.reforestry.core.climate;

import java.util.IdentityHashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.climate.ClimateState;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public final class ForestryClimateManager implements IClimateManager {
    private final IdentityHashMap<ResourceKey<Biome>, TemperatureType> temperatures = new IdentityHashMap<>();
    private final IdentityHashMap<ResourceKey<Biome>, HumidityType> humidities = new IdentityHashMap<>();

    @Override
    public TemperatureType getTemperature(Holder<Biome> biome) {
        return getTemperature(biome instanceof Holder.Reference<Biome> reference ? reference.key() : biome.unwrapKey().orElseThrow());
    }

    @Override
    public TemperatureType getTemperature(ResourceKey<Biome> biome) {
        return temperatures.getOrDefault(biome, TemperatureType.NORMAL);
    }

    @Override
    public HumidityType getHumidity(Holder<Biome> biome) {
        return getHumidity(biome instanceof Holder.Reference<Biome> reference ? reference.key() : biome.unwrapKey().orElseThrow());
    }

    @Override
    public HumidityType getHumidity(ResourceKey<Biome> biome) {
        return humidities.getOrDefault(biome, HumidityType.NORMAL);
    }

    @Override
    public IClimateProvider createClimateProvider(LevelReader world, BlockPos pos) {
        return new ClimateProvider(world, pos);
    }

    @Override
    public IClimateProvider createDummyClimateProvider() {
        return FakeClimateProvider.INSTANCE;
    }

    @Override
    public ClimateState getState(ServerLevel level, BlockPos pos) {
        return getBiomeState(level, pos);
    }

    @Override
    public ClimateState getBiomeState(LevelReader level, BlockPos coordinates) {
        Holder<Biome> biome = level.getBiome(coordinates);
        return new ClimateState(getTemperature(biome), getHumidity(biome));
    }

    public void onBiomesReloaded(Registry<Biome> registry) {
        temperatures.clear();
        humidities.clear();

        registry.listElements().forEach(holder -> {
            boolean hasTemperatureTag = false;
            boolean hasHumidityTag = false;

            for (TemperatureType temperature : TemperatureType.VALUES) {
                if (holder.is(temperature.tag)) {
                    temperatures.put(holder.key(), temperature);
                    hasTemperatureTag = true;
                    break;
                }
            }
            for (HumidityType humidity : HumidityType.VALUES) {
                if (holder.is(humidity.tag)) {
                    humidities.put(holder.key(), humidity);
                    hasHumidityTag = true;
                    break;
                }
            }
            if (!hasTemperatureTag) {
                temperatures.put(holder.key(), TemperatureType.getFromValue(holder.value().getBaseTemperature()));
            }
            if (!hasHumidityTag) {
                humidities.put(holder.key(), HumidityType.getFromValue(BiomeDownfall.get(holder.value())));
            }
        });
    }
}
