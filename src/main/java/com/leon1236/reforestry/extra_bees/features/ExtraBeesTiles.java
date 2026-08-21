package com.leon1236.reforestry.extra_bees.features;

import net.minecraft.world.level.block.Block;

import team.reborn.energy.api.EnergyStorage;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.tiles.TileHive;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.extra_bees.blocks.BlockExtraBeeAlvearyType;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyFrameHousing;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyHatchery;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyLighting;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyMutator;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyRainShield;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyStimulator;
import com.leon1236.reforestry.extra_bees.multiblock.TileAlvearyTransmission;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ExtraBeesTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("extra_bees"));

	public static final FeatureBlockEntityType<TileHive> HIVE = REGISTRY.blockEntityType("extra_bees_hive",
			TileHive::new,
			ExtraBeesBlocks.BEEHIVE.getAll().values().stream().map(FeatureBlock::block).toArray(Block[]::new));

	public static final FeatureBlockEntityType<TileAlvearyMutator> ALVEARY_MUTATOR =
			REGISTRY.blockEntityType("alveary_mutator", TileAlvearyMutator::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.MUTATOR).block());

	public static final FeatureBlockEntityType<TileAlvearyFrameHousing> ALVEARY_FRAME =
			REGISTRY.blockEntityType("alveary_frame", TileAlvearyFrameHousing::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.FRAME).block());

	public static final FeatureBlockEntityType<TileAlvearyRainShield> ALVEARY_RAIN_SHIELD =
			REGISTRY.blockEntityType("alveary_rain_shield", TileAlvearyRainShield::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.RAIN_SHIELD).block());

	public static final FeatureBlockEntityType<TileAlvearyLighting> ALVEARY_LIGHTING =
			REGISTRY.blockEntityType("alveary_lighting", TileAlvearyLighting::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.LIGHTING).block());

	public static final FeatureBlockEntityType<TileAlvearyStimulator> ALVEARY_STIMULATOR =
			REGISTRY.blockEntityType("alveary_stimulator", TileAlvearyStimulator::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.STIMULATOR).block());

	public static final FeatureBlockEntityType<TileAlvearyHatchery> ALVEARY_HATCHERY =
			REGISTRY.blockEntityType("alveary_hatchery", TileAlvearyHatchery::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.HATCHERY).block());

	public static final FeatureBlockEntityType<TileAlvearyTransmission> ALVEARY_TRANSMISSION =
			REGISTRY.blockEntityType("alveary_transmission", TileAlvearyTransmission::new,
					ExtraBeesBlocks.ALVEARY.get(BlockExtraBeeAlvearyType.TRANSMISSION).block());

	public static void init() {
		InventoryHelper.registerSided(ALVEARY_MUTATOR.type());
		InventoryHelper.registerSided(ALVEARY_FRAME.type());
		InventoryHelper.registerSided(ALVEARY_STIMULATOR.type());
		InventoryHelper.registerSided(ALVEARY_HATCHERY.type());
		EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyStorage(), ALVEARY_STIMULATOR.type());
		EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getEnergyStorage(), ALVEARY_TRANSMISSION.type());
	}
}
