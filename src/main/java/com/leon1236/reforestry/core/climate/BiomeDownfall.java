package com.leon1236.reforestry.core.climate;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.biome.Biome;

public final class BiomeDownfall {
	@Nullable
	private static final MethodHandle GET_DOWNFALL = createHandle();

	private BiomeDownfall() {
	}

	@Nullable
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
			return null;
		}
	}

	public static float get(Biome biome) {
		MethodHandle handle = GET_DOWNFALL;
		if (handle == null) {
			return 0.5f;
		}
		try {
			return (float) handle.invoke(biome);
		} catch (Throwable t) {
			return 0.5f;
		}
	}
}
