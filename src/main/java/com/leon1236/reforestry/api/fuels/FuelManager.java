package com.leon1236.reforestry.api.fuels;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public class FuelManager {
    public static Map<ItemStack, FermenterFuel> fermenterFuel;
    public static Map<ItemStack, MoistenerFuel> moistenerResource;
    public static Map<ItemStack, RainSubstrate> rainSubstrate;
    public static Map<Fluid, EngineBronzeFuel> biogasEngineFuel;
    public static Map<ItemStack, EngineCopperFuel> peatEngineFuel;

    @Nullable
    public static EngineCopperFuel getPeatEngineFuel(ItemStack stack) {
        if (peatEngineFuel == null || stack.isEmpty()) {
            return null;
        }
        for (var entry : peatEngineFuel.entrySet()) {
            if (ItemStack.isSameItem(entry.getKey(), stack)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
