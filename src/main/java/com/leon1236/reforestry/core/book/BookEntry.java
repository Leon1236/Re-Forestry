package com.leon1236.reforestry.core.book;

import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.book.IBookEntry;
import com.leon1236.reforestry.api.book.IBookPage;

public record BookEntry(
		Identifier id,
		String nameKey,
		ItemStack icon,
		Identifier categoryId,
		List<BookPage> pageList
) implements IBookEntry {
	@Override
	public List<? extends IBookPage> pages() {
		return pageList;
	}
}
