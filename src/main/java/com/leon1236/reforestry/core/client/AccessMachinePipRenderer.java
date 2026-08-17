package com.leon1236.reforestry.core.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.core.AccessMode;
import com.leon1236.reforestry.core.blocks.BlockMachine;

@Environment(EnvType.CLIENT)
public final class AccessMachinePipRenderer extends PictureInPictureRenderer<AccessMachinePipRenderState> {
	private static final Matrix4f IDENTITY = new Matrix4f();
	private static final int FULL_BRIGHT = LightCoordsUtil.FULL_BRIGHT;
	private static final int GHOST_ALPHA_BYTE = Mth.clamp(Math.round(AccessMachinePipRenderState.GHOST_ALPHA * 255.0f), 1, 255);
	private static final float DEG = (float) Math.PI / 180.0f;
	private static final float FACE_TINT_DEPTH = -0.002f;
	private static final int FACE_TINT_ALPHA = 0x78;
	private static final int FACE_TINT_ALPHA_HOVER = 0xB0;

	private final BlockModelRenderState machineRenderState = new BlockModelRenderState();
	private final BlockModelRenderState ghostRenderState = new BlockModelRenderState();
	private final BlockModelRenderState accessTintRenderState = new BlockModelRenderState();
	private final Map<String, MachineSpecialRenderer> tesrMachines = new HashMap<>();

	public AccessMachinePipRenderer() {
	}

	@Override
	public Class<AccessMachinePipRenderState> getRenderStateClass() {
		return AccessMachinePipRenderState.class;
	}

	@Override
	protected void renderToTexture(AccessMachinePipRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
		Minecraft.getInstance().gameRenderer.lighting().setupFor(Lighting.Entry.ITEMS_3D);

		applyView(poseStack, state.yaw(), state.pitch(), state.entityModel());

		poseStack.pushPose();
		poseStack.translate(-0.5f, -0.5f, -0.5f);
		if (state.entityModel()) {
			renderTesrMachine(state, poseStack, submitNodeCollector);
		} else {
			updateMachineModel(this.machineRenderState, state);
			this.machineRenderState.submit(poseStack, submitNodeCollector, FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0);
		}
		updateAccessFaceTints(this.accessTintRenderState, state);
		this.accessTintRenderState.submit(poseStack, submitNodeCollector, FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0);
		poseStack.popPose();

		for (Direction direction : Direction.values()) {
			BlockState neighbor = state.neighbor(direction);
			if (neighbor.isAir()) {
				continue;
			}
			updateTranslucentGhost(this.ghostRenderState, neighbor);
			poseStack.pushPose();
			poseStack.translate(
					direction.getStepX() * AccessMachinePipRenderState.GHOST_DISTANCE,
					direction.getStepY() * AccessMachinePipRenderState.GHOST_DISTANCE,
					direction.getStepZ() * AccessMachinePipRenderState.GHOST_DISTANCE);
			poseStack.translate(-0.5f, -0.5f, -0.5f);
			this.ghostRenderState.submit(poseStack, submitNodeCollector, FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0);
			poseStack.popPose();
		}
	}

	private void renderTesrMachine(AccessMachinePipRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector) {
		Identifier blockId = BuiltInRegistries.BLOCK.getKey(state.machineState().getBlock());
		if (!ReForestry.MOD_ID.equals(blockId.getNamespace())) {
			return;
		}
		MachineSpecialRenderer renderer = this.tesrMachines.computeIfAbsent(blockId.getPath() + "_", this::createMachineRenderer);
		Direction facing = state.machineState().hasProperty(BlockMachine.FACING)
				? state.machineState().getValue(BlockMachine.FACING)
				: Direction.NORTH;
		renderer.submit(poseStack, submitNodeCollector, FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0, facing);
	}

	private MachineSpecialRenderer createMachineRenderer(String texturePrefix) {
		Minecraft minecraft = Minecraft.getInstance();
		return new MachineSpecialRenderer(
				minecraft.getAtlasManager(),
				minecraft.getEntityModels().bakeLayer(ForestryModelLayers.MACHINE_LAYER),
				texturePrefix);
	}

	@Override
	protected float getTranslateY(int height, int guiScale) {
		return height / 2.0f;
	}

	@Override
	protected String getTextureLabel() {
		return "reforestry access machine";
	}

	static boolean usesEntityMachineModel(BlockState blockState) {
		BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(blockState);
		AtomicInteger quadCount = new AtomicInteger();
		BlockModelRenderState probe = new BlockModelRenderState();
		QuadEmitter emitter = probe.setupMesh(IDENTITY, false);
		emitter.pushTransform(quad -> {
			quadCount.incrementAndGet();
			return false;
		});
		model.emitQuads(
				emitter,
				BlockAndTintGetter.EMPTY,
				BlockPos.ZERO,
				blockState,
				probe.scratchRandomSource(42L),
				direction -> false);
		emitter.popTransform();
		return quadCount.get() <= 0;
	}

	static void applyView(PoseStack poseStack, float yawDegrees, float pitchDegrees, boolean entityModel) {
		if (entityModel) {
			poseStack.scale(1.0f, -1.0f, -1.0f);
		} else {
			poseStack.scale(1.0f, -1.0f, 1.0f);
		}
		poseStack.mulPose(Axis.XP.rotationDegrees(pitchDegrees));
		poseStack.mulPose(Axis.YP.rotationDegrees(yawDegrees));
	}

	static float yawToShowFacing(Direction facing, boolean entityModel) {
		float yaw = facing.toYRot();
		return entityModel ? yaw + 180.0f : yaw;
	}

	static Matrix4f pickViewMatrix(float yawDegrees, float pitchDegrees, boolean entityModel) {
		Matrix4f matrix = new Matrix4f().scaling(1.0f, 1.0f, -1.0f);
		if (entityModel) {
			matrix.scale(1.0f, -1.0f, -1.0f);
		} else {
			matrix.scale(1.0f, -1.0f, 1.0f);
		}
		return matrix.rotateX(pitchDegrees * DEG).rotateY(yawDegrees * DEG);
	}

	static @Nullable Direction pickMachineFace(
			double mouseX,
			double mouseY,
			float centerX,
			float centerY,
			float scale,
			float yawDegrees,
			float pitchDegrees,
			boolean entityModel
	) {
		Matrix4f matrix = pickViewMatrix(yawDegrees, pitchDegrees, entityModel);

		Direction best = null;
		float bestDepth = Float.POSITIVE_INFINITY;
		Vector3f point = new Vector3f();
		Vector3f normal = new Vector3f();
		float[] xs = new float[4];
		float[] ys = new float[4];

		for (Direction direction : Direction.values()) {
			normal.set(direction.getStepX(), direction.getStepY(), direction.getStepZ());
			matrix.transformDirection(normal);
			if (normal.z() >= 0.0f) {
				continue;
			}

			float depthSum = 0.0f;
			for (int corner = 0; corner < 4; corner++) {
				faceCorner(direction, corner, point);
				matrix.transformPosition(point);
				xs[corner] = centerX + point.x * scale;
				ys[corner] = centerY + point.y * scale;
				depthSum += point.z;
			}
			if (!pointInConvexQuad(mouseX, mouseY, xs, ys)) {
				continue;
			}

			float depth = depthSum * 0.25f;
			if (depth < bestDepth) {
				best = direction;
				bestDepth = depth;
			}
		}
		return best;
	}

	private static void faceCorner(Direction face, int corner, Vector3f out) {
		float u = (corner == 0 || corner == 3) ? -0.5f : 0.5f;
		float v = (corner == 0 || corner == 1) ? -0.5f : 0.5f;
		switch (face) {
			case DOWN -> out.set(u, -0.5f, v);
			case UP -> out.set(u, 0.5f, -v);
			case NORTH -> out.set(-u, v, -0.5f);
			case SOUTH -> out.set(u, v, 0.5f);
			case WEST -> out.set(-0.5f, v, u);
			case EAST -> out.set(0.5f, v, -u);
		}
	}

	private static boolean pointInConvexQuad(double px, double py, float[] xs, float[] ys) {
		boolean sign = false;
		boolean hasSign = false;
		for (int i = 0; i < 4; i++) {
			int j = (i + 1) % 4;
			double cross = (xs[j] - xs[i]) * (py - ys[i]) - (ys[j] - ys[i]) * (px - xs[i]);
			if (cross == 0.0) {
				continue;
			}
			boolean positive = cross > 0.0;
			if (!hasSign) {
				sign = positive;
				hasSign = true;
			} else if (sign != positive) {
				return false;
			}
		}
		return hasSign;
	}

	private static void updateMachineModel(BlockModelRenderState renderState, AccessMachinePipRenderState state) {
		renderState.clear();
		BlockState blockState = state.machineState();
		BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(blockState);
		QuadEmitter emitter = renderState.setupMesh(IDENTITY, false);
		model.emitQuads(
				emitter,
				BlockAndTintGetter.EMPTY,
				BlockPos.ZERO,
				blockState,
				renderState.scratchRandomSource(42L),
				direction -> false);
	}

	private static void updateAccessFaceTints(BlockModelRenderState renderState, AccessMachinePipRenderState state) {
		renderState.clear();
		QuadEmitter emitter = renderState.setupMesh(IDENTITY, true);
		Material.Baked material = new Material.Baked(
				Minecraft.getInstance().getAtlasManager().get(Sheets.BLOCKS_MAPPER.defaultNamespaceApply("white_wool")),
				false);
		for (Direction face : Direction.values()) {
			int alpha = face == state.hoveredFace() ? FACE_TINT_ALPHA_HOVER : FACE_TINT_ALPHA;
			int color = (tintRgb(state.access(face)) & 0x00FFFFFF) | (alpha << 24);
			emitter.square(face, 0.0f, 0.0f, 1.0f, 1.0f, FACE_TINT_DEPTH);
			emitter.color(color, color, color, color);
			emitter.materialBake(material, MutableQuadView.BAKE_LOCK_UV);
			emitter.emit();
		}
	}

	private static void updateTranslucentGhost(BlockModelRenderState renderState, BlockState blockState) {
		renderState.clear();
		BlockStateModel model = Minecraft.getInstance().getModelManager().getBlockStateModelSet().get(blockState);
		QuadEmitter emitter = renderState.setupMesh(IDENTITY, true);
		emitter.pushTransform(quad -> {
			for (int vertex = 0; vertex < 4; vertex++) {
				int color = quad.color(vertex);
				int alpha = (color >>> 24) & 0xFF;
				if (alpha == 0) {
					alpha = 255;
				}
				alpha = alpha * GHOST_ALPHA_BYTE / 255;
				quad.color(vertex, (color & 0x00FFFFFF) | (alpha << 24));
			}
			return true;
		});
		model.emitQuads(
				emitter,
				BlockAndTintGetter.EMPTY,
				BlockPos.ZERO,
				blockState,
				renderState.scratchRandomSource(42L),
				direction -> false);
		emitter.popTransform();
	}

	private static int tintRgb(AccessMode mode) {
		return switch (mode) {
			case NONE -> 0xFF9A9A9A;
			case INPUT -> 0xFF3A8CFF;
			case OUTPUT -> 0xFFFF8C1A;
			case BOTH -> 0xFF2ECC55;
		};
	}
}
