package com.leon1236.reforestry.core.climate;

import java.util.HashMap;
import java.util.Map;

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
    private final Map<ResourceKey<Biome>, TemperatureType> temperatures = new HashMap<>();
    private final Map<ResourceKey<Biome>, HumidityType> humidities = new HashMap<>();

    @Override
    public TemperatureType getTemperature(Holder<Biome> biome) {
        ResourceKey<Biome> key = biomeKey(biome);
        if (key != null) {
            TemperatureType cached = temperatures.get(key);
            if (cached != null) {
                return cached;
            }
        }
        return resolveTemperature(biome);
    }

    @Override
    public TemperatureType getTemperature(ResourceKey<Biome> biome) {
        TemperatureType cached = temperatures.get(biome);
        return cached != null ? cached : TemperatureType.NORMAL;
    }

    @Override
    public HumidityType getHumidity(Holder<Biome> biome) {
        ResourceKey<Biome> key = biomeKey(biome);
        if (key != null) {
            HumidityType cached = humidities.get(key);
            if (cached != null) {
                return cached;
            }
        }
        return resolveHumidity(biome);
    }

    @Override
    public HumidityType getHumidity(ResourceKey<Biome> biome) {
        HumidityType cached = humidities.get(biome);
        return cached != null ? cached : HumidityType.NORMAL;
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
            temperatures.put(holder.key(), resolveTemperature(holder));
            humidities.put(holder.key(), resolveHumidity(holder));
        });
    }

    private static ResourceKey<Biome> biomeKey(Holder<Biome> biome) {
        if (biome instanceof Holder.Reference<Biome> reference) {
            return reference.key();
        }
        return biome.unwrapKey().orElse(null);
    }

    private static TemperatureType resolveTemperature(Holder<Biome> biome) {
        for (TemperatureType temperature : TemperatureType.VALUES) {
            if (biome.is(temperature.tag)) {
                return temperature;
            }
        }
        return TemperatureType.getFromValue(biome.value().getBaseTemperature());
    }

    private static HumidityType resolveHumidity(Holder<Biome> biome) {
        for (HumidityType humidity : HumidityType.VALUES) {
            if (biome.is(humidity.tag)) {
                return humidity;
            }
        }
        return HumidityType.getFromValue(BiomeDownfall.get(biome.value()));
    }
}
