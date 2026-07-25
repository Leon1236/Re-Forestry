package com.leon1236.reforestry.core.fluids;

public final class FluidUnits {
    private static final long DROPLETS_PER_MB = 81;

    private FluidUnits() {
    }

    public static long mbToDroplets(long mb) {
        return mb * DROPLETS_PER_MB;
    }

    public static long dropletsToMb(long droplets) {
        return droplets / DROPLETS_PER_MB;
    }
}
