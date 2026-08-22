package com.leon1236.reforestry;

import java.util.List;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.apiculture.ModuleApiculture;
import com.leon1236.reforestry.arboriculture.ModuleArboriculture;
import com.leon1236.reforestry.core.ForestryApiImpl;
import com.leon1236.reforestry.core.ModuleCore;
import com.leon1236.reforestry.core.compat.trinkets.TrinketsCompat;
import com.leon1236.reforestry.core.plugin.PluginManager;
import com.leon1236.reforestry.cultivation.ModuleCultivation;
import com.leon1236.reforestry.energy.ModuleEnergy;
import com.leon1236.reforestry.extra_bees.ModuleExtraBees;
import com.leon1236.reforestry.extratrees.ModuleExtraTrees;
import com.leon1236.reforestry.factory.ModuleFactory;
import com.leon1236.reforestry.farming.ModuleFarming;
import com.leon1236.reforestry.gendustry.ModuleGendustry;
import com.leon1236.reforestry.lepidopterology.ModuleLepidopterology;
import com.leon1236.reforestry.modules.ModuleManager;
import com.leon1236.reforestry.sorting.ModuleSorting;
import com.leon1236.reforestry.storage.ModuleStorage;
import com.leon1236.reforestry.worktable.ModuleWorktable;

public class ReForestry implements ModInitializer {
	public static final String MOD_ID = "reforestry";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		IForestryApi.setInstance(ForestryApiImpl.get());
		ModuleManager.INSTANCE.load(List.of(
				new ModuleCore(),
				new ModuleApiculture(),
				new ModuleArboriculture(),
				new ModuleLepidopterology(),
				new ModuleFactory(),
				new ModuleEnergy(),
				new ModuleStorage(),
				new ModuleWorktable(),
				new ModuleSorting(),
				new ModuleFarming(),
				new ModuleCultivation(),
				new ModuleGendustry(),
				new ModuleExtraBees(),
				new ModuleExtraTrees()));
		PluginManager.runPollenRegistration();
		ModuleStorage.registerOptionalCrates();
		if (FabricLoader.getInstance().isModLoaded("trinkets")) {
			TrinketsCompat.init();
		}
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
