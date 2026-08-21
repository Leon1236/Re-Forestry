package com.leon1236.reforestry.farming.plugin;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.agriculture.ForestryFarmTypes;
import com.leon1236.reforestry.api.circuits.ForestryCircuitLayouts;
import com.leon1236.reforestry.api.circuits.ForestryCircuitSocketTypes;
import com.leon1236.reforestry.api.plugin.ICircuitRegistration;
import com.leon1236.reforestry.api.plugin.IFarmingRegistration;
import com.leon1236.reforestry.api.plugin.IForestryPlugin;
import com.leon1236.reforestry.core.circuits.EnumElectronTube;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.farming.circuits.CircuitFarmLogic;

public class AgricultureForestryPlugin implements IForestryPlugin {
	@Override
	public Identifier id() {
		return ReForestry.id("farming");
	}

	@Override
	public boolean shouldLoad() {
		return IForestryApi.get().getModuleManager().isModuleLoaded(ReForestry.id("farming"));
	}

	@Override
	public void registerCircuits(ICircuitRegistration circuits) {
		circuits.registerLayout(ForestryCircuitLayouts.MANAGED_FARM, ForestryCircuitSocketTypes.FARM);
		circuits.registerLayout(ForestryCircuitLayouts.MANUAL_FARM, ForestryCircuitSocketTypes.FARM);

		registerFarmCircuit(circuits, EnumElectronTube.COPPER, ForestryFarmTypes.ARBOREAL, false);
		registerFarmCircuit(circuits, EnumElectronTube.TIN, ForestryFarmTypes.PEAT, false);
		registerFarmCircuit(circuits, EnumElectronTube.BRONZE, ForestryFarmTypes.CROPS, false);
		registerFarmCircuit(circuits, EnumElectronTube.IRON, ForestryFarmTypes.ENDER, false);
		registerFarmCircuit(circuits, EnumElectronTube.BLAZE, ForestryFarmTypes.INFERNAL, false);
		registerFarmCircuit(circuits, EnumElectronTube.OBSIDIAN, ForestryFarmTypes.GOURD, false);
		registerFarmCircuit(circuits, EnumElectronTube.APATITE, ForestryFarmTypes.SHROOM, false);

		registerFarmCircuit(circuits, EnumElectronTube.COPPER, ForestryFarmTypes.ORCHARD, true);
		registerFarmCircuit(circuits, EnumElectronTube.TIN, ForestryFarmTypes.PEAT, true);
		registerFarmCircuit(circuits, EnumElectronTube.BRONZE, ForestryFarmTypes.CROPS, true);
		registerFarmCircuit(circuits, EnumElectronTube.IRON, ForestryFarmTypes.ENDER, true);
		registerFarmCircuit(circuits, EnumElectronTube.GOLD, ForestryFarmTypes.SUCCULENTES, true);
		registerFarmCircuit(circuits, EnumElectronTube.DIAMOND, ForestryFarmTypes.POALES, true);
		registerFarmCircuit(circuits, EnumElectronTube.OBSIDIAN, ForestryFarmTypes.GOURD, true);
		registerFarmCircuit(circuits, EnumElectronTube.APATITE, ForestryFarmTypes.SHROOM, true);
		registerFarmCircuit(circuits, EnumElectronTube.LAPIS, ForestryFarmTypes.COCOA, true);
	}

	private static void registerFarmCircuit(ICircuitRegistration circuits, EnumElectronTube tube, Identifier typeId, boolean manual) {
		String id = manual ? "farm.manual." + typeId.getPath() : "farm.managed." + typeId.getPath();
		circuits.registerCircuit(
				manual ? ForestryCircuitLayouts.MANUAL_FARM : ForestryCircuitLayouts.MANAGED_FARM,
				new ItemStack(CoreItems.ELECTRON_TUBES.item(tube)),
				new CircuitFarmLogic(id, typeId, manual)
		);
	}

	@Override
	public void registerFarming(IFarmingRegistration farming) {
		DefaultFarms.registerFarmTypes(farming);
		farming.registerFertilizer(CoreItems.FERTILIZER_COMPOUND.item(), 500);
	}
}
