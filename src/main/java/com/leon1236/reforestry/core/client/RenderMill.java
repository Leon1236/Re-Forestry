package com.leon1236.reforestry.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.tiles.TileMill;

@Environment(EnvType.CLIENT)
public class RenderMill implements BlockEntityRenderer<TileMill, MillRenderState> {
	private enum Textures {
		PEDESTAL,
		EXTENSION,
		BLADE,
		CHARGE
	}

	private final SpriteGetter sprites;
	private final SpriteId[] textures;
	private final ModelPart pedestal;
	private final ModelPart column;
	private final ModelPart extension;
	private final ModelPart blade;

	public RenderMill(BlockEntityRendererProvider.Context ctx, String texturePrefix) {
		this.sprites = ctx.sprites();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.MILL_LAYER);
		this.pedestal = root.getChild(Textures.PEDESTAL.name());
		this.column = root.getChild(Textures.CHARGE.name());
		this.extension = root.getChild(Textures.EXTENSION.name());
		this.blade = root.getChild(Textures.BLADE.name());

		this.textures = new SpriteId[11];
		this.textures[Textures.PEDESTAL.ordinal()] = blockSprite(texturePrefix + "pedestal");
		this.textures[Textures.EXTENSION.ordinal()] = blockSprite(texturePrefix + "extension");
		this.textures[Textures.BLADE.ordinal()] = blockSprite(texturePrefix + "blade");
		for (int i = 0; i < 8; i++) {
			this.textures[Textures.CHARGE.ordinal() + i] = blockSprite(texturePrefix + "column_" + i);
		}
	}

	private static SpriteId blockSprite(String path) {
		return Sheets.BLOCKS_MAPPER.apply(ReForestry.id(path));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(Textures.PEDESTAL.name(), CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 16, 1, 16), PartPose.offset(0, 0, 0));
		partdefinition.addOrReplaceChild(Textures.CHARGE.name(), CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 4, 15, 4), PartPose.offset(6, 1, 6));
		partdefinition.addOrReplaceChild(Textures.EXTENSION.name(), CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 14, 2, 2), PartPose.offset(1, 8, 7));
		partdefinition.addOrReplaceChild(Textures.BLADE.name(), CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 1, 12, 8), PartPose.offset(10, 3, 4));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public MillRenderState createRenderState() {
		return new MillRenderState();
	}

	@Override
	public void extractRenderState(
			TileMill mill,
			MillRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(mill, state, partialTicks, cameraPosition, breakProgress);
		state.facing = mill.getBlockState().hasProperty(BlockMachine.FACING)
				? mill.getBlockState().getValue(BlockMachine.FACING)
				: Direction.SOUTH;
		state.pedestal = this.textures[Textures.PEDESTAL.ordinal()];
		state.extension = this.textures[Textures.EXTENSION.ordinal()];
		state.blade = this.textures[Textures.BLADE.ordinal()];
		int charge = Mth.clamp(mill.charge, 0, 7);
		state.column = this.textures[Textures.CHARGE.ordinal() + charge];
		state.bladeStep = getBladeStep(mill, partialTicks) / 16f;
	}

	@Override
	public void submit(MillRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, state.facing);

		submitPart(submitNodeCollector, poseStack, this.pedestal, state.pedestal, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.column, state.column, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.extension, state.extension, state.lightCoords, state.breakProgress);

		poseStack.pushPose();
		poseStack.translate(state.bladeStep, 0, 0);
		submitPart(submitNodeCollector, poseStack, this.blade, state.blade, state.lightCoords, state.breakProgress);
		poseStack.popPose();

		poseStack.translate(1, 0, 1);
		poseStack.mulPose(Axis.YP.rotation(Mth.PI));
		poseStack.translate(state.bladeStep, 0, 0);
		submitPart(submitNodeCollector, poseStack, this.blade, state.blade, state.lightCoords, state.breakProgress);

		poseStack.popPose();
	}

	private static float getBladeStep(TileMill mill, float partialTick) {
		float progress;
		if (mill.hasLevel()) {
			progress = mill.progress;
			if (mill.stage != 0) {
				progress = progress + mill.speed * partialTick;
			}
		} else {
			progress = 0.0f;
		}

		if (progress > 0.5f) {
			return 3.99f - (progress - 0.5f) * 2f * 3.99f;
		}
		return progress * 2f * 3.99f;
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			SpriteId spriteId,
			int light,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		TextureAtlasSprite sprite = this.sprites.get(spriteId);
		collector.submitModelPart(
				part,
				poseStack,
				spriteId.renderType(RenderTypes::entityCutout),
				light,
				OverlayTexture.NO_OVERLAY,
				sprite,
				-1,
				breakProgress);
	}
}
