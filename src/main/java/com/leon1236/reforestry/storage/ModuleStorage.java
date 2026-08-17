package com.leon1236.reforestry.storage;

import java.util.List;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.ForestryTags;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.api.storage.IBackpackInterface;
import com.leon1236.reforestry.apiculture.features.ApicultureCrates;
import com.leon1236.reforestry.core.config.ForestryConfig;
import com.leon1236.reforestry.modules.ModuleManager;
import com.leon1236.reforestry.storage.client.StorageClientHandler;
import com.leon1236.reforestry.storage.features.BackpackItems;
import com.leon1236.reforestry.storage.features.CrateItems;
import com.leon1236.reforestry.storage.features.StorageCreativeTabs;
import com.leon1236.reforestry.storage.features.StorageDataComponents;
import com.leon1236.reforestry.storage.features.StorageItems;
import com.leon1236.reforestry.storage.features.StorageMenuTypes;

@ForestryModule(name = "Storage", description = "Backpacks and crates for storing items.")
public class ModuleStorage implements IForestryModule {
	public static final IBackpackInterface BACKPACK_INTERFACE = new BackpackInterface();

	public static final int WHITE = 0xffffff;
	public static final BackpackDefinition MINER = new BackpackDefinition(0x36187d, WHITE, new BackpackFilter(ForestryTags.Items.MINER_ALLOW, ForestryTags.Items.MINER_REJECT));
	public static final BackpackDefinition DIGGER = new BackpackDefinition(0x363cc5, WHITE, new BackpackFilter(ForestryTags.Items.DIGGER_ALLOW, ForestryTags.Items.DIGGER_REJECT));
	public static final BackpackDefinition FORESTER = new BackpackDefinition(0x347427, WHITE, new BackpackFilter(ForestryTags.Items.FORESTER_ALLOW, ForestryTags.Items.FORESTER_REJECT));
	public static final BackpackDefinition HUNTER = new BackpackDefinition(0x412215, WHITE, new BackpackFilter(ForestryTags.Items.HUNTER_ALLOW, ForestryTags.Items.HUNTER_REJECT));
	public static final BackpackDefinition ADVENTURER = new BackpackDefinition(0x7fb8c2, WHITE, new BackpackFilter(ForestryTags.Items.ADVENTURER_ALLOW, ForestryTags.Items.ADVENTURER_REJECT));
	public static final BackpackDefinition BUILDER = new BackpackDefinition(0xdd3a3a, WHITE, new BackpackFilter(ForestryTags.Items.BUILDER_ALLOW, ForestryTags.Items.BUILDER_REJECT));
	public static final BackpackDefinition BREWER = new BackpackDefinition(0xBD7CBD, WHITE, new BackpackFilter(ForestryTags.Items.BREWER_ALLOW, ForestryTags.Items.BREWER_REJECT));
	public static final BackpackDefinition APIARIST = new BackpackDefinition(0xc4923d, WHITE, BACKPACK_INTERFACE.createNaturalistBackpackFilter(ForestrySpeciesTypes.BEE));
	public static final BackpackDefinition ARBORIST = new BackpackDefinition(0x657e3a, WHITE, BACKPACK_INTERFACE.createNaturalistBackpackFilter(ForestrySpeciesTypes.TREE));
	public static final BackpackDefinition LEPIDOPTERIST = new BackpackDefinition(0x995b31, WHITE, BACKPACK_INTERFACE.createNaturalistBackpackFilter(ForestrySpeciesTypes.BUTTERFLY));

	@Override
	public Identifier getId() {
		return ReForestry.id("storage");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		StorageDataComponents.init();
		StorageItems.init();
		CrateItems.init();
		BackpackItems.init();
		StorageMenuTypes.init();
		StorageCreativeTabs.init();
		ServerTickEvents.END_LEVEL_TICK.register(level -> {
			if (!ForestryConfig.enableBackpackResupply()) {
				return;
			}
			for (var player : level.players()) {
				BackpackResupplyHandler.resupply(player);
			}
		});
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new StorageClientHandler());
	}

	public static void registerOptionalCrates() {
		if (!ModuleManager.INSTANCE.isModuleLoaded(ReForestry.id("storage"))) {
			return;
		}
		if (ModuleManager.INSTANCE.isModuleLoaded(ReForestry.id("apiculture"))) {
			ApicultureCrates.init();
		}
	}
}
