package com.leon1236.reforestry.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import com.leon1236.reforestry.ReForestry;

public final class ForestryTags {
	private ForestryTags() {
	}

	public static final class Items {
		public static final TagKey<Item> MINER_ALLOW = itemTag("backpack/allow/miner");
		public static final TagKey<Item> MINER_REJECT = itemTag("backpack/reject/miner");

		public static final TagKey<Item> DIGGER_ALLOW = itemTag("backpack/allow/digger");
		public static final TagKey<Item> DIGGER_REJECT = itemTag("backpack/reject/digger");

		public static final TagKey<Item> FORESTER_ALLOW = itemTag("backpack/allow/forester");
		public static final TagKey<Item> FORESTER_REJECT = itemTag("backpack/reject/forester");

		public static final TagKey<Item> HUNTER_ALLOW = itemTag("backpack/allow/hunter");
		public static final TagKey<Item> HUNTER_REJECT = itemTag("backpack/reject/hunter");

		public static final TagKey<Item> ADVENTURER_ALLOW = itemTag("backpack/allow/adventurer");
		public static final TagKey<Item> ADVENTURER_REJECT = itemTag("backpack/reject/adventurer");

		public static final TagKey<Item> BUILDER_ALLOW = itemTag("backpack/allow/builder");
		public static final TagKey<Item> BUILDER_REJECT = itemTag("backpack/reject/builder");

		public static final TagKey<Item> BREWER_ALLOW = itemTag("backpack/allow/brewer");
		public static final TagKey<Item> BREWER_REJECT = itemTag("backpack/reject/brewer");

		public static final TagKey<Item> VILLAGE_COMBS = itemTag("village_combs");

		private Items() {
		}
	}

	public static final class Blocks {
		public static final TagKey<Block> VALID_FARM_BASE = blockTag("valid_farm_base");
		public static final TagKey<Block> TREE_SAPLINGS = blockTag("tree_saplings");

		private Blocks() {
		}
	}

	public static TagKey<Item> itemTag(String name) {
		return TagKey.create(Registries.ITEM, ReForestry.id(name));
	}

	public static TagKey<Block> blockTag(String name) {
		return TagKey.create(Registries.BLOCK, ReForestry.id(name));
	}
}
