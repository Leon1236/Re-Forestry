package com.leon1236.reforestry.extratrees.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.extratrees.tiles.TileBottleRack;
import com.leon1236.reforestry.extratrees.tiles.TileBrewery;
import com.leon1236.reforestry.extratrees.tiles.TileDistillery;
import com.leon1236.reforestry.extratrees.tiles.TileLumbermill;
import com.leon1236.reforestry.extratrees.tiles.TilePress;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraTreesTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_trees"));

	public static final FeatureBlockEntityType<TileLumbermill> LUMBERMILL =
			REGISTRY.blockEntityType("lumbermill", TileLumbermill::new);
	public static final FeatureBlockEntityType<TilePress> PRESS =
			REGISTRY.blockEntityType("press", TilePress::new);
	public static final FeatureBlockEntityType<TileBrewery> BREWERY =
			REGISTRY.blockEntityType("brewery", TileBrewery::new);
	public static final FeatureBlockEntityType<TileDistillery> DISTILLERY =
			REGISTRY.blockEntityType("distillery", TileDistillery::new);
	public static final FeatureBlockEntityType<TileBottleRack> BOTTLE_RACK =
			REGISTRY.blockEntityType("bottle_rack", TileBottleRack::new);

	public static void init() {
		EnergyHelper.registerSided(LUMBERMILL.type());
		InventoryHelper.registerSided(LUMBERMILL.type());
		FluidHelper.registerSided(LUMBERMILL.type(), TileLumbermill::getTankManager);

		EnergyHelper.registerSided(PRESS.type());
		InventoryHelper.registerSided(PRESS.type());
		FluidHelper.registerSided(PRESS.type(), TilePress::getTankManager);

		EnergyHelper.registerSided(BREWERY.type());
		InventoryHelper.registerSided(BREWERY.type());
		FluidHelper.registerSided(BREWERY.type(), TileBrewery::getTankManager);

		EnergyHelper.registerSided(DISTILLERY.type());
		FluidHelper.registerSided(DISTILLERY.type(), TileDistillery::getTankManager);

		FluidHelper.registerSided(BOTTLE_RACK.type(), TileBottleRack::getTankManager);
	}
}
