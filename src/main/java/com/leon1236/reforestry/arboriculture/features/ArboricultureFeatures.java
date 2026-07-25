package com.leon1236.reforestry.arboriculture.features;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.worldgen.TreeDecorator;
import com.leon1236.reforestry.arboriculture.worldgen.feature.ForestryTreeFeature;

public final class ArboricultureFeatures {
    public static final TreeDecorator TREE = Registry.register(BuiltInRegistries.FEATURE, ReForestry.id("tree"),
            new TreeDecorator());

    public static final ForestryTreeFeature CUSTOM_TREE =
            Registry.register(BuiltInRegistries.FEATURE, ReForestry.id("custom_tree"), new ForestryTreeFeature());

    public static final ResourceKey<ConfiguredFeature<?, ?>> CONFIGURED_TREE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ReForestry.id("tree"));
    public static final ResourceKey<PlacedFeature> PLACED_TREE =
            ResourceKey.create(Registries.PLACED_FEATURE, ReForestry.id("tree"));

    private ArboricultureFeatures() {
    }

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.all(),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                PLACED_TREE);
    }
}
