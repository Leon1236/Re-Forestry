package com.leon1236.reforestry.core.client.accessio;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.core.blocks.BlockMachine;

@Environment(EnvType.CLIENT)
public final class AccessIoCamera {
	public static final float DEFAULT_PITCH = 20.0f;
	public static final float DEFAULT_SCALE = 22.0f;
	public static final float MAX_PITCH = 80.0f;
	public static final float ROTATE_SENSITIVITY = 0.5f;

	private static final float DEG = (float) Math.PI / 180.0f;

	private AccessIoCamera() {
	}

	public static Direction machineFacing(BlockState blockState) {
		return blockState.hasProperty(BlockMachine.FACING)
				? blockState.getValue(BlockMachine.FACING)
				: Direction.NORTH;
	}

	public static float yawLookingAtFace(Direction face) {
		return face.toYRot() + 180.0f;
	}

	public static float clampPitch(float pitch) {
		return Mth.clamp(pitch, -MAX_PITCH, MAX_PITCH);
	}

	public static void applyModelView(PoseStack poseStack, float yawDegrees, float pitchDegrees) {
		poseStack.scale(1.0f, -1.0f, 1.0f);
		poseStack.mulPose(Axis.XP.rotationDegrees(pitchDegrees));
		poseStack.mulPose(Axis.YP.rotationDegrees(yawDegrees));
		poseStack.translate(-0.5f, -0.5f, -0.5f);
	}

	public static Matrix4f pickMatrix(float yawDegrees, float pitchDegrees) {
		PoseStack poseStack = new PoseStack();
		poseStack.scale(1.0f, 1.0f, -1.0f);
		applyModelView(poseStack, yawDegrees, pitchDegrees);
		return new Matrix4f(poseStack.last().pose());
	}

	public static @Nullable Direction pickFace(
			double mouseX,
			double mouseY,
			float centerX,
			float centerY,
			float scale,
			float yawDegrees,
			float pitchDegrees
	) {
		Matrix4f matrix = pickMatrix(yawDegrees, pitchDegrees);
		Direction best = null;
		float bestDepth = Float.NEGATIVE_INFINITY;
		Vector3f point = new Vector3f();
		Vector3f normal = new Vector3f();
		float[] xs = new float[4];
		float[] ys = new float[4];

		for (Direction face : Direction.values()) {
			normal.set(face.getStepX(), face.getStepY(), face.getStepZ());
			matrix.transformDirection(normal);
			if (normal.z() <= 0.0f) {
				continue;
			}

			float depthSum = 0.0f;
			for (int corner = 0; corner < 4; corner++) {
				faceCorner(face, corner, point);
				matrix.transformPosition(point);
				xs[corner] = centerX + point.x * scale;
				ys[corner] = centerY + point.y * scale;
				depthSum += point.z;
			}
			if (!containsPoint(mouseX, mouseY, xs, ys)) {
				continue;
			}

			float depth = depthSum * 0.25f;
			if (depth > bestDepth) {
				best = face;
				bestDepth = depth;
			}
		}
		return best;
	}

	private static void faceCorner(Direction face, int corner, Vector3f out) {
		float u = (corner == 0 || corner == 3) ? 0.0f : 1.0f;
		float v = (corner == 0 || corner == 1) ? 0.0f : 1.0f;
		switch (face) {
			case DOWN -> out.set(u, 0.0f, 1.0f - v);
			case UP -> out.set(u, 1.0f, v);
			case NORTH -> out.set(1.0f - u, v, 0.0f);
			case SOUTH -> out.set(u, v, 1.0f);
			case WEST -> out.set(0.0f, v, u);
			case EAST -> out.set(1.0f, v, 1.0f - u);
		}
	}

	private static boolean containsPoint(double px, double py, float[] xs, float[] ys) {
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
}
