package com.leon1236.reforestry.api.book;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public interface IBookEntry {
	Identifier id();

	String nameKey();

	ItemStack icon();

	Identifier categoryId();

	List<? extends IBookPage> pages();

}
