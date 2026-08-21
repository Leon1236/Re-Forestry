package com.leon1236.reforestry.gendustry.fluids;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.FeatureFluid;
import com.leon1236.reforestry.core.fluids.ForestryFluidProperties;

public enum GFluids {
	MUTAGEN(0x3CA14E),
	LIQUID_DNA(0x80329F),
	PROTEIN(0xC15757);

	private final FeatureFluid feature;

	GFluids(int particleColor) {
		this.feature = FeatureFluid.create(
				ReForestry.id("gendustry"),
				name().toLowerCase(Locale.ROOT),
				ForestryFluidProperties.builder().particleColor(particleColor).build());
	}

	public FeatureFluid getFeature() {
		return this.feature;
	}

	public Fluid getFluid() {
		return this.feature.source();
	}

	public Fluid getFlowing() {
		return this.feature.flowing();
	}

	public BucketItem getBucket() {
		return this.feature.bucket();
	}

	public boolean is(@Nullable Fluid fluid) {
		return fluid != null && (getFluid() == fluid || getFlowing() == fluid);
	}

	public static void init() {
		for (GFluids fluid : values()) {
			ForestryFluidProperties properties = fluid.feature.properties();
			FluidVariantAttributeHandler handler = new FluidVariantAttributeHandler() {
				@Override
				public int getTemperature(FluidVariant variant) {
					return properties.temperature();
				}

				@Override
				public int getViscosity(FluidVariant variant, @Nullable Level level) {
					return Math.max(1, properties.viscosity());
				}
			};
			FluidVariantAttributes.register(fluid.getFluid(), handler);
			FluidVariantAttributes.register(fluid.getFlowing(), handler);
		}
	}
}
