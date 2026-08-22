package com.leon1236.reforestry.lepidopterology.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.lepidopterology.entities.EntityButterfly;

@Environment(EnvType.CLIENT)
public class ButterflyEntityRenderer extends MobRenderer<EntityButterfly, ButterflyRenderState, ButterflyModel> {
	public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(ReForestry.id("butterfly"), "main");

	public ButterflyEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new ButterflyModel(context.bakeLayer(MODEL_LAYER)), 0.25f);
	}

	@Override
	public ButterflyRenderState createRenderState() {
		return new ButterflyRenderState();
	}

	@Override
	public void extractRenderState(EntityButterfly entity, ButterflyRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.size = entity.getSize();
		state.renderable = entity.isRenderable();
		state.ageInTicks = entity.getWingFlap(partialTicks);
		Identifier texture = null;
		if (entity.getSpecies() != null) {
			texture = ButterflyClientManager.INSTANCE.getEntityTexture(entity.getSpecies().id());
		}
		state.texture = texture != null ? texture : Identifier.withDefaultNamespace("missingno");
	}

	@Override
	public Identifier getTextureLocation(ButterflyRenderState state) {
		return state.texture;
	}

	@Override
	public void submit(ButterflyRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
			CameraRenderState camera) {
		if (!state.renderable) {
			return;
		}
		poseStack.pushPose();
		poseStack.translate(0, 0.2, 0);
		super.submit(state, poseStack, collector, camera);
		poseStack.popPose();
	}

	@Override
	protected void scale(ButterflyRenderState state, PoseStack poseStack) {
		float size = state.size <= 0 ? 0.75f : state.size;
		poseStack.scale(size, size, size);
		poseStack.translate(0.0F, 1.45f / size, 0.0F);
	}
}
