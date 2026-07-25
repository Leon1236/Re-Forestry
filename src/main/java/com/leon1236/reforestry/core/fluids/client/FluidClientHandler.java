package com.leon1236.reforestry.core.fluids.client;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRenderHandler;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.ForestryFluids;

public final class FluidClientHandler {
	private FluidClientHandler() {
	}

	public static void registerClient() {
		for (ForestryFluids fluid : ForestryFluids.values()) {
			int argb = fluid.getFeature().properties().particleColor() | 0xFF000000;
			Identifier still = ReForestry.id("block/liquid/" + fluid.name().toLowerCase(Locale.ROOT) + "_still");
			Material material = new Material(still, true);
			FluidModel.Unbaked model = new FluidModel.Unbaked(material, material, null, null);
			FluidRenderingRegistry.register(fluid.getFluid(), fluid.getFlowing(), model);

			FluidVariantRenderHandler handler = new FluidVariantRenderHandler() {
				@Override
				public int getColor(FluidVariant fluidVariant, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos) {
					return argb;
				}
			};
			FluidVariantRendering.register(fluid.getFluid(), handler);
			FluidVariantRendering.register(fluid.getFlowing(), handler);
		}
	}
}
