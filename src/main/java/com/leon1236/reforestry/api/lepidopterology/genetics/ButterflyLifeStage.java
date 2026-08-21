package com.leon1236.reforestry.api.lepidopterology.genetics;

import java.util.Locale;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ILifeStage;

public enum ButterflyLifeStage implements ILifeStage {
	BUTTERFLY(ReForestry.id("butterfly")),
	SERUM(ReForestry.id("butterfly_serum")),
	CATERPILLAR(ReForestry.id("caterpillar")),
	COCOON(ReForestry.id("cocoon"));

	private final String serializedName;
	private final Identifier itemId;

	ButterflyLifeStage(Identifier itemId) {
		this.serializedName = name().toLowerCase(Locale.ROOT);
		this.itemId = itemId;
	}

	public Identifier itemId() {
		return itemId;
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
