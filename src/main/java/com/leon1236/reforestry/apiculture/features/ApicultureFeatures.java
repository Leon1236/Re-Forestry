package com.leon1236.reforestry.apiculture.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.hives.HiveDecorator;
import com.leon1236.reforestry.apiculture.worldgen.ApiaristPoolElement;

public final class ApicultureFeatures {
    public static final HiveDecorator HIVE = Registry.register(BuiltInRegistries.FEATURE, ReForestry.id("hive"),
            new HiveDecorator());

    public static final StructurePoolElementType<ApiaristPoolElement> APIARIST_POOL_ELEMENT_TYPE =
            Registry.register(
                    BuiltInRegistries.STRUCTURE_POOL_ELEMENT,
                    ReForestry.id("apiarist"),
                    () -> ApiaristPoolElement.CODEC);

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_HIVE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ReForestry.id("hive"));
    public static final ResourceKey<PlacedFeature> PLACED_HIVE =
            ResourceKey.create(Registries.PLACED_FEATURE, ReForestry.id("hive"));

    private ApicultureFeatures() {
    }

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.all(),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                PLACED_HIVE);
    }
}
