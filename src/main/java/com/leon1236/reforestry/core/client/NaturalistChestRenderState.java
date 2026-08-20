package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class NaturalistChestRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.NORTH;
	public float lidAngle;
}
