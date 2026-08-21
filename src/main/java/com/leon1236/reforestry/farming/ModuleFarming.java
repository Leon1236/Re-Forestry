package com.leon1236.reforestry.farming;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.plugin.PluginManager;
import com.leon1236.reforestry.farming.client.FarmingClientHandler;
import com.leon1236.reforestry.farming.features.FarmingBlocks;
import com.leon1236.reforestry.farming.features.FarmingCreativeTabs;
import com.leon1236.reforestry.farming.features.FarmingMenuTypes;
import com.leon1236.reforestry.farming.features.FarmingTiles;

@ForestryModule(name = "Farming", description = "Adds automatic multiblock farm for a wide variety of products.")
public class ModuleFarming implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("farming");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		FarmingBlocks.init();
		FarmingTiles.init();
		FarmingMenuTypes.init();
		FarmingCreativeTabs.init();
		PluginManager.runFarmingRegistration(registration -> {
			Item fertilizer = CoreItems.FERTILIZER_COMPOUND.item();
			if (registration.getFertilizers().getInt(fertilizer) <= 0) {
				registration.registerFertilizer(fertilizer, 500);
			}
		});
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new FarmingClientHandler());
	}
}
