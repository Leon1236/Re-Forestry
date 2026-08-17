package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.api.core.AccessMode;

@Environment(EnvType.CLIENT)
public record AccessMachinePipRenderState(
		BlockState machineState,
		BlockState[] neighborStates,
		AccessMode[] accessModes,
		boolean entityModel,
		float yaw,
		float pitch,
		float scale,
		@Nullable Direction hoveredFace,
		int x0,
		int y0,
		int x1,
		int y1,
		@Nullable ScreenRectangle scissorArea,
		@Nullable ScreenRectangle bounds
) implements PictureInPictureRenderState {
	public static final float DEFAULT_SCALE = 20.0f;
	public static final float GHOST_DISTANCE = 1.0f;
	public static final float GHOST_ALPHA = 0.35f;
	public static final float DEFAULT_PITCH = 15.0f;

	public AccessMachinePipRenderState(
			BlockState machineState,
			BlockState[] neighborStates,
			AccessMode[] accessModes,
			boolean entityModel,
			float yaw,
			float pitch,
			float scale,
			@Nullable Direction hoveredFace,
			int x0,
			int y0,
			int x1,
			int y1,
			@Nullable ScreenRectangle scissorArea
	) {
		this(machineState, neighborStates, accessModes, entityModel, yaw, pitch, scale, hoveredFace, x0, y0, x1, y1, scissorArea,
				PictureInPictureRenderState.getBounds(x0, y0, x1, y1, scissorArea));
	}

	public BlockState neighbor(Direction direction) {
		return this.neighborStates[direction.get3DDataValue()];
	}

	public AccessMode access(Direction direction) {
		return this.accessModes[direction.get3DDataValue()];
	}

	@Override
	public float scale() {
		return this.scale;
	}
}
