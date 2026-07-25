package com.leon1236.reforestry.core;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.IErrorManager;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.modules.IModuleManager;
import com.leon1236.reforestry.apiculture.hives.HiveManager;
import com.leon1236.reforestry.core.climate.ForestryClimateManager;
import com.leon1236.reforestry.core.errors.ErrorManager;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.modules.ModuleManager;

import com.google.common.collect.ImmutableMap;

public final class ForestryApiImpl implements IForestryApi {
    private static final ForestryApiImpl INSTANCE = new ForestryApiImpl();

    private final ForestryClimateManager climateManager = new ForestryClimateManager();
    private final ErrorManager errorManager = new ErrorManager();
    private IHiveManager hiveManager = new HiveManager(ImmutableMap.of());

    private ForestryApiImpl() {
    }

    public static IForestryApi get() {
        return INSTANCE;
    }

    public ForestryClimateManager getForestryClimateManager() {
        return climateManager;
    }

    public void setHiveManager(IHiveManager hiveManager) {
        this.hiveManager = hiveManager;
    }

    @Override
    public IAlleleManager getAlleleManager() {
        return AlleleManager.INSTANCE;
    }

    @Override
    public IModuleManager getModuleManager() {
        return ModuleManager.INSTANCE;
    }

    @Override
    public IClimateManager getClimateManager() {
        return climateManager;
    }

    @Override
    public IErrorManager getErrorManager() {
        return errorManager;
    }

    @Override
    public IHiveManager getHiveManager() {
        return hiveManager;
    }
}
