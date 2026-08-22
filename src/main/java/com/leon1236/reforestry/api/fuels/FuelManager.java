package com.leon1236.reforestry.api.fuels;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public final class FuelManager {
	private static final Map<Item, FermenterFuel> FERMENTER_FUEL = new HashMap<>();
	private static final Map<Item, MoistenerFuel> MOISTENER_RESOURCE = new HashMap<>();
	private static final Map<Item, RainSubstrate> RAIN_SUBSTRATE = new HashMap<>();
	private static final Map<Fluid, EngineBronzeFuel> BIOGAS_ENGINE_FUEL = new HashMap<>();
	private static final Map<Item, EngineCopperFuel> PEAT_ENGINE_FUEL = new HashMap<>();

	private static final Map<Item, FermenterFuel> FERMENTER_FUEL_VIEW = Collections.unmodifiableMap(FERMENTER_FUEL);
	private static final Map<Item, MoistenerFuel> MOISTENER_RESOURCE_VIEW = Collections.unmodifiableMap(MOISTENER_RESOURCE);
	private static final Map<Item, RainSubstrate> RAIN_SUBSTRATE_VIEW = Collections.unmodifiableMap(RAIN_SUBSTRATE);
	private static final Map<Fluid, EngineBronzeFuel> BIOGAS_ENGINE_FUEL_VIEW = Collections.unmodifiableMap(BIOGAS_ENGINE_FUEL);
	private static final Map<Item, EngineCopperFuel> PEAT_ENGINE_FUEL_VIEW = Collections.unmodifiableMap(PEAT_ENGINE_FUEL);

	private FuelManager() {
	}

	public static void registerFermenterFuel(Item item, FermenterFuel fuel) {
		FERMENTER_FUEL.put(Objects.requireNonNull(item), Objects.requireNonNull(fuel));
	}

	public static void registerFermenterFuel(ItemStack stack, FermenterFuel fuel) {
		registerFermenterFuel(stack.getItem(), fuel);
	}

	public static void registerMoistenerResource(Item item, MoistenerFuel fuel) {
		MOISTENER_RESOURCE.put(Objects.requireNonNull(item), Objects.requireNonNull(fuel));
	}

	public static void registerMoistenerResource(ItemStack stack, MoistenerFuel fuel) {
		registerMoistenerResource(stack.getItem(), fuel);
	}

	public static void registerRainSubstrate(Item item, RainSubstrate substrate) {
		RAIN_SUBSTRATE.put(Objects.requireNonNull(item), Objects.requireNonNull(substrate));
	}

	public static void registerRainSubstrate(ItemStack stack, RainSubstrate substrate) {
		registerRainSubstrate(stack.getItem(), substrate);
	}

	public static void registerBiogasEngineFuel(Fluid fluid, EngineBronzeFuel fuel) {
		BIOGAS_ENGINE_FUEL.put(Objects.requireNonNull(fluid), Objects.requireNonNull(fuel));
	}

	public static void registerPeatEngineFuel(Item item, EngineCopperFuel fuel) {
		PEAT_ENGINE_FUEL.put(Objects.requireNonNull(item), Objects.requireNonNull(fuel));
	}

	public static void registerPeatEngineFuel(ItemStack stack, EngineCopperFuel fuel) {
		registerPeatEngineFuel(stack.getItem(), fuel);
	}

	public static Map<Item, FermenterFuel> getFermenterFuels() {
		return FERMENTER_FUEL_VIEW;
	}

	public static Map<Item, MoistenerFuel> getMoistenerResources() {
		return MOISTENER_RESOURCE_VIEW;
	}

	public static Map<Item, RainSubstrate> getRainSubstrates() {
		return RAIN_SUBSTRATE_VIEW;
	}

	public static Map<Fluid, EngineBronzeFuel> getBiogasEngineFuels() {
		return BIOGAS_ENGINE_FUEL_VIEW;
	}

	public static Map<Item, EngineCopperFuel> getPeatEngineFuels() {
		return PEAT_ENGINE_FUEL_VIEW;
	}

	@Nullable
	public static FermenterFuel getFermenterFuel(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		return FERMENTER_FUEL.get(stack.getItem());
	}

	@Nullable
	public static MoistenerFuel getMoistenerFuel(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		return MOISTENER_RESOURCE.get(stack.getItem());
	}

	@Nullable
	public static RainSubstrate getRainSubstrate(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		return RAIN_SUBSTRATE.get(stack.getItem());
	}

	@Nullable
	public static EngineBronzeFuel getBiogasEngineFuel(Fluid fluid) {
		if (fluid == null) {
			return null;
		}
		return BIOGAS_ENGINE_FUEL.get(fluid);
	}

	public static boolean isBiogasEngineFuel(Fluid fluid) {
		return fluid != null && BIOGAS_ENGINE_FUEL.containsKey(fluid);
	}

	@Nullable
	public static EngineCopperFuel getPeatEngineFuel(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		return PEAT_ENGINE_FUEL.get(stack.getItem());
	}
}
