package com.leon1236.reforestry.energy.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.FluidHelper;
import com.leon1236.reforestry.core.inventory.InventoryHelper;
import com.leon1236.reforestry.energy.blocks.EngineBlock;
import com.leon1236.reforestry.energy.tiles.BiogasEngineBlockEntity;
import com.leon1236.reforestry.energy.tiles.ClockworkEngineBlockEntity;
import com.leon1236.reforestry.energy.tiles.EngineBlockEntity;
import com.leon1236.reforestry.energy.tiles.PeatEngineBlockEntity;
import com.leon1236.reforestry.modules.features.FeatureBlockEntityType;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import team.reborn.energy.api.EnergyStorage;

public class EnergyTiles {
	private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("energy"));

	public static final FeatureBlockEntityType<PeatEngineBlockEntity> PEAT_ENGINE =
			REGISTRY.blockEntityType("peat_engine", PeatEngineBlockEntity::new);
	public static final FeatureBlockEntityType<BiogasEngineBlockEntity> BIOGAS_ENGINE =
			REGISTRY.blockEntityType("biogas_engine", BiogasEngineBlockEntity::new);
	public static final FeatureBlockEntityType<ClockworkEngineBlockEntity> CLOCKWORK_ENGINE =
			REGISTRY.blockEntityType("clockwork_engine", ClockworkEngineBlockEntity::new);

	public static void init() {
		registerEngineSided(PEAT_ENGINE.type());
		InventoryHelper.registerSided(PEAT_ENGINE.type());
		registerEngineSided(BIOGAS_ENGINE.type());
		InventoryHelper.registerSided(BIOGAS_ENGINE.type());
		FluidHelper.registerSided(BIOGAS_ENGINE.type(), BiogasEngineBlockEntity::getTankManager);
		registerEngineSided(CLOCKWORK_ENGINE.type());
	}

	private static void registerEngineSided(BlockEntityType<? extends EngineBlockEntity> type) {
		EnergyStorage.SIDED.registerForBlockEntity((tile, direction) -> {
			if (tile.isRemoved()) {
				return null;
			}
			BlockState state = tile.getBlockState();
			if (!state.hasProperty(EngineBlock.VERTICAL_FACING)
					|| direction != state.getValue(EngineBlock.VERTICAL_FACING)) {
				return null;
			}
			return tile.getEnergyManager();
		}, type);
	}
}
