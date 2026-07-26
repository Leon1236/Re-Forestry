package com.leon1236.reforestry.core.features;

import com.mojang.serialization.Codec;

import net.minecraft.network.codec.ByteBufCodecs;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.genetics.ResearchNoteContents;
import com.leon1236.reforestry.modules.features.FeatureDataComponent;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreDataComponents {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

	public static final FeatureDataComponent<ResearchNoteContents> RESEARCH_NOTE =
			REGISTRY.dataComponent("research_note", builder -> builder
					.persistent(ResearchNoteContents.CODEC)
					.networkSynchronized(ResearchNoteContents.STREAM_CODEC));

	public static final FeatureDataComponent<Boolean> ANALYZED =
			REGISTRY.dataComponent("analyzed", builder -> builder
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL));

	public static void init() {
	}
}
