package com.leon1236.reforestry.extratrees.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.ReForestry;

public final class ExtraTreesTags {
	public static final class Items {
		public static final TagKey<Item> GRAIN_WHEAT = tag("grain_wheat");
		public static final TagKey<Item> GRAIN_BARLEY = tag("grain_barley");
		public static final TagKey<Item> GRAIN_RYE = tag("grain_rye");
		public static final TagKey<Item> GRAIN_CORN = tag("grain_corn");
		public static final TagKey<Item> GRAIN_ROASTED = tag("grain_roasted");
		public static final TagKey<Item> HOPS = tag("hops");

		private static TagKey<Item> tag(String path) {
			return TagKey.create(Registries.ITEM, ReForestry.id(path));
		}

		private Items() {
		}
	}

	private ExtraTreesTags() {
	}
}
