package com.leon1236.reforestry.core.genetics.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.features.CoreItems;

public final class ResearchNoteLoot {
	private static final float STRUCTURE_CHEST_CHANCE = 0.2f;
	private static final float STRONGHOLD_LIBRARY_CHANCE = 0.45f;

	private ResearchNoteLoot() {
	}

	public static void init() {
		Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, ReForestry.id("fill_research_note"),
				FillResearchNoteFunction.MAP_CODEC);

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (!isStructureChest(key)) {
				return;
			}
			float chance = key.identifier().getPath().equals("chests/stronghold_library")
					? STRONGHOLD_LIBRARY_CHANCE
					: STRUCTURE_CHEST_CHANCE;
			tableBuilder.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.when(LootItemRandomChanceCondition.randomChance(chance))
					.add(LootItem.lootTableItem(CoreItems.RESEARCH_NOTE.item())
							.apply(FillResearchNoteFunction.fillResearchNote())));
		});
	}

	private static boolean isStructureChest(ResourceKey<LootTable> key) {
		Identifier id = key.identifier();
		if (!Identifier.DEFAULT_NAMESPACE.equals(id.getNamespace())) {
			return false;
		}
		String path = id.getPath();
		if (!path.startsWith("chests/")) {
			return false;
		}
		if (path.startsWith("chests/village/")) {
			return false;
		}
		if (path.endsWith("_dispenser")) {
			return false;
		}
		return !path.equals("chests/spawn_bonus_chest");
	}
}
