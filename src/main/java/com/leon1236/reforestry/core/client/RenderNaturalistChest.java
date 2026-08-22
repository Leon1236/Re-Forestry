package com.leon1236.reforestry.core.client;

import com.mojang.blaze3d.vertex.PoseStack;

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
import com.leon1236.reforestry.core.tiles.TileNaturalistChest;

@Environment(EnvType.CLIENT)
public class RenderNaturalistChest implements BlockEntityRenderer<TileNaturalistChest, NaturalistChestRenderState> {
	private static final String LID = "lid";
	private static final String BASE = "base";
	private static final String LOCK = "lock";

	private final SpriteGetter sprites;
	private final ModelPart lid;
	private final ModelPart base;
	private final ModelPart lock;
	private final SpriteId texture;

	public RenderNaturalistChest(BlockEntityRendererProvider.Context ctx, String textureName) {
		this.sprites = ctx.sprites();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.NATURALIST_CHEST_LAYER);
		this.lid = root.getChild(LID);
		this.base = root.getChild(BASE);
		this.lock = root.getChild(LOCK);
		this.texture = Sheets.BLOCKS_MAPPER.apply(ReForestry.id(textureName));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(BASE, CubeListBuilder.create().texOffs(0, 19)
				.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild(LID, CubeListBuilder.create().texOffs(0, 0)
				.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0, 9.0F, 1.0F));
		root.addOrReplaceChild(LOCK, CubeListBuilder.create().texOffs(0, 0)
				.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0, 8.0F, 0));

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public NaturalistChestRenderState createRenderState() {
		return new NaturalistChestRenderState();
	}

	@Override
	public void extractRenderState(
			TileNaturalistChest chest,
			NaturalistChestRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(chest, state, partialTicks, cameraPosition, breakProgress);
		state.facing = chest.getBlockState().hasProperty(BlockMachine.FACING)
				? chest.getBlockState().getValue(BlockMachine.FACING)
				: Direction.NORTH;
		float angle = Mth.lerp(partialTicks, chest.getPrevLidAngle(), chest.getLidAngle());
		angle = 1.0F - angle;
		angle = 1.0F - angle * angle * angle;
		state.lidAngle = -(angle * Mth.HALF_PI);
	}

	@Override
	public void submit(
			NaturalistChestRenderState state,
			PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector,
			CameraRenderState camera
	) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, state.facing);

		this.lid.xRot = state.lidAngle;
		this.lock.xRot = state.lidAngle;

		submitPart(submitNodeCollector, poseStack, this.lid, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.lock, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.base, state.lightCoords, state.breakProgress);

		poseStack.popPose();
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			int light,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		TextureAtlasSprite sprite = this.sprites.get(this.texture);
		collector.submitModelPart(
				part,
				poseStack,
				this.texture.renderType(RenderTypes::entityCutout),
				light,
				OverlayTexture.NO_OVERLAY,
				sprite,
				-1,
				breakProgress);
	}
}
