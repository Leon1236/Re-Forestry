package com.leon1236.reforestry.core.climate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.climate.ClimateState;
import com.leon1236.reforestry.api.climate.IBiomeProvider;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;

public final class ClimateProvider implements IClimateProvider, IBiomeProvider {
	private final LevelReader level;
	private final BlockPos pos;

	public ClimateProvider(LevelReader level, BlockPos pos) {
		this.level = level;
		this.pos = pos.immutable();
	}

	@Override
	public Holder<Biome> getBiome() {
		return level.getBiome(pos);
	}

	@Override
	public TemperatureType temperature() {
		if (level instanceof ServerLevel serverLevel) {
			ClimateState state = IForestryApi.get().getClimateManager().getState(serverLevel, pos);
			return state.temperature();
		}
		return IForestryApi.get().getClimateManager().getTemperature(getBiome());
	}

	@Override
	public HumidityType humidity() {
		if (level instanceof ServerLevel serverLevel) {
			ClimateState state = IForestryApi.get().getClimateManager().getState(serverLevel, pos);
			return state.humidity();
		}
		return IForestryApi.get().getClimateManager().getHumidity(getBiome());
	}
}
