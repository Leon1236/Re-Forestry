package com.leon1236.reforestry.core.client;

import java.util.EnumMap;
import java.util.Locale;
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
import com.leon1236.reforestry.core.render.EnumTankLevel;

@Environment(EnvType.CLIENT)
public class MachineSpecialRenderer implements NoDataSpecialModelRenderer {
	private static final String BASE_FRONT = "basefront";
	private static final String BASE_BACK = "baseback";
	private static final String RESOURCE_TANK = "resourceTank";
	private static final String PRODUCT_TANK = "productTank";

	private final SpriteGetter sprites;
	private final ModelPart basefront;
	private final ModelPart baseback;
	private final ModelPart resourceTank;
	private final ModelPart productTank;
	private final SpriteId textureBase;
	private final SpriteId textureResourceTank;
	private final SpriteId textureProductTank;
	private final EnumMap<EnumTankLevel, SpriteId> texturesTankLevels = new EnumMap<>(EnumTankLevel.class);

	public MachineSpecialRenderer(SpriteGetter sprites, ModelPart root, String texturePrefix) {
		this.sprites = sprites;
		this.basefront = root.getChild(BASE_FRONT);
		this.baseback = root.getChild(BASE_BACK);
		this.resourceTank = root.getChild(RESOURCE_TANK);
		this.productTank = root.getChild(PRODUCT_TANK);
		this.textureBase = blockSprite(texturePrefix + "base");
		this.textureProductTank = blockSprite(texturePrefix + "tank_product_empty");
		this.textureResourceTank = blockSprite(texturePrefix + "tank_resource_empty");
		for (EnumTankLevel tankLevel : EnumTankLevel.values()) {
			if (tankLevel == EnumTankLevel.EMPTY) {
				continue;
			}
			String tankLevelString = tankLevel.toString().toLowerCase(Locale.ENGLISH);
			this.texturesTankLevels.put(tankLevel, blockSprite("machine_tank_" + tankLevelString));
		}
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
		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Axis.XP.rotation(-Mth.HALF_PI));
		poseStack.translate(-0.5, -0.5, -0.5);

		submitPart(submitNodeCollector, poseStack, this.basefront, this.textureBase, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.baseback, this.textureBase, lightCoords, overlayCoords, outlineColor);

		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Axis.YP.rotation(-Mth.HALF_PI));
		poseStack.translate(-0.5, -0.5, -0.5);

		submitPart(submitNodeCollector, poseStack, this.resourceTank, this.textureResourceTank, lightCoords, overlayCoords, outlineColor);
		submitPart(submitNodeCollector, poseStack, this.productTank, this.textureProductTank, lightCoords, overlayCoords, outlineColor);

		poseStack.popPose();
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.basefront.getExtentsForGui(poseStack, output);
		this.baseback.getExtentsForGui(poseStack, output);
		this.resourceTank.getExtentsForGui(poseStack, output);
		this.productTank.getExtentsForGui(poseStack, output);
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
		public static final MapCodec<MachineSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				instance -> instance.group(Codec.STRING.fieldOf("texture").forGetter(MachineSpecialRenderer.Unbaked::texture))
						.apply(instance, MachineSpecialRenderer.Unbaked::new));

		@Override
		public MapCodec<MachineSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public MachineSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
			return new MachineSpecialRenderer(
					context.sprites(),
					context.entityModelSet().bakeLayer(ForestryModelLayers.MACHINE_LAYER),
					this.texture);
		}
	}
}
