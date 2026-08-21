package com.leon1236.reforestry.gendustry.client;

import java.util.Locale;

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
import com.leon1236.reforestry.gendustry.fluids.GFluids;

@Environment(EnvType.CLIENT)
public final class GendustryFluidClientHandler {
	private GendustryFluidClientHandler() {
	}

	public static void registerClient() {
		for (GFluids fluid : GFluids.values()) {
			String name = fluid.name().toLowerCase(Locale.ROOT);
			Identifier stillId = ReForestry.id("block/liquid/" + name + "_still");
			Identifier flowingId = ReForestry.id("block/liquid/" + name + "_flowing");
			Material still = new Material(stillId, true);
			Material flowing = new Material(flowingId, true);
			FluidModel.Unbaked model = new FluidModel.Unbaked(still, flowing, null, null);
			FluidRenderingRegistry.register(fluid.getFluid(), fluid.getFlowing(), model);

			FluidVariantRenderHandler handler = new FluidVariantRenderHandler() {
				@Override
				public int getColor(FluidVariant fluidVariant, @Nullable BlockAndTintGetter level, @Nullable BlockPos pos) {
					return 0xFFFFFFFF;
				}
			};
			FluidVariantRendering.register(fluid.getFluid(), handler);
			FluidVariantRendering.register(fluid.getFlowing(), handler);
		}
	}
}
