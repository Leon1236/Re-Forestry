package com.leon1236.reforestry.gendustry.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;

public final class GendustryTags {
	public static final class Items {
		public static final TagKey<Item> UPGRADES = TagKey.create(Registries.ITEM, ReForestry.id("upgrades"));

		private Items() {
		}
	}

	private GendustryTags() {
	}
}
