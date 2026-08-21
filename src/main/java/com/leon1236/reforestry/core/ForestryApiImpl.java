package com.leon1236.reforestry.core;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.apiculture.hives.IHiveManager;
import com.leon1236.reforestry.api.climate.IClimateManager;
import com.leon1236.reforestry.api.core.IErrorManager;
import com.leon1236.reforestry.api.genetics.IAlleleManager;
import com.leon1236.reforestry.api.genetics.IFlowerTypeManager;
import com.leon1236.reforestry.api.genetics.IGeneticManager;
import com.leon1236.reforestry.api.genetics.pollen.IPollenManager;
import com.leon1236.reforestry.api.modules.IModuleManager;
import com.leon1236.reforestry.api.arboriculture.ITreeManager;
import com.leon1236.reforestry.api.circuits.ICircuitManager;
import com.leon1236.reforestry.apiculture.hives.HiveManager;
import com.leon1236.reforestry.arboriculture.TreeManager;
import com.leon1236.reforestry.arboriculture.charcoal.CharcoalManager;
import com.leon1236.reforestry.core.circuits.CircuitManager;
import com.leon1236.reforestry.core.climate.ForestryClimateManager;
import com.leon1236.reforestry.core.errors.ErrorManager;
import com.leon1236.reforestry.core.genetics.FlowerTypeManager;
import com.leon1236.reforestry.core.genetics.GeneticManager;
import com.leon1236.reforestry.core.genetics.alleles.AlleleManager;
import com.leon1236.reforestry.core.genetics.pollen.PollenManager;
import com.leon1236.reforestry.modules.ModuleManager;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;

public final class ForestryApiImpl implements IForestryApi {
    private static final ForestryApiImpl INSTANCE = new ForestryApiImpl();

    private final ForestryClimateManager climateManager = new ForestryClimateManager();
    private final ErrorManager errorManager = new ErrorManager();
    private IHiveManager hiveManager = new HiveManager(ImmutableMap.of());
    private ICircuitManager circuitManager = new CircuitManager(ImmutableMultimap.of(), ImmutableMap.of(), ImmutableMap.of());
    private ITreeManager treeManager = new TreeManager(ImmutableMap.of(), new CharcoalManager());
    private final GeneticManager geneticManager = new GeneticManager();

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

    public void setCircuitManager(ICircuitManager circuitManager) {
        this.circuitManager = circuitManager;
    }

    public void setTreeManager(ITreeManager treeManager) {
        this.treeManager = treeManager;
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

    @Override
    public ICircuitManager getCircuitManager() {
        return circuitManager;
    }

    @Override
    public IGeneticManager getGeneticManager() {
        return geneticManager;
    }

    @Override
    public IFlowerTypeManager getFlowerTypeManager() {
        return FlowerTypeManager.INSTANCE;
    }

    @Override
    public IPollenManager getPollenManager() {
        return PollenManager.INSTANCE;
    }

    @Override
    public ITreeManager getTreeManager() {
        return treeManager;
    }

    public GeneticManager getMutableGeneticManager() {
        return geneticManager;
    }
}
