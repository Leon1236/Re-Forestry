package com.leon1236.reforestry.api.predicates;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public final class HygroregulatorInputFluids {
	private static volatile Predicate<FluidVariant> predicate = HygroregulatorInputFluids::isFallbackFluid;

	private HygroregulatorInputFluids() {
	}

	public static void setAllowedFluids(Set<Fluid> fluids) {
		Set<Fluid> copy = new HashSet<>(fluids);
		copy.add(Fluids.WATER);
		copy.add(Fluids.LAVA);
		Set<Fluid> allowed = Set.copyOf(copy);
		predicate = variant -> allowed.contains(variant.getFluid());
	}

	public static boolean test(FluidVariant variant) {
		return !variant.isBlank() && predicate.test(variant);
	}

	private static boolean isFallbackFluid(FluidVariant variant) {
		Fluid fluid = variant.getFluid();
		return fluid == Fluids.WATER || fluid == Fluids.LAVA;
	}
}
