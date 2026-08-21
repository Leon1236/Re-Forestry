package com.leon1236.reforestry.farming.farmlogic;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.agriculture.IFarmHousing;
import com.leon1236.reforestry.api.agriculture.IFarmListener;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.farming.multiblock.FarmFertilizerManager;
import com.leon1236.reforestry.farming.multiblock.FarmHydrationManager;
import com.leon1236.reforestry.farming.multiblock.IFarmInventoryInternal;

public interface IFarmHousingInternal extends IFarmHousing, IClimateProvider {
	@Nullable
	Level getWorldObj();

	BlockPos getTopCoord();

	void setUpFarmlandTargets(Map<Direction, List<FarmTarget>> targets);

	@Override
	IFarmInventoryInternal getFarmInventory();

	FarmHydrationManager getHydrationManager();

	FarmFertilizerManager getFertilizerManager();

	Collection<IFarmListener> getFarmListeners();
}
