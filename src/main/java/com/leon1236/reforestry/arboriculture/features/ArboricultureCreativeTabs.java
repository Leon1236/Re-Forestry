package com.leon1236.reforestry.arboriculture.features;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.VanillaWoodType;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.modules.features.FeatureBlock;
import com.leon1236.reforestry.modules.features.FeatureBlockGroup;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ArboricultureCreativeTabs {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("arboriculture"));

    private static final List<FeatureBlockGroup<?, VanillaWoodType>> VANILLA_WOOD_GROUPS = List.of(
            ArboricultureBlocks.FIREPROOF_LOG,
            ArboricultureBlocks.FIREPROOF_STRIPPED_LOG,
            ArboricultureBlocks.FIREPROOF_WOOD,
            ArboricultureBlocks.FIREPROOF_STRIPPED_WOOD,
            ArboricultureBlocks.FIREPROOF_PLANKS,
            ArboricultureBlocks.FIREPROOF_SLAB,
            ArboricultureBlocks.FIREPROOF_FENCE,
            ArboricultureBlocks.FIREPROOF_FENCE_GATE,
            ArboricultureBlocks.FIREPROOF_STAIRS
    );

    private static final List<FeatureBlockGroup<?, ForestryWoodType>> FOREST_WOOD_GROUPS = List.of(
            ArboricultureBlocks.LOGS,
            ArboricultureBlocks.LOGS_FIREPROOF,
            ArboricultureBlocks.STRIPPED_LOGS,
            ArboricultureBlocks.STRIPPED_LOGS_FIREPROOF,
            ArboricultureBlocks.WOOD,
            ArboricultureBlocks.WOOD_FIREPROOF,
            ArboricultureBlocks.STRIPPED_WOOD,
            ArboricultureBlocks.STRIPPED_WOOD_FIREPROOF,
            ArboricultureBlocks.PLANKS,
            ArboricultureBlocks.PLANKS_FIREPROOF,
            ArboricultureBlocks.SLABS,
            ArboricultureBlocks.SLABS_FIREPROOF,
            ArboricultureBlocks.FENCES,
            ArboricultureBlocks.FENCES_FIREPROOF,
            ArboricultureBlocks.FENCE_GATES,
            ArboricultureBlocks.FENCE_GATES_FIREPROOF,
            ArboricultureBlocks.STAIRS,
            ArboricultureBlocks.STAIRS_FIREPROOF,
            ArboricultureBlocks.DOORS,
            ArboricultureBlocks.TRAPDOORS,
            ArboricultureBlocks.BUTTON,
            ArboricultureBlocks.PRESSURE_PLATE,
            ArboricultureBlocks.SIGN,
            ArboricultureBlocks.HANGING_SIGN
    );

    public static final FeatureCreativeTab ARBORICULTURE = REGISTRY.creativeTab("arboriculture", tab -> {
        tab.icon(() -> ArboricultureBlocks.FIREPROOF_LOG.get(VanillaWoodType.OAK).item().getDefaultInstance());
        tab.displayItems((parameters, output) -> {
            output.accept(ArboricultureItems.GRAFTER.item());
            output.accept(ArboricultureItems.GRAFTER_PROVEN.item());
            for (Identifier speciesId : ArboricultureGenetics.getAllSpeciesIds()) {
                for (FeatureItem<?> germling : new FeatureItem<?>[]{
                        ArboricultureItems.SAPLING, ArboricultureItems.POLLEN_FERTILE}) {
                    ItemStack stack = new ItemStack(germling.item());
                    stack.set(ArboricultureDataComponents.TREE_GENOME.type(), ArboricultureGenetics.getDefaultGenome(speciesId));
                    output.accept(stack);
                }
            }
            output.accept(ArboricultureItems.AMBER_SAPLING.item());
            for (FeatureBlockGroup<?, VanillaWoodType> group : VANILLA_WOOD_GROUPS) {
                for (FeatureBlock<?> feature : group.getAll().values()) {
                    output.accept(feature.item());
                }
            }
            for (FeatureBlockGroup<?, ForestryWoodType> group : FOREST_WOOD_GROUPS) {
                for (FeatureBlock<?> feature : group.getAll().values()) {
                    if (feature.item() != null) {
                        output.accept(feature.item());
                    }
                }
            }
            for (FeatureBlock<?> feature : ArboricultureBlocks.LEAVES_DECORATIVE.getAll().values()) {
                if (feature.item() != null) {
                    output.accept(feature.item());
                }
            }
        });
    });

    public static void init() {
    }
}
