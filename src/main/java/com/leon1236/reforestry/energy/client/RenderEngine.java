package com.leon1236.reforestry.energy.client;

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
import com.leon1236.reforestry.core.client.ForestryModelLayers;
import com.leon1236.reforestry.core.tiles.TemperatureState;
import com.leon1236.reforestry.energy.blocks.EngineBlock;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;

@Environment(EnvType.CLIENT)
public class RenderEngine implements BlockEntityRenderer<EngineBlockEntity, EngineRenderState> {
	private static final float[] ANGLE_MAP = new float[6];

	private enum Textures {
		BASE, PISTON, EXTENSION, TRUNK_HIGHEST, TRUNK_HIGHER, TRUNK_HIGH, TRUNK_MEDIUM, TRUNK_LOW
	}

	private final SpriteGetter sprites;
	private final ModelPart boiler;
	private final ModelPart trunk;
	private final ModelPart piston;
	private final ModelPart extension;
	private final SpriteId[] textures;

	static {
		ANGLE_MAP[Direction.EAST.ordinal()] = -Mth.HALF_PI;
		ANGLE_MAP[Direction.NORTH.ordinal()] = -Mth.HALF_PI;
		ANGLE_MAP[Direction.WEST.ordinal()] = Mth.HALF_PI;
		ANGLE_MAP[Direction.SOUTH.ordinal()] = Mth.HALF_PI;
		ANGLE_MAP[Direction.UP.ordinal()] = 0;
		ANGLE_MAP[Direction.DOWN.ordinal()] = Mth.PI;
	}

	public RenderEngine(BlockEntityRendererProvider.Context ctx, String baseTexture) {
		this.sprites = ctx.sprites();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.ENGINE_LAYER);
		this.boiler = root.getChild("boiler");
		this.trunk = root.getChild("trunk");
		this.piston = root.getChild("piston");
		this.extension = root.getChild("extension");
		this.textures = new SpriteId[]{
				blockSprite(baseTexture + "base"),
				blockSprite(baseTexture + "piston"),
				blockSprite(baseTexture + "extension"),
				blockSprite("engine_trunk_highest"),
				blockSprite("engine_trunk_higher"),
				blockSprite("engine_trunk_high"),
				blockSprite("engine_trunk_medium"),
				blockSprite("engine_trunk_low"),
		};
	}

	static SpriteId blockSprite(String path) {
		return Sheets.BLOCKS_MAPPER.apply(ReForestry.id(path));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("boiler", CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 16, 6, 16), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 8, 12, 8), PartPose.offset(4, 4, 4));
		root.addOrReplaceChild("piston", CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 12, 4, 12), PartPose.offset(2, 6, 2));
		root.addOrReplaceChild("extension", CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 10, 2, 10), PartPose.offset(3, 5, 3));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public EngineRenderState createRenderState() {
		return new EngineRenderState();
	}

	@Override
	public void extractRenderState(
			EngineBlockEntity engine,
			EngineRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(engine, state, partialTicks, cameraPosition, breakProgress);
		state.facing = engine.getBlockState().hasProperty(EngineBlock.VERTICAL_FACING)
				? engine.getBlockState().getValue(EngineBlock.VERTICAL_FACING)
				: Direction.UP;
		state.pistonStep = getPistonStep(engine, partialTicks);
		TemperatureState temperature = engine.hasLevel() ? engine.getTemperatureState() : TemperatureState.COOL;
		state.trunkTexture = switch (temperature) {
			case OVERHEATING -> this.textures[Textures.TRUNK_HIGHEST.ordinal()];
			case RUNNING_HOT -> this.textures[Textures.TRUNK_HIGHER.ordinal()];
			case OPERATING_TEMPERATURE -> this.textures[Textures.TRUNK_HIGH.ordinal()];
			case WARMED_UP -> this.textures[Textures.TRUNK_MEDIUM.ordinal()];
			default -> this.textures[Textures.TRUNK_LOW.ordinal()];
		};
	}

	@Override
	public void submit(
			EngineRenderState state,
			PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector,
			CameraRenderState camera
	) {
		poseStack.pushPose();
		applyOrientation(poseStack, state.facing);

		submitPart(submitNodeCollector, poseStack, this.boiler, this.textures[Textures.BASE.ordinal()],
				state.lightCoords, state.breakProgress);

		float tfactor = state.pistonStep / 16;
		poseStack.translate(0, tfactor, 0);
		submitPart(submitNodeCollector, poseStack, this.piston, this.textures[Textures.PISTON.ordinal()],
				state.lightCoords, state.breakProgress);
		poseStack.translate(0, -tfactor, 0);

		submitPart(submitNodeCollector, poseStack, this.trunk, state.trunkTexture,
				state.lightCoords, state.breakProgress);

		float chamberf = 2F / 16F;
		if (state.pistonStep > 0) {
			for (int i = 0; i <= state.pistonStep + 2; i += 2) {
				submitPart(submitNodeCollector, poseStack, this.extension, this.textures[Textures.EXTENSION.ordinal()],
						state.lightCoords, state.breakProgress);
				poseStack.translate(0, chamberf, 0);
			}
		}

		poseStack.popPose();
	}

	static void applyOrientation(PoseStack stack, Direction orientation) {
		stack.translate(0.5, 0.5, 0.5);
		switch (orientation) {
			case EAST, WEST, DOWN -> stack.mulPose(Axis.ZP.rotation(ANGLE_MAP[orientation.ordinal()]));
			default -> stack.mulPose(Axis.XP.rotation(ANGLE_MAP[orientation.ordinal()]));
		}
		stack.translate(-0.5, -0.5, -0.5);
	}

	static float idlePistonStep() {
		return 0.25f * 2f * 6f;
	}

	private static float getPistonStep(EngineBlockEntity engine, float partialTick) {
		float progress;
		if (engine.hasLevel()) {
			progress = engine.progress;
			if (engine.stagePiston != 0) {
				progress = progress + engine.pistonSpeedServer * partialTick;
			}
		} else {
			progress = 0.25f;
		}

		if (progress > 0.5f) {
			return 6f - (progress - 0.5f) * 2f * 6F;
		}
		return progress * 2f * 6f;
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
