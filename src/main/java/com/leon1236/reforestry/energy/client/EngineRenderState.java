package com.leon1236.reforestry.energy.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class EngineRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.UP;
	public float pistonStep;
	public SpriteId trunkTexture;
}
