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
public class AnalyzerSpecialRenderer implements NoDataSpecialModelRenderer {
	private static final String TOWER2 = "tower2";
	private static final String TOWER1 = "tower1";
	private static final String COVER = "cover";
	private static final String PEDESTAL = "pedestal";

	private final SpriteGetter sprites;
	private final ModelPart pedestal;
	private final ModelPart cover;
	private final ModelPart tower1;
	private final ModelPart tower2;
	private final SpriteId texturePedestal;
	private final SpriteId textureTower1;
	private final SpriteId textureTower2;

	public AnalyzerSpecialRenderer(SpriteGetter sprites, ModelPart root) {
		this.sprites = sprites;
		this.pedestal = root.getChild(PEDESTAL);
		this.cover = root.getChild(COVER);
		this.tower1 = root.getChild(TOWER1);
		this.tower2 = root.getChild(TOWER2);
		this.texturePedestal = blockSprite("analyzer_pedestal");
		this.textureTower1 = blockSprite("analyzer_tower1");
		this.textureTower2 = blockSprite("analyzer_tower2");
	}

	private static SpriteId blockSprite(String path) {
		return Sheets.BLOCKS_MAPPER.apply(ReForestry.id(path));
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
		submitPart(submitNodeCollector, poseStack, this.pedestal, this.texturePedestal, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.cover, this.texturePedestal, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.tower1, this.textureTower1, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.tower2, this.textureTower2, lightCoords, overlayCoords, outlineColor);
		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.pedestal.getExtentsForGui(poseStack, output);
		this.cover.getExtentsForGui(poseStack, output);
		this.tower1.getExtentsForGui(poseStack, output);
		this.tower2.getExtentsForGui(poseStack, output);
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			SpriteId spriteId,
			int light,
			int overlay,
			int outlineColor
	) {
		TextureAtlasSprite sprite = this.sprites.get(spriteId);
		collector.submitModelPart(
				part,
				poseStack,
				spriteId.renderType(RenderTypes::entityCutout),
				light,
				overlay,
				sprite,
				-1,
				null,
				outlineColor);
	}

	@Environment(EnvType.CLIENT)
	public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
		public static final MapCodec<AnalyzerSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new AnalyzerSpecialRenderer.Unbaked());

		@Override
		public MapCodec<AnalyzerSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public AnalyzerSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new AnalyzerSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.ANALYZER_LAYER));
		}
	}
}
