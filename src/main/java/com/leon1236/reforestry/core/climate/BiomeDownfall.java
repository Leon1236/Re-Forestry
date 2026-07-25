package com.leon1236.reforestry.core.climate;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.world.level.biome.Biome;

public final class BiomeDownfall {
    private static final MethodHandle GET_DOWNFALL = createHandle();

    private BiomeDownfall() {
    }

    private static MethodHandle createHandle() {
        try {
            Class<?> settingsClass = Class.forName("net.minecraft.world.level.biome.Biome$ClimateSettings");
            Field climateSettings = Biome.class.getDeclaredField("climateSettings");
            climateSettings.setAccessible(true);
            Method downfall = settingsClass.getDeclaredMethod("downfall");
            downfall.setAccessible(true);
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            return MethodHandles.filterReturnValue(lookup.unreflectGetter(climateSettings), lookup.unreflect(downfall));
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static float get(Biome biome) {
        try {
            return (float) GET_DOWNFALL.invoke(biome);
        } catch (Throwable t) {
            throw new IllegalStateException("Failed to read biome downfall", t);
        }
    }
}
