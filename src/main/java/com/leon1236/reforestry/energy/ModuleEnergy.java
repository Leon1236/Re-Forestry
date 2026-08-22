package com.leon1236.reforestry.energy;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.api.fuels.EngineBronzeFuel;
import com.leon1236.reforestry.api.fuels.EngineCopperFuel;
import com.leon1236.reforestry.api.fuels.FuelManager;
import com.leon1236.reforestry.api.modules.ForestryModule;
import com.leon1236.reforestry.api.modules.IForestryModule;
import com.leon1236.reforestry.core.features.CoreItems;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.energy.client.EnergyClientHandler;
import com.leon1236.reforestry.energy.features.EnergyBlocks;
import com.leon1236.reforestry.energy.features.EnergyMenus;
import com.leon1236.reforestry.energy.features.EnergyTiles;

@ForestryModule(name = "Energy", description = "Adds several RF engines.")
public class ModuleEnergy implements IForestryModule {
    @Override
    public Identifier getId() {
        return ReForestry.id("energy");
    }

    @Override
    public List<Identifier> getModuleDependencies() {
        return List.of(ReForestry.id("core"));
    }

    @Override
    public void init() {
        setupApi();
        EnergyBlocks.init();
        EnergyTiles.init();
        EnergyMenus.init();
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        registrar.accept(new EnergyClientHandler());
    }

    private static void setupApi() {
        Fluid biomass = ForestryFluids.BIOMASS.getFluid();
        FuelManager.registerBiogasEngineFuel(biomass, new EngineBronzeFuel(biomass,
                EnergyConstants.ENGINE_FUEL_VALUE_BIOMASS, EnergyConstants.ENGINE_CYCLE_DURATION_BIOMASS, 1));

        FuelManager.registerBiogasEngineFuel(Fluids.WATER, new EngineBronzeFuel(Fluids.WATER,
                EnergyConstants.ENGINE_FUEL_VALUE_WATER, EnergyConstants.ENGINE_CYCLE_DURATION_WATER, 3));

        Fluid milk = ForestryFluids.MILK.getFluid();
        FuelManager.registerBiogasEngineFuel(milk, new EngineBronzeFuel(milk,
                EnergyConstants.ENGINE_FUEL_VALUE_MILK, EnergyConstants.ENGINE_CYCLE_DURATION_MILK, 3));

        Fluid seedOil = ForestryFluids.SEED_OIL.getFluid();
        FuelManager.registerBiogasEngineFuel(seedOil, new EngineBronzeFuel(seedOil,
                EnergyConstants.ENGINE_FUEL_VALUE_SEED_OIL, EnergyConstants.ENGINE_CYCLE_DURATION_SEED_OIL, 1));

        Fluid honey = ForestryFluids.HONEY.getFluid();
        FuelManager.registerBiogasEngineFuel(honey, new EngineBronzeFuel(honey,
                EnergyConstants.ENGINE_FUEL_VALUE_HONEY, EnergyConstants.ENGINE_CYCLE_DURATION_HONEY, 1));

        Fluid juice = ForestryFluids.JUICE.getFluid();
        FuelManager.registerBiogasEngineFuel(juice, new EngineBronzeFuel(juice,
                EnergyConstants.ENGINE_FUEL_VALUE_JUICE, EnergyConstants.ENGINE_CYCLE_DURATION_JUICE, 1));

        ItemStack peat = new ItemStack(CoreItems.PEAT.item());
        FuelManager.registerPeatEngineFuel(peat, new EngineCopperFuel(peat,
                EnergyConstants.ENGINE_COPPER_FUEL_VALUE_PEAT, EnergyConstants.ENGINE_COPPER_CYCLE_DURATION_PEAT));

        ItemStack bituminousPeat = new ItemStack(CoreItems.BITUMINOUS_PEAT.item());
        FuelManager.registerPeatEngineFuel(bituminousPeat, new EngineCopperFuel(bituminousPeat,
                EnergyConstants.ENGINE_COPPER_FUEL_VALUE_BITUMINOUS_PEAT,
                EnergyConstants.ENGINE_COPPER_CYCLE_DURATION_BITUMINOUS_PEAT));
    }
}
