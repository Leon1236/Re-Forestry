package com.leon1236.reforestry.apiculture.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.mixin.StructureTemplatePoolAccessor;

public final class VillagerJigsaw {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY =
            ResourceKey.create(Registries.PROCESSOR_LIST, Identifier.withDefaultNamespace("empty"));

    private VillagerJigsaw() {
    }

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(VillagerJigsaw::onServerStarting);
    }

    private static void onServerStarting(MinecraftServer server) {
        HolderLookup.RegistryLookup<StructureTemplatePool> pools =
                server.registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        HolderLookup.RegistryLookup<StructureProcessorList> processors =
                server.registryAccess().lookupOrThrow(Registries.PROCESSOR_LIST);
        Holder<StructureProcessorList> empty = processors.getOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        addVillagerHouse(pools, empty, "plains", 15);
        addVillagerHouse(pools, empty, "snowy", 15);
        addVillagerHouse(pools, empty, "savanna", 15);
        addVillagerHouse(pools, empty, "desert", 15);
        addVillagerHouse(pools, empty, "taiga", 15);
    }

    private static void addVillagerHouse(
            HolderLookup.RegistryLookup<StructureTemplatePool> pools,
            Holder<StructureProcessorList> empty,
            String biome,
            int weight) {
        ApiaristPoolElement element = new ApiaristPoolElement(
                Either.left(ReForestry.id("village/apiarist_house_" + biome + "_1")),
                empty,
                StructureTemplatePool.Projection.RIGID,
                Optional.empty());
        addToJigsawPattern(pools, Identifier.withDefaultNamespace("village/" + biome + "/houses"), element, weight);
    }

    private static void addToJigsawPattern(
            HolderLookup.RegistryLookup<StructureTemplatePool> pools,
            Identifier poolId,
            StructurePoolElement newPiece,
            int weight) {
        Optional<Holder.Reference<StructureTemplatePool>> holder =
                pools.get(ResourceKey.create(Registries.TEMPLATE_POOL, poolId));
        if (holder.isEmpty()) {
            return;
        }
        StructureTemplatePoolAccessor accessor = (StructureTemplatePoolAccessor) (Object) holder.get().value();
        ObjectArrayList<StructurePoolElement> templates = accessor.reforestry$getTemplates();
        for (int i = 0; i < weight; ++i) {
            templates.add(newPiece);
        }
        List<Pair<StructurePoolElement, Integer>> rawTemplates =
                new ArrayList<>(accessor.reforestry$getRawTemplates());
        rawTemplates.add(Pair.of(newPiece, weight));
        accessor.reforestry$setRawTemplates(rawTemplates);
    }
}
