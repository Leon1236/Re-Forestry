package com.leon1236.reforestry.energy.client;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;

import org.joml.Vector3fc;

import com.leon1236.reforestry.core.client.ForestryModelLayers;

@Environment(EnvType.CLIENT)
public class EngineSpecialRenderer implements NoDataSpecialModelRenderer {
	private final SpriteGetter sprites;
	private final ModelPart boiler;
	private final ModelPart trunk;
	private final ModelPart piston;
	private final ModelPart extension;
	private final SpriteId baseTexture;
	private final SpriteId pistonTexture;
	private final SpriteId extensionTexture;
	private final SpriteId trunkTexture;

	public EngineSpecialRenderer(SpriteGetter sprites, ModelPart root, String texturePrefix) {
		this.sprites = sprites;
		this.boiler = root.getChild("boiler");
		this.trunk = root.getChild("trunk");
		this.piston = root.getChild("piston");
		this.extension = root.getChild("extension");
		this.baseTexture = RenderEngine.blockSprite(texturePrefix + "base");
		this.pistonTexture = RenderEngine.blockSprite(texturePrefix + "piston");
		this.extensionTexture = RenderEngine.blockSprite(texturePrefix + "extension");
		this.trunkTexture = RenderEngine.blockSprite("engine_trunk_low");
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
		RenderEngine.applyOrientation(poseStack, Direction.UP);

		submitPart(submitNodeCollector, poseStack, this.boiler, this.baseTexture, lightCoords, overlayCoords, outlineColor);

		float pistonStep = RenderEngine.idlePistonStep();
		float tfactor = pistonStep / 16;
		poseStack.translate(0, tfactor, 0);
		submitPart(submitNodeCollector, poseStack, this.piston, this.pistonTexture, lightCoords, overlayCoords, outlineColor);
		poseStack.translate(0, -tfactor, 0);

		submitPart(submitNodeCollector, poseStack, this.trunk, this.trunkTexture, lightCoords, overlayCoords, outlineColor);

		float chamberf = 2F / 16F;
		for (int i = 0; i <= pistonStep + 2; i += 2) {
			submitPart(submitNodeCollector, poseStack, this.extension, this.extensionTexture, lightCoords, overlayCoords, outlineColor);
			poseStack.translate(0, chamberf, 0);
		}

		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.boiler.getExtentsForGui(poseStack, output);
		this.trunk.getExtentsForGui(poseStack, output);
		this.piston.getExtentsForGui(poseStack, output);
		this.extension.getExtentsForGui(poseStack, output);
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
		public static final MapCodec<EngineSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(Codec.STRING.fieldOf("texture").forGetter(EngineSpecialRenderer.Unbaked::texture))
						.apply(instance, EngineSpecialRenderer.Unbaked::new));

		@Override
		public MapCodec<EngineSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public EngineSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new EngineSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.ENGINE_LAYER),
					this.texture);
		}
	}
}
