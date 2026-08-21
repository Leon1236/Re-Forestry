package com.leon1236.reforestry.api;

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
import com.leon1236.reforestry.core.ForestryApiImpl;

public interface IForestryApi {
    IForestryApi INSTANCE = ForestryApiImpl.get();

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
}
