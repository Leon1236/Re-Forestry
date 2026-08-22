package com.leon1236.reforestry.arboriculture.villagers;

import java.util.function.Predicate;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.NaturalistChestBlockType;
import com.leon1236.reforestry.core.features.CoreBlocks;

public final class ArboricultureVillagers {
    public static final ResourceKey<PoiType> POI_TREE_CHEST =
            ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ReForestry.id("tree_chest"));
    public static final ResourceKey<VillagerProfession> ARBORIST =
            ResourceKey.create(Registries.VILLAGER_PROFESSION, ReForestry.id("arborist"));

    public static final ResourceKey<TradeSet> ARBORIST_LEVEL_1 = tradeSet("arborist/level_1");
    public static final ResourceKey<TradeSet> ARBORIST_LEVEL_2 = tradeSet("arborist/level_2");
    public static final ResourceKey<TradeSet> ARBORIST_LEVEL_3 = tradeSet("arborist/level_3");
    public static final ResourceKey<TradeSet> ARBORIST_LEVEL_4 = tradeSet("arborist/level_4");

    private ArboricultureVillagers() {
    }

    public static void init() {
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("set_random_forestry_wood"),
                SetRandomForestryWoodFunction.MAP_CODEC);
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("set_random_tree_genome"),
                SetRandomTreeGenomeFunction.MAP_CODEC);

        PoiHelper.register(POI_TREE_CHEST.identifier(), 1, 1,
                CoreBlocks.NATURALIST_CHESTS.get(NaturalistChestBlockType.TREE_CHEST).block());

        Predicate<Holder<PoiType>> jobSite = poi -> poi.is(POI_TREE_CHEST);
        Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, ARBORIST, new VillagerProfession(
                Component.translatable("entity.reforestry.villager.arborist"),
                jobSite,
                jobSite,
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_FISHERMAN,
                Int2ObjectMap.ofEntries(
                        Int2ObjectMap.entry(1, ARBORIST_LEVEL_1),
                        Int2ObjectMap.entry(2, ARBORIST_LEVEL_2),
                        Int2ObjectMap.entry(3, ARBORIST_LEVEL_3),
                        Int2ObjectMap.entry(4, ARBORIST_LEVEL_4))));
    }

    private static ResourceKey<TradeSet> tradeSet(String path) {
        return ResourceKey.create(Registries.TRADE_SET, ReForestry.id(path));
    }
}
