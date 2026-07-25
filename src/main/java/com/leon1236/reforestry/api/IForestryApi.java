package com.leon1236.reforestry.api;

import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.IErrorManager;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.modules.IModuleManager;
import com.leon1236.reforestry.core.ForestryApiImpl;

public interface IForestryApi {
    IForestryApi INSTANCE = ForestryApiImpl.get();

    IAlleleManager getAlleleManager();

    IModuleManager getModuleManager();

    IClimateManager getClimateManager();

    IErrorManager getErrorManager();

    IHiveManager getHiveManager();
}
