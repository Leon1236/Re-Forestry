package com.leon1236.reforestry.farming.features;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

import net.minecraft.world.level.block.Block;

import team.reborn.energy.api.EnergyStorage;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.farming.blocks.EnumFarmBlockType;
import com.leon1236.reforestry.farming.multiblock.TileFarmControl;
import com.leon1236.reforestry.farming.multiblock.TileFarmGearbox;
import com.leon1236.reforestry.farming.multiblock.TileFarmHatch;
import com.leon1236.reforestry.farming.multiblock.TileFarmPlain;
import com.leon1236.reforestry.farming.multiblock.TileFarmValve;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class FarmingTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("farming"));

	public static final FeatureBlockEntityType<TileFarmPlain> PLAIN = REGISTRY.blockEntityType(
			"plain", TileFarmPlain::new, rowBlocks(EnumFarmBlockType.PLAIN));

	public static final FeatureBlockEntityType<TileFarmGearbox> GEARBOX = REGISTRY.blockEntityType(
			"gearbox", TileFarmGearbox::new, rowBlocks(EnumFarmBlockType.GEARBOX));

	public static final FeatureBlockEntityType<TileFarmHatch> HATCH = REGISTRY.blockEntityType(
			"hatch", TileFarmHatch::new, rowBlocks(EnumFarmBlockType.HATCH));

	public static final FeatureBlockEntityType<TileFarmValve> VALVE = REGISTRY.blockEntityType(
			"valve", TileFarmValve::new, rowBlocks(EnumFarmBlockType.VALVE));

	public static final FeatureBlockEntityType<TileFarmControl> CONTROL = REGISTRY.blockEntityType(
			"control", TileFarmControl::new, rowBlocks(EnumFarmBlockType.CONTROL));

	private static Block[] rowBlocks(EnumFarmBlockType type) {
		return FarmingBlocks.FARM.getRowBlocks(type).toArray(Block[]::new);
	}

	public static void init() {
		EnergyStorage.SIDED.registerForBlockEntity(
				(tile, direction) -> tile.isRemoved() ? null : tile.getEnergyStorage(), GEARBOX.type());
		InventoryHelper.registerSided(HATCH.type());
		ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> Storage.<ItemVariant>empty(), PLAIN.type());
		ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> Storage.<ItemVariant>empty(), GEARBOX.type());
		ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> Storage.<ItemVariant>empty(), VALVE.type());
		ItemStorage.SIDED.registerForBlockEntity((tile, direction) -> Storage.<ItemVariant>empty(), CONTROL.type());
		FluidStorage.SIDED.registerForBlockEntity((tile, direction) -> tile.getTank(), VALVE.type());
	}
}
