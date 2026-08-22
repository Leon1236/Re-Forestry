package com.leon1236.reforestry.storage.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.compat.jei.JeiDescriptions;
import com.leon1236.reforestry.storage.features.BackpackItems;

@JeiPlugin
public class StorageJeiPlugin implements IModPlugin {
	@Override
	public Identifier getPluginUid() {
		return ReForestry.id("storage");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		JeiDescriptions.addDescription(registry, "miner_bag",
				BackpackItems.MINER_BACKPACK,
				BackpackItems.MINER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "digger_bag",
				BackpackItems.DIGGER_BACKPACK,
				BackpackItems.DIGGER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "forester_bag",
				BackpackItems.FORESTER_BACKPACK,
				BackpackItems.FORESTER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "hunter_bag",
				BackpackItems.HUNTER_BACKPACK,
				BackpackItems.HUNTER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "adventurer_bag",
				BackpackItems.ADVENTURER_BACKPACK,
				BackpackItems.ADVENTURER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "builder_bag",
				BackpackItems.BUILDER_BACKPACK,
				BackpackItems.BUILDER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, "brewer_bag",
				BackpackItems.BREWER_BACKPACK,
				BackpackItems.BREWER_BACKPACK_T_2
		);
		JeiDescriptions.addDescription(registry, BackpackItems.APIARIST_BACKPACK);
		JeiDescriptions.addDescription(registry, BackpackItems.LEPIDOPTERIST_BACKPACK);
	}
}
