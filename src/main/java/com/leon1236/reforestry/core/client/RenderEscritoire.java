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
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.escritoire.TileEscritoire;

@Environment(EnvType.CLIENT)
public class RenderEscritoire implements BlockEntityRenderer<TileEscritoire, EscritoireRenderState> {
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
	private final ItemModelResolver itemModelResolver;
	private final ModelPart[] parts;
	private final SpriteId texture;

	public RenderEscritoire(BlockEntityRendererProvider.Context ctx) {
		this.sprites = ctx.sprites();
		this.itemModelResolver = ctx.itemModelResolver();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.ESCRITOIRE_LAYER);
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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(DESK, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 16, 2, 15).mirror(), PartPose.offsetAndRotation(0, 9.5f, 0.4f, 0.0872665f, 0, 0));
		root.addOrReplaceChild(STAND_RB, CubeListBuilder.create().texOffs(38, 18)
				.addBox(0f, 0f, 0f, 2, 6, 2).mirror(), PartPose.offset(13, 4, 13));
		root.addOrReplaceChild(STAND_RF, CubeListBuilder.create().texOffs(38, 18)
				.addBox(0f, 0f, 0f, 2, 6, 2).mirror(), PartPose.offset(13, 4, 1));
		root.addOrReplaceChild(STAND_LB, CubeListBuilder.create().texOffs(38, 18)
				.addBox(0f, 0f, 0f, 2, 6, 2).mirror(), PartPose.offset(1, 4, 1));
		root.addOrReplaceChild(STAND_LF, CubeListBuilder.create().texOffs(38, 18)
				.addBox(0f, 0f, 0f, 2, 6, 2).mirror(), PartPose.offset(1, 4, 13));
		root.addOrReplaceChild(DRAWERS, CubeListBuilder.create().texOffs(0, 18)
				.addBox(0f, 0f, 0f, 15, 5, 3).mirror(), PartPose.offset(0.5f, 11, 0.5f));
		root.addOrReplaceChild(STAND_LOW_RB, CubeListBuilder.create().texOffs(0, 26)
				.addBox(0f, 0f, 0f, 1, 4, 1).mirror(), PartPose.offset(13.5f, 0, 13.5f));
		root.addOrReplaceChild(STAND_LOW_RF, CubeListBuilder.create().texOffs(0, 26)
				.addBox(0f, 0f, 0f, 1, 4, 1).mirror(), PartPose.offset(13.5f, 0, 1.5f));
		root.addOrReplaceChild(STAND_LOW_LB, CubeListBuilder.create().texOffs(0, 26)
				.addBox(0f, 0f, 0f, 1, 4, 1).mirror(), PartPose.offset(1.5f, 0, 1.5f));
		root.addOrReplaceChild(STAND_LOW_LF, CubeListBuilder.create().texOffs(0, 26)
				.addBox(0f, 0f, 0f, 1, 4, 1).mirror(), PartPose.offset(1.5f, 0, 13.5f));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public EscritoireRenderState createRenderState() {
		return new EscritoireRenderState();
	}

	@Override
	public void extractRenderState(
			TileEscritoire escritoire,
			EscritoireRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(escritoire, state, partialTicks, cameraPosition, breakProgress);
		state.facing = escritoire.getBlockState().hasProperty(BlockMachine.FACING)
				? escritoire.getBlockState().getValue(BlockMachine.FACING)
				: Direction.NORTH;

		ItemStack displayStack = escritoire.getIndividualOnDisplay();
		state.displayItem = new ItemStackRenderState();
		if (!displayStack.isEmpty()) {
			int seed = (int) escritoire.getBlockPos().asLong();
			this.itemModelResolver.updateForTopItem(
					state.displayItem,
					displayStack,
					ItemDisplayContext.GROUND,
					escritoire.getLevel(),
					null,
					seed);
			Level level = escritoire.getLevel();
			if (level != null) {
				float smoothTick = level.getGameTime() + partialTicks;
				state.itemBob = Mth.sin(smoothTick / 10.0f) * 0.1f + 0.1f;
				state.itemSpin = smoothTick / 20.0f;
			}
		}
	}

	@Override
	public void submit(
			EscritoireRenderState state,
			PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector,
			CameraRenderState camera
	) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, state.facing);

		for (ModelPart part : this.parts) {
			submitPart(submitNodeCollector, poseStack, part, state.lightCoords, state.breakProgress);
		}

		if (!state.displayItem.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(0.5f, 0.65f + state.itemBob, 0.5f);
			poseStack.scale(0.75f, 0.75f, 0.75f);
			poseStack.mulPose(Axis.YP.rotation(state.itemSpin));
			state.displayItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
			poseStack.popPose();
		}

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
