package com.leon1236.reforestry.core.client;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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
import net.minecraft.util.Mth;

import org.joml.Vector3fc;

import com.leon1236.reforestry.ReForestry;

@Environment(EnvType.CLIENT)
public class MillSpecialRenderer implements NoDataSpecialModelRenderer {
	private enum Textures {
		PEDESTAL,
		EXTENSION,
		BLADE,
		CHARGE
	}

	private final SpriteGetter sprites;
	private final ModelPart pedestal;
	private final ModelPart column;
	private final ModelPart extension;
	private final ModelPart blade;
	private final SpriteId pedestalTexture;
	private final SpriteId extensionTexture;
	private final SpriteId bladeTexture;
	private final SpriteId columnTexture;

	public MillSpecialRenderer(SpriteGetter sprites, ModelPart root, String texturePrefix) {
		this.sprites = sprites;
		this.pedestal = root.getChild(Textures.PEDESTAL.name());
		this.column = root.getChild(Textures.CHARGE.name());
		this.extension = root.getChild(Textures.EXTENSION.name());
		this.blade = root.getChild(Textures.BLADE.name());
		this.pedestalTexture = blockSprite(texturePrefix + "pedestal");
		this.extensionTexture = blockSprite(texturePrefix + "extension");
		this.bladeTexture = blockSprite(texturePrefix + "blade");
		this.columnTexture = blockSprite(texturePrefix + "column_0");
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

		submitPart(submitNodeCollector, poseStack, this.pedestal, this.pedestalTexture, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.column, this.columnTexture, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.extension, this.extensionTexture, lightCoords, overlayCoords, outlineColor);

		poseStack.pushPose();
		submitPart(submitNodeCollector, poseStack, this.blade, this.bladeTexture, lightCoords, overlayCoords, outlineColor);
		poseStack.popPose();

		poseStack.translate(1, 0, 1);
		poseStack.mulPose(Axis.YP.rotation(Mth.PI));
		submitPart(submitNodeCollector, poseStack, this.blade, this.bladeTexture, lightCoords, overlayCoords, outlineColor);

		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.pedestal.getExtentsForGui(poseStack, output);
		this.column.getExtentsForGui(poseStack, output);
		this.extension.getExtentsForGui(poseStack, output);
		this.blade.getExtentsForGui(poseStack, output);
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
	public record Unbaked(String texture) implements NoDataSpecialModelRenderer.Unbaked {
		public static final MapCodec<MillSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(Codec.STRING.fieldOf("texture").forGetter(MillSpecialRenderer.Unbaked::texture))
						.apply(instance, MillSpecialRenderer.Unbaked::new));

		@Override
		public MapCodec<MillSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public MillSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new MillSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.MILL_LAYER),
					this.texture);
		}
	}
}
