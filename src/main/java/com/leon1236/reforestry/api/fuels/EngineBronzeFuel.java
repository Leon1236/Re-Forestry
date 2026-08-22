package com.leon1236.reforestry.api.fuels;

import net.minecraft.world.level.material.Fluid;

public record EngineBronzeFuel(Fluid liquid, int powerPerCycle, int burnDuration, int dissipationMultiplier) {
}
