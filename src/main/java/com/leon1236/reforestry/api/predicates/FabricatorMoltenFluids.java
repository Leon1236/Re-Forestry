package com.leon1236.reforestry.api.predicates;

import java.util.Set;
import java.util.function.Predicate;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.level.material.Fluid;

public final class FabricatorMoltenFluids {
    private static volatile Predicate<FluidVariant> predicate = variant -> true;

    private FabricatorMoltenFluids() {
    }

    public static void setAllowedFluids(Set<Fluid> fluids) {
        Set<Fluid> copy = Set.copyOf(fluids);
        predicate = variant -> copy.contains(variant.getFluid());
    }

    public static boolean test(FluidVariant variant) {
        return predicate.test(variant);
    }
}
