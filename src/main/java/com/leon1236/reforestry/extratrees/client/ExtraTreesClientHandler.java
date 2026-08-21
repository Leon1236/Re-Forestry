package com.leon1236.reforestry.extratrees.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.EntityRenderers;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.extratrees.features.ExtraTreesEntities;

public class ExtraTreesClientHandler implements IClientModuleHandler {
	@Override
	public void registerClient() {
		ModelLayerRegistry.registerModelLayer(ExtraTreesBoatRenderer.BOAT_MODEL_LAYER, BoatModel::createBoatModel);
		ModelLayerRegistry.registerModelLayer(ExtraTreesBoatRenderer.CHEST_BOAT_MODEL_LAYER, BoatModel::createChestBoatModel);
		EntityRenderers.register(ExtraTreesEntities.BOAT.entityType(), context -> new ExtraTreesBoatRenderer<>(context, false));
		EntityRenderers.register(ExtraTreesEntities.CHEST_BOAT.entityType(), context -> new ExtraTreesBoatRenderer<>(context, true));
	}
}
