package com.leon1236.reforestry.gendustry;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.gendustry.client.GendustryClientHandler;
import com.leon1236.reforestry.gendustry.features.GBlockEntities;
import com.leon1236.reforestry.gendustry.features.GBlocks;
import com.leon1236.reforestry.gendustry.features.GCreativeTabs;
import com.leon1236.reforestry.gendustry.features.GItems;
import com.leon1236.reforestry.gendustry.features.GMenus;
import com.leon1236.reforestry.gendustry.features.GRecipeTypes;
import com.leon1236.reforestry.gendustry.fluids.GFluids;
import com.leon1236.reforestry.gendustry.recipe.cache.DnaRecipeCache;
import com.leon1236.reforestry.gendustry.recipe.cache.MutagenRecipeCache;
import com.leon1236.reforestry.gendustry.recipe.cache.ProteinRecipeCache;
import com.leon1236.reforestry.gendustry.recipe.cache.RecipeCacheRegistry;

@ForestryModule(name = "Gendustry", description = "Industrial genetics machines, upgrades, and pollen tools.")
public class ModuleGendustry implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("gendustry");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(
				ReForestry.id("core"),
				ReForestry.id("apiculture"),
				ReForestry.id("arboriculture"),
				ReForestry.id("lepidopterology"));
	}

	@Override
	public void init() {
		GFluids.init();
		GItems.init();
		GBlocks.init();
		GBlockEntities.init();
		GMenus.init();
		GRecipeTypes.init();
		GCreativeTabs.init();
		new RecipeCacheRegistry(registrar -> {
			registrar.accept(MutagenRecipeCache.INSTANCE);
			registrar.accept(DnaRecipeCache.INSTANCE);
			registrar.accept(ProteinRecipeCache.INSTANCE);
		});
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new GendustryClientHandler());
	}
}
