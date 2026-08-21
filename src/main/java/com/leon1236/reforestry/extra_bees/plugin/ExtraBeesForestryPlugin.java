package com.leon1236.reforestry.extra_bees.plugin;

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

public class ExtraBeesForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("extra_bees");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleLoaded(ReForestry.id("extra_bees"));
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
