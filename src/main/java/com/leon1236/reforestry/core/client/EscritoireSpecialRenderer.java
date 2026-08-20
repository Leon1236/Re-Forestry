package com.leon1236.reforestry.core.client;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;

import org.joml.Vector3fc;

import com.leon1236.reforestry.ReForestry;

@Environment(EnvType.CLIENT)
public class EscritoireSpecialRenderer implements NoDataSpecialModelRenderer {
	private static final String DESK = "desk";
	private static final String STAND_RB = "standrb";
	private static final String STAND_RF = "standrf";
	private static final String STAND_LB = "standlb";
	private static final String STAND_LF = "standlf";
	private static final String DRAWERS = "drawers";
	private static final String STAND_LOW_RB = "standlowrb";
	private static final String STAND_LOW_RF = "standlowrf";
	private static final String STAND_LOW_LB = "standlowlb";
	private static final String STAND_LOW_LF = "standlowlf";

	private final SpriteGetter sprites;
	private final ModelPart[] parts;
	private final SpriteId texture;

	public EscritoireSpecialRenderer(SpriteGetter sprites, ModelPart root) {
		this.sprites = sprites;
		this.parts = new ModelPart[]{
				root.getChild(DESK),
				root.getChild(STAND_RB),
				root.getChild(STAND_RF),
				root.getChild(STAND_LB),
				root.getChild(STAND_LF),
				root.getChild(DRAWERS),
				root.getChild(STAND_LOW_RB),
				root.getChild(STAND_LOW_RF),
				root.getChild(STAND_LOW_LB),
				root.getChild(STAND_LOW_LF)
		};
		this.texture = Sheets.BLOCKS_MAPPER.apply(ReForestry.id("escritoire"));
	}

	@Override
	public void submit(
			PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector,
			int lightCoords,
			int overlayCoords,
			boolean hasFoil,
			int outlineColor
	) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, Direction.SOUTH);
		for (ModelPart part : this.parts) {
			submitPart(submitNodeCollector, poseStack, part, lightCoords, overlayCoords, outlineColor);
		}
		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		for (ModelPart part : this.parts) {
			part.getExtentsForGui(poseStack, output);
		}
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			int light,
			int overlay,
			int outlineColor
	) {
		TextureAtlasSprite sprite = this.sprites.get(this.texture);
		collector.submitModelPart(
				part,
				poseStack,
				this.texture.renderType(RenderTypes::entityCutout),
				light,
				overlay,
				sprite,
				-1,
				null,
				outlineColor);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
		public static final MapCodec<EscritoireSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new EscritoireSpecialRenderer.Unbaked());

		@Override
		public MapCodec<EscritoireSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public EscritoireSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new EscritoireSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.ESCRITOIRE_LAYER));
		}
	}
}
