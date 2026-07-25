package com.leon1236.reforestry.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.core.Direction;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.core.fluids.ForestryFluids;

@Environment(EnvType.CLIENT)
public final class RenderUtil {
	private RenderUtil() {
	}

	public static void rotateByHorizontalDirection(PoseStack stack, Direction facing) {
		if (facing != Direction.SOUTH) {
			stack.translate(0.5, 0.5, 0.5);
			stack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
			stack.translate(-0.5, -0.5, -0.5);
		}
	}

	public static int getFluidColor(Fluid fluid) {
		if (fluid == null || fluid == Fluids.EMPTY || fluid.defaultFluidState().isEmpty()) {
			return 0x0000FF;
		}
		if (fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER) {
			return 0x3F76E4;
		}
		for (ForestryFluids definition : ForestryFluids.values()) {
			if (definition.is(fluid)) {
				return definition.getFeature().properties().particleColor();
			}
		}
		int color = FluidVariantRendering.getColor(FluidVariant.of(fluid));
		if (color == -1) {
			return 0x0000FF;
		}
		return color & 0xFFFFFF;
	}
}
