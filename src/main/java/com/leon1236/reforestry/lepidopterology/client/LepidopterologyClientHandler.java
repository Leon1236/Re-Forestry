package com.leon1236.reforestry.lepidopterology.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.renderer.entity.EntityRenderers;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.lepidopterology.features.LepidopterologyEntities;

public class LepidopterologyClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		ModelLayerRegistry.registerModelLayer(ButterflyEntityRenderer.MODEL_LAYER, ButterflyModel::createLayer);
		EntityRenderers.register(LepidopterologyEntities.BUTTERFLY.entityType(), ButterflyEntityRenderer::new);
	}
}
