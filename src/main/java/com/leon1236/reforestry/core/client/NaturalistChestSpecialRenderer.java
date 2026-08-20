package com.leon1236.reforestry.core.client;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

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
public class NaturalistChestSpecialRenderer implements NoDataSpecialModelRenderer {
	private static final String LID = "lid";
	private static final String BASE = "base";
	private static final String LOCK = "lock";

	private final SpriteGetter sprites;
	private final ModelPart lid;
	private final ModelPart base;
	private final ModelPart lock;
	private final SpriteId texture;

	public NaturalistChestSpecialRenderer(SpriteGetter sprites, ModelPart root, String textureName) {
		this.sprites = sprites;
		this.lid = root.getChild(LID);
		this.base = root.getChild(BASE);
		this.lock = root.getChild(LOCK);
		this.texture = Sheets.BLOCKS_MAPPER.apply(ReForestry.id(textureName));
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
		this.lid.xRot = 0.0F;
		this.lock.xRot = 0.0F;
		submitPart(submitNodeCollector, poseStack, this.lid, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.lock, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.base, lightCoords, overlayCoords, outlineColor);
		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.lid.getExtentsForGui(poseStack, output);
		this.lock.getExtentsForGui(poseStack, output);
		this.base.getExtentsForGui(poseStack, output);
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
	public record Unbaked(String texture) implements NoDataSpecialModelRenderer.Unbaked {
		public static final MapCodec<NaturalistChestSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(Codec.STRING.fieldOf("texture").forGetter(NaturalistChestSpecialRenderer.Unbaked::texture))
						.apply(instance, NaturalistChestSpecialRenderer.Unbaked::new));

		@Override
		public MapCodec<NaturalistChestSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public NaturalistChestSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new NaturalistChestSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.NATURALIST_CHEST_LAYER),
					this.texture);
		}
	}
}
