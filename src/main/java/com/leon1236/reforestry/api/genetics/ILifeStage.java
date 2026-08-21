package com.leon1236.reforestry.api.genetics;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;

public interface ILifeStage extends StringRepresentable {
	@Override
	String getSerializedName();

	Item getItemForm();
}
