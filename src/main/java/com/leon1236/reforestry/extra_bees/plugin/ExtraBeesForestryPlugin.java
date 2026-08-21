package com.leon1236.reforestry.extra_bees.plugin;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.circuits.ForestryCircuitLayouts;
import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;
import com.leon1236.reforestry.api.plugin.IApicultureRegistration;
import com.leon1236.reforestry.api.plugin.ICircuitRegistration;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.api.plugin.IGeneticRegistration;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.extra_bees.circuit.AlvearySimulatorCircuitType;
import com.leon1236.reforestry.extra_bees.circuit.StimulatorCircuit;
import com.leon1236.reforestry.extra_bees.genetics.ExtraBeesBeeSpecies;
import com.leon1236.reforestry.extra_bees.genetics.ExtraBeesFlowerType;
import com.leon1236.reforestry.extra_bees.genetics.effects.ExtraBeesEffects;
import com.leon1236.reforestry.extra_bees.hives.ExtraBeesHiveDefinition;

public class ExtraBeesForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("extra_bees");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleEnabled(ReForestry.id("extra_bees"));
	}

	@Override
	public void registerGenetics(IGeneticRegistration registration) {
		for (ExtraBeesFlowerType type : ExtraBeesFlowerType.values()) {
			registration.registerFlowerType(type.id(), type);
		}
		ExtraBeesBeeSpecies.registerTaxa(registration);
	}

	@Override
	public void registerApiculture(IApicultureRegistration registration) {
		ExtraBeesEffects.register(registration);
		ExtraBeesBeeSpecies.register(registration);
		registerHives(registration);
	}

	private static void registerHives(IApicultureRegistration registration) {
		registration.registerHive(ReForestry.id("bee_water"), ExtraBeesHiveDefinition.WATER)
				.addDrop(0.80, ReForestry.id("bee_water"), () -> List.of(), 0.5f)
				.addDrop(0.03, ReForestry.id("bee_valiant"), () -> List.of(), 0.5f);

		registration.registerHive(ReForestry.id("bee_rock"), ExtraBeesHiveDefinition.ROCK)
				.addDrop(0.80, ReForestry.id("bee_rock"), () -> List.of(), 0.5f)
				.addDrop(0.03, ReForestry.id("bee_valiant"), () -> List.of(), 0.5f);

		registration.registerHive(ReForestry.id("bee_basalt"), ExtraBeesHiveDefinition.NETHER)
				.addDrop(0.80, ReForestry.id("bee_basalt"), () -> List.of(), 0.5f)
				.addDrop(0.03, ReForestry.id("bee_valiant"), () -> List.of(), 0.5f);

		registration.registerHive(ReForestry.id("bee_marble"), ExtraBeesHiveDefinition.MARBLE)
				.addDrop(0.80, ReForestry.id("bee_marble"), () -> List.of(), 0.5f)
				.addDrop(0.03, ReForestry.id("bee_valiant"), () -> List.of(), 0.5f);
	}

	@Override
	public void registerCircuits(ICircuitRegistration circuits) {
		circuits.registerLayout(ForestryCircuitLayouts.STIMULATOR, ForestryCircuitSocketTypes.STIMULATOR);
		for (AlvearySimulatorCircuitType type : AlvearySimulatorCircuitType.values()) {
			circuits.registerCircuit(
					ForestryCircuitLayouts.STIMULATOR,
					new ItemStack(CoreItems.ELECTRON_TUBES.item(type.getTube())),
					new StimulatorCircuit(type));
		}
	}
}
