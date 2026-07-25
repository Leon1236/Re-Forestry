package com.leon1236.reforestry.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.render.EnumTankLevel;
import com.leon1236.reforestry.core.render.TankRenderInfo;

@Environment(EnvType.CLIENT)
public class MachineRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.NORTH;
	public SpriteId textureBase;
	public SpriteId textureResourceTank;
	public SpriteId textureProductTank;
	public EnumTankLevel resourceLevel = EnumTankLevel.EMPTY;
	public EnumTankLevel productLevel = EnumTankLevel.EMPTY;
	public int resourceColor = 0xFFFFFF;
	public int productColor = 0xFFFFFF;

	public void setTanks(TankRenderInfo resource, TankRenderInfo product) {
		this.resourceLevel = resource.getLevel();
		this.productLevel = product.getLevel();
		this.resourceColor = resource.getFluid() == Fluids.EMPTY ? 0xFFFFFF : RenderUtil.getFluidColor(resource.getFluid());
		this.productColor = product.getFluid() == Fluids.EMPTY ? 0xFFFFFF : RenderUtil.getFluidColor(product.getFluid());
	}
}
