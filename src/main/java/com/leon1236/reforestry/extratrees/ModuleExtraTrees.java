package com.leon1236.reforestry.extratrees;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.extratrees.client.ExtraTreesClientHandler;
import com.leon1236.reforestry.extratrees.features.ExtraTreesBlocks;
import com.leon1236.reforestry.extratrees.features.ExtraTreesCreativeTabs;
import com.leon1236.reforestry.extratrees.features.ExtraTreesEntities;
import com.leon1236.reforestry.extratrees.features.ExtraTreesItems;
import com.leon1236.reforestry.extratrees.features.ExtraTreesMenuTypes;
import com.leon1236.reforestry.extratrees.features.ExtraTreesTiles;

@ForestryModule(name = "Extra Trees", description = "Extra Trees woods, fruits, machines, and moths.")
public class ModuleExtraTrees implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("extra_trees");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"), ReForestry.id("arboriculture"), ReForestry.id("lepidopterology"));
	}

	@Override
	public void init() {
		ExtraTreesBlocks.init();
		ExtraTreesEntities.init();
		ExtraTreesItems.init();
		ExtraTreesTiles.init();
		ExtraTreesMenuTypes.init();
		ExtraTreesCreativeTabs.init();
		com.leon1236.reforestry.extratrees.fluids.ExtraTreesFluids.init();
		com.leon1236.reforestry.extratrees.recipes.ExtraTreesAlcoholRecipes.init();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new ExtraTreesClientHandler());
	}
}
