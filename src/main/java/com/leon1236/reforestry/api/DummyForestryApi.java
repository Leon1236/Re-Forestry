package com.leon1236.reforestry.api;

import com.leon1236.reforestry.api.agriculture.IFarmingManager;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.arboriculture.ITreeManager;
import com.leon1236.reforestry.api.circuits.ICircuitManager;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.IErrorManager;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.genetics.IFlowerTypeManager;
import com.leon1236.reforestry.api.genetics.IGeneticManager;
import com.leon1236.reforestry.api.genetics.filter.IFilterManager;
import com.leon1236.reforestry.api.genetics.pollen.IPollenManager;
import com.leon1236.reforestry.api.modules.IModuleManager;

public final class DummyForestryApi implements IForestryApi {
	public static final DummyForestryApi INSTANCE = new DummyForestryApi();

	private DummyForestryApi() {
	}

	private static UnsupportedOperationException notInitialized(String manager) {
		return new UnsupportedOperationException(
				"IForestryApi not initialized yet (requested " + manager + "). Call IForestryApi.setInstance before plugins.");
	}

	@Override
	public IAlleleManager getAlleleManager() {
		throw notInitialized("IAlleleManager");
	}

	@Override
	public IModuleManager getModuleManager() {
		throw notInitialized("IModuleManager");
	}

	@Override
	public IClimateManager getClimateManager() {
		throw notInitialized("IClimateManager");
	}

	@Override
	public IErrorManager getErrorManager() {
		throw notInitialized("IErrorManager");
	}

	@Override
	public IHiveManager getHiveManager() {
		throw notInitialized("IHiveManager");
	}

	@Override
	public ICircuitManager getCircuitManager() {
		throw notInitialized("ICircuitManager");
	}

	@Override
	public IFilterManager getFilterManager() {
		throw notInitialized("IFilterManager");
	}

	@Override
	public IFarmingManager getFarmingManager() {
		throw notInitialized("IFarmingManager");
	}

	@Override
	public IGeneticManager getGeneticManager() {
		throw notInitialized("IGeneticManager");
	}

	@Override
	public IFlowerTypeManager getFlowerTypeManager() {
		throw notInitialized("IFlowerTypeManager");
	}

	@Override
	public IPollenManager getPollenManager() {
		throw notInitialized("IPollenManager");
	}

	@Override
	public ITreeManager getTreeManager() {
		throw notInitialized("ITreeManager");
	}
}
