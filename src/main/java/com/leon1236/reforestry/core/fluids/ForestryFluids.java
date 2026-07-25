package com.leon1236.reforestry.core.fluids;

import java.util.Locale;
import java.util.function.UnaryOperator;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributeHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;

import com.leon1236.reforestry.ReForestry;

public enum ForestryFluids {
    BIO_ETHANOL(properties -> properties.particleColor(color(255, 111, 0)).density(790).viscosity(1000)
            .flammability(300).spreadsFire()),
    BIOMASS(properties -> properties.particleColor(color(100, 132, 41)).density(400).viscosity(6560)
            .flammability(100)),
    GLASS(properties -> properties.particleColor(color(164, 164, 164)).density(2400).viscosity(10000)
            .spreadsFire().temperature(1400)),
    HONEY(properties -> properties.particleColor(color(255, 196, 35)).density(1420).viscosity(75600)),
    ICE(properties -> properties.particleColor(color(175, 242, 255)).density(520).viscosity(1000).temperature(265)),
    JUICE(properties -> properties.particleColor(color(168, 201, 114))),
    MILK(properties -> properties.particleColor(color(255, 255, 255)).density(1030).viscosity(1200)),
    SEED_OIL(properties -> properties.particleColor(color(255, 255, 168)).density(885).viscosity(5000)
            .spreadsFire().flammability(2)),
    SHORT_MEAD(properties -> properties.particleColor(color(239, 154, 56)).density(1000).viscosity(1200)
            .spreadsFire().flammability(4)),
    WAX(properties -> properties.particleColor(color(255, 237, 140)).density(1420).viscosity(500).temperature(329));

    private final FeatureFluid feature;

    ForestryFluids(UnaryOperator<ForestryFluidProperties.Builder> properties) {
        ForestryFluidProperties built = properties.apply(ForestryFluidProperties.builder()).build();
        this.feature = FeatureFluid.create(ReForestry.id("core"), name().toLowerCase(Locale.ROOT), built);
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

    private static int color(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    public static void init() {
        for (ForestryFluids fluid : values()) {
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
