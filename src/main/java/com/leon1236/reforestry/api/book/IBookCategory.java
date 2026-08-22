package com.leon1236.reforestry.api.book;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

public interface IBookCategory {
	Identifier id();

	String nameKey();

	String descriptionKey();

	ItemStack icon();

	int sortNum();

	@Nullable
	Identifier parentId();

	List<? extends IBookEntry> entries();

}
