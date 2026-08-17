package com.leon1236.reforestry.worktable;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.worktable.client.WorktableClientHandler;
import com.leon1236.reforestry.worktable.features.WorktableBlocks;
import com.leon1236.reforestry.worktable.features.WorktableCreativeTabs;
import com.leon1236.reforestry.worktable.features.WorktableMenuTypes;
import com.leon1236.reforestry.worktable.features.WorktableTiles;

@ForestryModule(name = "Worktable", description = "A crafting table that remembers recipes.")
public class ModuleWorktable implements IForestryModule {
	@Override
	public Identifier getId() {
		return ReForestry.id("worktable");
	}

	@Override
	public List<Identifier> getModuleDependencies() {
		return List.of(ReForestry.id("core"));
	}

	@Override
	public void init() {
		WorktableBlocks.init();
		WorktableTiles.init();
		WorktableMenuTypes.init();
		WorktableCreativeTabs.init();
	}

	@Override
	public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
		registrar.accept(new WorktableClientHandler());
	}
}
