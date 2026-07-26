package com.leon1236.reforestry.core.items.definitions;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public final class ToolMaterials {
	public static final TagKey<Item> REPAIR_BRONZE = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "ingots/bronze"));

	public static final ToolMaterial SURVIVALIST = new ToolMaterial(
			BlockTags.INCORRECT_FOR_IRON_TOOL,
			200,
			7.0F,
			2.5F,
			10,
			REPAIR_BRONZE
	);

	private ToolMaterials() {
	}
}
