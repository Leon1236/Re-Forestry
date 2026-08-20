package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelLayerLocation;

import com.leon1236.reforestry.ReForestry;

@Environment(EnvType.CLIENT)
public final class ForestryModelLayers {
	public static final ModelLayerLocation MACHINE_LAYER = new ModelLayerLocation(ReForestry.id("machine"), "main");
	public static final ModelLayerLocation MILL_LAYER = new ModelLayerLocation(ReForestry.id("mill"), "main");
	public static final ModelLayerLocation NATURALIST_CHEST_LAYER = new ModelLayerLocation(ReForestry.id("naturalist_chest"), "main");
	public static final ModelLayerLocation ANALYZER_LAYER = new ModelLayerLocation(ReForestry.id("analyzer"), "main");
	public static final ModelLayerLocation ESCRITOIRE_LAYER = new ModelLayerLocation(ReForestry.id("escritoire"), "main");

	private ForestryModelLayers() {
	}
}
