package com.leon1236.reforestry.apiculture.features;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.blocks.BlockHiveType;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.modules.features.FeatureCreativeTab;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureCreativeTabs {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureCreativeTab APICULTURE = REGISTRY.creativeTab("apiculture", tab -> {
        tab.icon(() -> ApicultureItems.BEE_COMBS.get(EnumHoneyComb.HONEY).item().getDefaultInstance());
        tab.displayItems((parameters, output) -> {
            output.accept(ApicultureBlocks.BEE_HOUSE.item());
            output.accept(ApicultureBlocks.APIARY.item());
            for (var alveary : ApicultureBlocks.ALVEARY.getAll().values()) {
                if (alveary.item() != null) {
                    output.accept(alveary.item());
                }
            }
            for (var hive : ApicultureBlocks.BEEHIVE.getAll().entrySet()) {
                if (hive.getKey() != BlockHiveType.SWARM && hive.getValue().item() != null) {
                    output.accept(hive.getValue().item());
                }
            }
            output.accept(ApicultureItems.SCOOP.item());
            output.accept(ApicultureItems.SCOOP_PROVEN.item());
            output.accept(ApicultureItems.SMOKER.item());
            output.accept(ApicultureItems.FRAME_UNTREATED.item());
            output.accept(ApicultureItems.FRAME_IMPREGNATED.item());
            output.accept(ApicultureItems.FRAME_PROVEN.item());
            output.accept(ApicultureItems.FRAME_CREATIVE.item());
            output.accept(ApicultureItems.APIARIST_HELMET.item());
            output.accept(ApicultureItems.APIARIST_CHEST.item());
            output.accept(ApicultureItems.APIARIST_LEGS.item());
            output.accept(ApicultureItems.APIARIST_BOOTS.item());
            for (Identifier speciesId : ApicultureGenetics.getAllSpeciesIds()) {
                for (FeatureItem<?> bee : new FeatureItem<?>[]{
                        ApicultureItems.BEE_QUEEN, ApicultureItems.BEE_DRONE, ApicultureItems.BEE_PRINCESS, ApicultureItems.BEE_LARVAE}) {
                    ItemStack stack = new ItemStack(bee.item());
                    stack.set(ApicultureDataComponents.BEE_GENOME.type(), ApicultureGenetics.getDefaultGenome(speciesId));
                    output.accept(stack);
                }
            }
            output.accept(ApicultureItems.HONEY_DROP.item());
            output.accept(ApicultureItems.HONEYDEW.item());
            output.accept(ApicultureItems.EXPERIENCE_DROP.item());
            output.accept(ApicultureItems.ROYAL_JELLY.item());
            output.accept(ApicultureItems.AMBER_DRONE.item());
            output.accept(ApicultureItems.HONEYED_SLICE.item());
            output.accept(ApicultureItems.HONEY_POT.item());
            output.accept(ApicultureItems.AMBROSIA.item());
            for (FeatureItem<?> feature : ApicultureItems.POLLEN_CLUSTER.getAll().values()) {
                output.accept(feature.item());
            }
            for (FeatureItem<?> feature : ApicultureItems.BEE_COMBS.getAll().values()) {
                output.accept(feature.item());
            }
            for (FeatureItem<?> feature : ApicultureItems.PROPOLIS.getAll().values()) {
                output.accept(feature.item());
            }
            for (var feature : ApicultureBlocks.BEE_COMB.getAll().values()) {
                if (feature.item() != null) {
                    output.accept(feature.item());
                }
            }
        });
    });

    public static void init() {
    }
}
