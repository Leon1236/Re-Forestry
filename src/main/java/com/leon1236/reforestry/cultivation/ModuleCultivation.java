package com.leon1236.reforestry.cultivation;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.cultivation.blocks.BlockTypePlanter;
import com.leon1236.reforestry.cultivation.client.CultivationClientHandler;
import com.leon1236.reforestry.cultivation.features.CultivationBlocks;
import com.leon1236.reforestry.cultivation.features.CultivationMenuTypes;
import com.leon1236.reforestry.cultivation.features.CultivationTiles;
import com.leon1236.reforestry.farming.features.FarmingCreativeTabs;

@ForestryModule(name = "Cultivation", description = "Adds automatic farms for a wide variety of products.")
public class ModuleCultivation implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("cultivation");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("farming"));
	}

	@Override
	public void init() {
		CultivationBlocks.init();
		CultivationTiles.init();
		CultivationMenuTypes.init();
		FarmingCreativeTabs.setAgricultureIcon(
				() -> CultivationBlocks.MANAGED_PLANTER.stack(BlockTypePlanter.ARBORETUM));
		FarmingCreativeTabs.addAgricultureItems(output -> {
			for (BlockTypePlanter type : BlockTypePlanter.values()) {
				output.accept(CultivationBlocks.MANAGED_PLANTER.stack(type));
				output.accept(CultivationBlocks.MANUAL_PLANTER.stack(type));
			}
		});
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new CultivationClientHandler());
	}
}
