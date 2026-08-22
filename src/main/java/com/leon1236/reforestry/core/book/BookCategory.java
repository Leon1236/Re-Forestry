package com.leon1236.reforestry.core.book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.api.book.IBookCategory;
import com.leon1236.reforestry.api.book.IBookEntry;

public record BookCategory(
		Identifier id,
		String nameKey,
		String descriptionKey,
		ItemStack icon,
		int sortNum,
		@Nullable Identifier parentId,
		List<BookEntry> entryList
) implements IBookCategory {
	public BookCategory withEntries(List<BookEntry> sortedEntries) {
		return new BookCategory(id, nameKey, descriptionKey, icon, sortNum, parentId, sortedEntries);
	}

	@Override
	public List<? extends IBookEntry> entries() {
		return entryList;
	}

	public static List<BookCategory> attachEntries(List<BookCategory> categories, List<BookEntry> allEntries) {
		List<BookCategory> result = new ArrayList<>();
		for (BookCategory category : categories) {
			List<BookEntry> matched = allEntries.stream()
					.filter(entry -> entry.categoryId().equals(category.id()))
					.sorted(Comparator.comparing(entry -> entry.nameKey()))
					.toList();
			result.add(category.withEntries(matched));
		}
		result.sort(Comparator.comparingInt(BookCategory::sortNum).thenComparing(category -> category.nameKey()));
		return result;
	}
}
