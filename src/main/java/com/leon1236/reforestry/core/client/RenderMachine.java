package com.leon1236.reforestry.core.client;

import java.util.EnumMap;
import java.util.Locale;

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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.render.EnumTankLevel;
import com.leon1236.reforestry.core.render.TankRenderInfo;
import com.leon1236.reforestry.core.tiles.IRenderableTile;

@Environment(EnvType.CLIENT)
public class RenderMachine implements BlockEntityRenderer<BlockEntity, MachineRenderState> {
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

	public RenderMachine(BlockEntityRendererProvider.Context ctx, String texturePrefix) {
		this.sprites = ctx.sprites();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.MACHINE_LAYER);
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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(BASE_FRONT, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 16, 4, 16), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild(BASE_BACK, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 16, 4, 16), PartPose.offset(0, 12, 0));
		root.addOrReplaceChild(RESOURCE_TANK, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 12, 16, 6), PartPose.offset(2, 0, 2));
		root.addOrReplaceChild(PRODUCT_TANK, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0f, 0f, 0f, 12, 16, 6), PartPose.offset(2, 0, 8));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public MachineRenderState createRenderState() {
		return new MachineRenderState();
	}

	@Override
	public void extractRenderState(
			BlockEntity blockEntity,
			MachineRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.facing = blockEntity.getBlockState().hasProperty(BlockMachine.FACING)
				? blockEntity.getBlockState().getValue(BlockMachine.FACING)
				: Direction.NORTH;
		state.textureBase = this.textureBase;
		state.textureResourceTank = this.textureResourceTank;
		state.textureProductTank = this.textureProductTank;

		TankRenderInfo resource = TankRenderInfo.EMPTY;
		TankRenderInfo product = TankRenderInfo.EMPTY;
		if (blockEntity instanceof IRenderableTile renderable) {
			resource = renderable.getResourceTankInfo();
			product = renderable.getProductTankInfo();
		}
		state.setTanks(resource, product);
	}

	@Override
	public void submit(MachineRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, state.facing);
		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Axis.XP.rotation(-Mth.HALF_PI));
		poseStack.translate(-0.5, -0.5, -0.5);

		submitPart(submitNodeCollector, poseStack, this.basefront, state.textureBase, state.lightCoords, -1, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.baseback, state.textureBase, state.lightCoords, -1, state.breakProgress);

		poseStack.translate(0.5, 0.5, 0.5);
		poseStack.mulPose(Axis.YP.rotation(-Mth.HALF_PI));
		poseStack.translate(-0.5, -0.5, -0.5);

		submitTank(submitNodeCollector, poseStack, this.resourceTank, state.textureResourceTank, state.resourceLevel, state.resourceColor, state.lightCoords, state.breakProgress);
		submitTank(submitNodeCollector, poseStack, this.productTank, state.textureProductTank, state.productLevel, state.productColor, state.lightCoords, state.breakProgress);

		poseStack.popPose();
	}

	private void submitTank(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart tankModel,
			SpriteId textureBase,
			EnumTankLevel level,
			int fluidColor,
			int light,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		submitPart(collector, poseStack, tankModel, textureBase, light, -1, breakProgress);
		SpriteId textureLevel = this.texturesTankLevels.get(level);
		if (textureLevel == null) {
			return;
		}
		int tint = 0xFF000000 | (fluidColor & 0xFFFFFF);
		submitPart(collector, poseStack, tankModel, textureLevel, light, tint, breakProgress);
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			SpriteId spriteId,
			int light,
			int tintedColor,
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
				tintedColor,
				breakProgress);
	}
}
