package com.leon1236.reforestry.api;

import java.util.Objects;

import com.leon1236.reforestry.api.agriculture.IFarmingManager;
import com.leon1236.reforestry.api.circuits.ICircuitManager;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.IErrorManager;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.genetics.IFlowerTypeManager;
import com.leon1236.reforestry.api.genetics.IGeneticManager;
import com.leon1236.reforestry.api.genetics.filter.IFilterManager;
import com.leon1236.reforestry.api.genetics.pollen.IPollenManager;
import com.leon1236.reforestry.api.modules.IModuleManager;

public interface IForestryApi {
	static IForestryApi get() {
		return Holder.INSTANCE;
	}

	static void setInstance(IForestryApi instance) {
		Holder.INSTANCE = Objects.requireNonNull(instance, "IForestryApi instance");
	}

	IAlleleManager getAlleleManager();

	IModuleManager getModuleManager();

	IClimateManager getClimateManager();

	IErrorManager getErrorManager();

	IHiveManager getHiveManager();

	ICircuitManager getCircuitManager();

	IFilterManager getFilterManager();

	IFarmingManager getFarmingManager();

	IGeneticManager getGeneticManager();

	IFlowerTypeManager getFlowerTypeManager();

	IPollenManager getPollenManager();

	com.leon1236.reforestry.api.arboriculture.ITreeManager getTreeManager();

	final class Holder {
		static IForestryApi INSTANCE = DummyForestryApi.INSTANCE;

		private Holder() {
		}
	}
}
