package com.leon1236.reforestry.api.apiculture.genetics;

import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.genetics.ILifeStage;

public enum BeeLifeStage implements ILifeStage {
	DRONE(ReForestry.id("bee_drone_ge")),
	PRINCESS(ReForestry.id("bee_princess_ge")),
	QUEEN(ReForestry.id("bee_queen_ge")),
	LARVAE(ReForestry.id("bee_larvae_ge"));

	private final String serializedName;
	private final Identifier itemId;

	BeeLifeStage(Identifier itemId) {
		this.serializedName = name().toLowerCase(Locale.ROOT);
		this.itemId = itemId;
	}

	public Identifier itemId() {
		return itemId;
	}

	@Nullable
	public static BeeLifeStage bySerializedName(String name) {
		for (BeeLifeStage stage : values()) {
			if (stage.serializedName.equals(name)) {
				return stage;
			}
		}
		return null;
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
