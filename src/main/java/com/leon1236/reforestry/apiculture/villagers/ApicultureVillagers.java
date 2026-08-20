package com.leon1236.reforestry.apiculture.villagers;

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
import com.leon1236.reforestry.core.blocks.BlockTypeCore;
import com.leon1236.reforestry.core.features.CoreBlocks;

public final class ApicultureVillagers {
    public static final ResourceKey<PoiType> POI_ESCRITOIRE =
            ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, ReForestry.id("escritoire"));
    public static final ResourceKey<VillagerProfession> BEEKEEPER =
            ResourceKey.create(Registries.VILLAGER_PROFESSION, ReForestry.id("beekeeper"));

    public static final ResourceKey<TradeSet> BEEKEEPER_LEVEL_1 = tradeSet("beekeeper/level_1");
    public static final ResourceKey<TradeSet> BEEKEEPER_LEVEL_2 = tradeSet("beekeeper/level_2");
    public static final ResourceKey<TradeSet> BEEKEEPER_LEVEL_3 = tradeSet("beekeeper/level_3");
    public static final ResourceKey<TradeSet> BEEKEEPER_LEVEL_4 = tradeSet("beekeeper/level_4");

    private ApicultureVillagers() {
    }

    public static void init() {
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("set_random_village_comb"),
                SetRandomVillageCombFunction.MAP_CODEC);
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("set_random_mundane_drone"),
                SetRandomMundaneDroneFunction.MAP_CODEC);
        Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("set_bee_species_genome"),
                SetBeeSpeciesGenomeFunction.MAP_CODEC);

        PoiHelper.register(POI_ESCRITOIRE.identifier(), 1, 1,
                CoreBlocks.MACHINES.get(BlockTypeCore.ESCRITOIRE).block());

        Predicate<Holder<PoiType>> jobSite = poi -> poi.is(POI_ESCRITOIRE);
        Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, BEEKEEPER, new VillagerProfession(
                Component.translatable("entity.reforestry.villager.beekeeper"),
                jobSite,
                jobSite,
                ImmutableSet.of(),
                ImmutableSet.of(),
                SoundEvents.VILLAGER_WORK_LIBRARIAN,
                Int2ObjectMap.ofEntries(
                        Int2ObjectMap.entry(1, BEEKEEPER_LEVEL_1),
                        Int2ObjectMap.entry(2, BEEKEEPER_LEVEL_2),
                        Int2ObjectMap.entry(3, BEEKEEPER_LEVEL_3),
                        Int2ObjectMap.entry(4, BEEKEEPER_LEVEL_4))));
    }

    private static ResourceKey<TradeSet> tradeSet(String path) {
        return ResourceKey.create(Registries.TRADE_SET, ReForestry.id(path));
    }
}
