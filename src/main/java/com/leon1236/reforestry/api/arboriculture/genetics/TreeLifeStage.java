package com.leon1236.reforestry.api.arboriculture.genetics;

import java.util.Locale;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ILifeStage;

public enum TreeLifeStage implements ILifeStage {
	SAPLING(ReForestry.id("sapling"), "sapling"),
	POLLEN(ReForestry.id("pollen_fertile"), "pollen");

	private final String serializedName;
	private final Identifier itemId;

	TreeLifeStage(Identifier itemId, String serializedName) {
		this.serializedName = serializedName;
		this.itemId = itemId;
	}

	public Identifier itemId() {
		return itemId;
	}

	public static TreeLifeStage bySerializedName(String name) {
		for (TreeLifeStage stage : values()) {
			if (stage.serializedName.equals(name)) {
				return stage;
			}
		}
		return SAPLING;
	}

	@Override
	public String getSerializedName() {
		return serializedName;
	}

	@Override
	public Item getItemForm() {
		return BuiltInRegistries.ITEM.get(itemId).map(Holder.Reference::value).orElse(Items.AIR);
	}
}
