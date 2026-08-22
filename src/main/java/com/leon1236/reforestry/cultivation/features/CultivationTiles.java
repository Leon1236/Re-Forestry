package com.leon1236.reforestry.cultivation.features;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;

import net.minecraft.world.level.block.entity.BlockEntityType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.energy.EnergyHelper;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.cultivation.blocks.BlockTypePlanter;
import com.leon1236.reforestry.cultivation.tiles.TileArboretum;
import com.leon1236.reforestry.cultivation.tiles.TileBog;
import com.leon1236.reforestry.cultivation.tiles.TileFarmCrops;
import com.leon1236.reforestry.cultivation.tiles.TileFarmEnder;
import com.leon1236.reforestry.cultivation.tiles.TileFarmGourd;
import com.leon1236.reforestry.cultivation.tiles.TileFarmMushroom;
import com.leon1236.reforestry.cultivation.tiles.TileFarmNether;
import com.leon1236.reforestry.cultivation.tiles.TilePlanter;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CultivationTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("cultivation"));

	public static final FeatureBlockEntityType<TileArboretum> ARBORETUM =
			REGISTRY.blockEntityType("arboretum", TileArboretum::new);
	public static final FeatureBlockEntityType<TileBog> BOG =
			REGISTRY.blockEntityType("bog", TileBog::new);
	public static final FeatureBlockEntityType<TileFarmCrops> CROPS =
			REGISTRY.blockEntityType("crops", TileFarmCrops::new);
	public static final FeatureBlockEntityType<TileFarmEnder> ENDER =
			REGISTRY.blockEntityType("ender", TileFarmEnder::new);
	public static final FeatureBlockEntityType<TileFarmGourd> GOURD =
			REGISTRY.blockEntityType("gourd", TileFarmGourd::new);
	public static final FeatureBlockEntityType<TileFarmMushroom> MUSHROOM =
			REGISTRY.blockEntityType("mushroom", TileFarmMushroom::new);
	public static final FeatureBlockEntityType<TileFarmNether> NETHER =
			REGISTRY.blockEntityType("nether", TileFarmNether::new);

	public static void init() {
		bindPlanter(ARBORETUM, BlockTypePlanter.ARBORETUM);
		bindPlanter(BOG, BlockTypePlanter.PEAT_POG);
		bindPlanter(CROPS, BlockTypePlanter.FARM_CROPS);
		bindPlanter(ENDER, BlockTypePlanter.FARM_ENDER);
		bindPlanter(GOURD, BlockTypePlanter.FARM_GOURD);
		bindPlanter(MUSHROOM, BlockTypePlanter.FARM_MUSHROOM);
		bindPlanter(NETHER, BlockTypePlanter.FARM_NETHER);
	}

	private static <T extends TilePlanter> void bindPlanter(FeatureBlockEntityType<T> feature, BlockTypePlanter type) {
		BlockEntityType<T> be = feature.type();
		FabricBlockEntityType fabricType = (FabricBlockEntityType) be;
		fabricType.addValidBlock(CultivationBlocks.MANAGED_PLANTER.get(type).block());
		fabricType.addValidBlock(CultivationBlocks.MANUAL_PLANTER.get(type).block());
		EnergyHelper.registerSided(be);
		InventoryHelper.registerSided(be);
		FluidHelper.registerSided(be, TilePlanter::getWaterTank);
	}
}
