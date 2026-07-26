package com.leon1236.reforestry.storage;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.api.storage.IBackpackInterface;
import com.leon1236.reforestry.storage.client.StorageClientHandler;
import com.leon1236.reforestry.storage.features.StorageCreativeTabs;
import com.leon1236.reforestry.storage.features.StorageItems;
import com.leon1236.reforestry.storage.features.StorageMenuTypes;

@ForestryModule(name = "Storage", description = "Backpacks and crates for storing items.")
public class ModuleStorage implements IForestryModule {
	public static final IBackpackInterface BACKPACK_INTERFACE = new BackpackInterface();

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
		StorageItems.init();
		StorageMenuTypes.init();
		StorageCreativeTabs.init();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new StorageClientHandler());
	}
}
