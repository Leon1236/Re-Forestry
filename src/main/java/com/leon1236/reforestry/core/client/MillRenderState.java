package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;

@Environment(EnvType.CLIENT)
public class MillRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.SOUTH;
	public SpriteId pedestal;
	public SpriteId extension;
	public SpriteId blade;
	public SpriteId column;
	public float bladeStep;
}
