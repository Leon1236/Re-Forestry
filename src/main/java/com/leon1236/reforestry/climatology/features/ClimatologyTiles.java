package com.leon1236.reforestry.climatology.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.climatology.tiles.TileHabitatFormer;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ClimatologyTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("climatology"));

	public static final FeatureBlockEntityType<TileHabitatFormer> HABITAT_FORMER =
			REGISTRY.blockEntityType("habitat_former", TileHabitatFormer::new);

	public static void init() {
		EnergyHelper.registerSided(HABITAT_FORMER.type());
		InventoryHelper.registerSided(HABITAT_FORMER.type());
		FluidHelper.registerSided(HABITAT_FORMER.type(), TileHabitatFormer::getTankManager);
	}
}
