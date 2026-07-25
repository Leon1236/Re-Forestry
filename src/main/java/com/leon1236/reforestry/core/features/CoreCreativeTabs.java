package com.leon1236.reforestry.core.features;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.fluids.ForestryFluids;
import com.leon1236.reforestry.core.features.FluidsItems;
import com.leon1236.reforestry.core.items.definitions.EnumContainerType;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreCreativeTabs {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureCreativeTab REFORESTRY = REGISTRY.creativeTab(ReForestry.MOD_ID, tab -> {
        tab.icon(() -> CoreItems.BEESWAX.item().getDefaultInstance());
        tab.displayItems((parameters, output) -> {
            output.accept(CoreItems.BEESWAX.item());
            output.accept(CoreItems.APATITE.item());
            output.accept(CoreItems.RAW_TIN.item());
            output.accept(CoreItems.AMBER.item());
            output.accept(CoreItems.INGOT_TIN.item());
            output.accept(CoreItems.INGOT_BRONZE.item());
            output.accept(CoreItems.GEAR_BRONZE.item());
            output.accept(CoreItems.GEAR_COPPER.item());
            output.accept(CoreItems.GEAR_TIN.item());
            output.accept(CoreItems.STURDY_CASING.item());
            output.accept(CoreItems.HARDENED_CASING.item());
            output.accept(CoreItems.IMPREGNATED_CASING.item());
            output.accept(CoreItems.FLEXIBLE_CASING.item());
            output.accept(CoreItems.CARTON.item());
            output.accept(CoreItems.BROKEN_BRONZE_PICKAXE.item());
            output.accept(CoreItems.BROKEN_BRONZE_SHOVEL.item());
            output.accept(CoreItems.BROKEN_BRONZE_AXE.item());
            output.accept(CoreItems.BROKEN_BRONZE_SWORD.item());
            output.accept(CoreItems.BROKEN_BRONZE_HOE.item());
            output.accept(CoreItems.MOULDY_WHEAT.item());
            output.accept(CoreItems.DECAYING_WHEAT.item());
            output.accept(CoreItems.IODINE_CHARGE.item());
            output.accept(CoreItems.DISSIPATION_CHARGE.item());
            output.accept(CoreItems.REFRACTORY_WAX.item());
            output.accept(CoreItems.ASH.item());
            output.accept(CoreItems.PEAT.item());
            output.accept(CoreItems.BITUMINOUS_PEAT.item());
            output.accept(CoreItems.COMPOST.item());
            output.accept(CoreItems.FERTILIZER_COMPOUND.item());
            output.accept(CoreItems.MULCH.item());
            for (FeatureItem<?> feature : CoreItems.CIRCUITBOARDS.getAll().values()) {
                output.accept(feature.item());
            }
            for (FeatureItem<?> feature : CoreItems.ELECTRON_TUBES.getAll().values()) {
                output.accept(feature.item());
            }
            output.accept(CoreItems.SOLDERING_IRON.item());
            for (FeatureItem<?> feature : CoreItems.CRAFTING_MATERIALS.getAll().values()) {
                output.accept(feature.item());
            }
            for (FeatureItem<?> feature : CoreItems.FRUITS.getAll().values()) {
                output.accept(feature.item());
            }
            output.accept(CoreBlocks.BOG_EARTH.item());
            output.accept(CoreBlocks.HUMUS.item());
            for (var feature : CoreBlocks.RESOURCE_STORAGE.getAll().values()) {
                output.accept(feature.item());
            }
            output.accept(CoreBlocks.APATITE_ORE.item());
            output.accept(CoreBlocks.DEEPSLATE_APATITE_ORE.item());
            output.accept(CoreBlocks.TIN_ORE.item());
            output.accept(CoreBlocks.DEEPSLATE_TIN_ORE.item());
            output.accept(CoreBlocks.RAW_TIN_BLOCK.item());
            for (ForestryFluids fluid : ForestryFluids.values()) {
                output.accept(fluid.getBucket());
            }
            for (EnumContainerType type : EnumContainerType.values()) {
                output.accept(FluidsItems.CONTAINERS.item(type));
                output.accept(FluidsItems.createFilled(type, ForestryFluids.BIOMASS));
            }
        });
    });

    public static void init() {
    }
}
