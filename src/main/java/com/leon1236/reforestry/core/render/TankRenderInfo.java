package com.leon1236.reforestry.core.render;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.fluids.FilteredFluidStorage;

public class TankRenderInfo {
	public static final TankRenderInfo EMPTY = new TankRenderInfo(Fluids.EMPTY, EnumTankLevel.EMPTY);

	private final Fluid fluid;
	private final EnumTankLevel level;

	public TankRenderInfo(Fluid fluid, EnumTankLevel level) {
		this.fluid = fluid;
		this.level = level;
	}

	public static TankRenderInfo of(FilteredFluidStorage tank) {
		long amount = tank.getAmount();
		long capacity = tank.getTankCapacity();
		if (amount <= 0 || capacity <= 0) {
			return EMPTY;
		}
		int scaled = (int) (100 * amount / capacity);
		return new TankRenderInfo(tank.getResource().getFluid(), EnumTankLevel.rateTankLevel(scaled));
	}

	public Fluid getFluid() {
		return this.fluid;
	}

	public EnumTankLevel getLevel() {
		return this.level;
	}
}
