package com.leon1236.reforestry.core.book;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.resources.Identifier;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.book.IBookCategory;
import com.leon1236.reforestry.api.book.IBookEntry;
import com.leon1236.reforestry.api.book.IForesterBook;

public record ForesterBook(
		String landingTextKey,
		Identifier bookTexture,
		List<BookCategory> categoryList,
		Map<Identifier, BookEntry> entriesById
) implements IForesterBook {
	public static final Identifier BOOK_ID = ReForestry.id("foresters_manual");

	@Override
	public List<? extends IBookCategory> categories() {
		return categoryList;
	}

	@Override
	@Nullable
	public IBookCategory category(Identifier id) {
		for (BookCategory category : categoryList) {
			if (category.id().equals(id)) {
				return category;
			}
		}
		return null;
	}

	@Override
	@Nullable
	public IBookEntry entry(Identifier id) {
		return entriesById.get(id);
	}

	public static ForesterBook empty() {
		return new ForesterBook(
				"for.gui.book.patchouli.landing_text",
				ReForestry.id("textures/gui/almanac/foresters_manual.png"),
				List.of(),
				Map.of()
		);
	}

	public static Map<Identifier, BookEntry> indexEntries(List<BookEntry> entries) {
		return entries.stream().collect(Collectors.toUnmodifiableMap(BookEntry::id, Function.identity(), (first, second) -> first));
	}
}
