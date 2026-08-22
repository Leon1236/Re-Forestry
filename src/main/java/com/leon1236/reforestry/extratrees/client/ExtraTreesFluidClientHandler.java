package com.leon1236.reforestry.extratrees.client;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids;

@Environment(EnvType.CLIENT)
public final class ExtraTreesFluidClientHandler {
	private ExtraTreesFluidClientHandler() {
	}

	public static void registerClient() {
		Identifier stillId = ReForestry.id("block/liquid/extra_trees_liquid");
		Material still = new Material(stillId, true);
		FluidModel.Unbaked model = new FluidModel.Unbaked(still, still, null, null);
		for (ExtraTreesFluids fluid : ExtraTreesFluids.values()) {
			FluidRenderingRegistry.register(fluid.getFluid(), fluid.getFlowing(), model);
			int particleColor = fluid.getFeature().properties().particleColor() | 0xFF000000;
			FluidVariantRenderHandler handler = new FluidVariantRenderHandler() {
				@Override
				public int getColor(FluidVariant fluidVariant, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos) {
					return particleColor;
				}
			};
			FluidVariantRendering.register(fluid.getFluid(), handler);
			FluidVariantRendering.register(fluid.getFlowing(), handler);
		}
	}
}
