package com.leon1236.reforestry.core.features;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyDataComponents {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

	public static final FeatureDataComponent<BlockPos> HABITAT_LINKED_POS =
			REGISTRY.dataComponent("habitat_linked_pos", builder -> builder
					.persistent(BlockPos.CODEC)
					.networkSynchronized(BlockPos.STREAM_CODEC));

	public static final FeatureDataComponent<ResourceKey<Level>> HABITAT_LINKED_DIMENSION =
			REGISTRY.dataComponent("habitat_linked_dimension", builder -> builder
					.persistent(ResourceKey.codec(Registries.DIMENSION))
					.networkSynchronized(ResourceKey.streamCodec(Registries.DIMENSION)));

	public static final FeatureDataComponent<Boolean> HABITAT_PREVIEW =
			REGISTRY.dataComponent("habitat_preview", builder -> builder
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL));

	public static void init() {
	}
}
