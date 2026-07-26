package com.leon1236.reforestry;

import java.util.List;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leon1236.reforestry.apiculture.ModuleApiculture;
import com.leon1236.reforestry.arboriculture.ModuleArboriculture;
import com.leon1236.reforestry.core.ModuleCore;
import com.leon1236.reforestry.factory.ModuleFactory;
import com.leon1236.reforestry.modules.ModuleManager;
import com.leon1236.reforestry.storage.ModuleStorage;

public class ReForestry implements ModInitializer {
	public static final String MOD_ID = "reforestry";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModuleManager.INSTANCE.load(List.of(
				new ModuleCore(),
				new ModuleApiculture(),
				new ModuleArboriculture(),
				new ModuleFactory(),
				new ModuleStorage()));
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
