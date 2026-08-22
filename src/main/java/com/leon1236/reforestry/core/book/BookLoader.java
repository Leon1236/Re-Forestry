package com.leon1236.reforestry.core.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.book.IBookLoader;
import com.leon1236.reforestry.api.book.IForesterBook;

public final class BookLoader implements IBookLoader {
	private static final BookLoader INSTANCE = new BookLoader();
	private static final String BOOK_ROOT = "patchouli_books/foresters_manual";
	private static final String CATEGORY_ROOT = BOOK_ROOT + "/en_us/categories";
	private static final String ENTRY_ROOT = BOOK_ROOT + "/en_us/entries";

	private ForesterBook book = ForesterBook.empty();

	private BookLoader() {
	}

	public static BookLoader get() {
		return INSTANCE;
	}

	@Override
	public IForesterBook book() {
		return book;
	}

	@Override
	public void reload() {
	}

	public void reload(ResourceManager manager) {
		String landingTextKey = "for.gui.book.patchouli.landing_text";
		Identifier bookTexture = ReForestry.id("textures/gui/almanac/foresters_manual.png");
		Optional<Resource> bookMeta = manager.getResource(ReForestry.id(BOOK_ROOT + "/book.json"));
		if (bookMeta.isPresent()) {
			JsonObject meta = readJson(bookMeta.get());
			if (meta.has("landing_text")) {
				landingTextKey = meta.get("landing_text").getAsString();
			}
			if (meta.has("book_texture")) {
				bookTexture = parseResourceId(meta.get("book_texture").getAsString());
			}
		}

		List<BookCategory> categories = loadCategories(manager);
		List<BookEntry> entries = loadEntries(manager);
		List<BookCategory> wired = BookCategory.attachEntries(categories, entries);
		this.book = new ForesterBook(landingTextKey, bookTexture, wired, ForesterBook.indexEntries(entries));
	}

	private static List<BookCategory> loadCategories(ResourceManager manager) {
		List<BookCategory> categories = new ArrayList<>();
		Map<Identifier, Resource> resources = manager.listResources(CATEGORY_ROOT, path -> path.getPath().endsWith(".json"));
		for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
			String path = entry.getKey().getPath();
			String fileName = path.substring(path.lastIndexOf('/') + 1, path.length() - 5);
			Identifier id = ReForestry.id(fileName);
			JsonObject json = readJson(entry.getValue());
			String nameKey = json.get("name").getAsString();
			String descriptionKey = json.has("description") ? json.get("description").getAsString() : nameKey;
			ItemStack icon = json.has("icon") ? stackFromId(parseItemId(json.get("icon").getAsString())) : ItemStack.EMPTY;
			int sortNum = json.has("sortnum") ? json.get("sortnum").getAsInt() : 0;
			Identifier parentId = json.has("parent") ? parseCategoryId(json.get("parent").getAsString()) : null;
			categories.add(new BookCategory(id, nameKey, descriptionKey, icon, sortNum, parentId, List.of()));
		}
		categories.sort(Comparator.comparingInt(BookCategory::sortNum).thenComparing(BookCategory::nameKey));
		return categories;
	}

	private static List<BookEntry> loadEntries(ResourceManager manager) {
		List<BookEntry> entries = new ArrayList<>();
		Map<Identifier, Resource> resources = manager.listResources(ENTRY_ROOT, path -> path.getPath().endsWith(".json"));
		for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
			String path = entry.getKey().getPath();
			String relative = path.substring((ENTRY_ROOT + "/").length(), path.length() - 5);
			Identifier id = ReForestry.id(relative.replace('\\', '/'));
			JsonObject json = readJson(entry.getValue());
			String nameKey = json.get("name").getAsString();
			ItemStack icon = json.has("icon") ? stackFromId(parseItemId(json.get("icon").getAsString())) : ItemStack.EMPTY;
			Identifier categoryId = parseCategoryId(json.get("category").getAsString());
			List<BookPage> pages = parsePages(json.getAsJsonArray("pages"));
			entries.add(new BookEntry(id, nameKey, icon, categoryId, pages));
		}
		entries.sort(Comparator.comparing(BookEntry::nameKey));
		return entries;
	}

	private static List<BookPage> parsePages(JsonArray array) {
		List<BookPage> pages = new ArrayList<>();
		for (JsonElement element : array) {
			pages.add(parsePage(element.getAsJsonObject()));
		}
		return pages;
	}

	private static BookPage parsePage(JsonObject json) {
		String rawType = json.has("type") ? json.get("type").getAsString() : "text";
		String type = normalizeType(rawType);
		String textKey = json.has("text") ? json.get("text").getAsString() : null;
		String titleKey = json.has("title") ? json.get("title").getAsString() : null;
		Identifier recipeId = json.has("recipe") ? parseRecipeId(json.get("recipe").getAsString()) : null;
		Identifier recipeId2 = json.has("recipe2") ? parseRecipeId(json.get("recipe2").getAsString()) : null;
		Identifier itemId = json.has("item") ? parseItemId(json.get("item").getAsString()) : null;
		Identifier itemId2 = json.has("item2") ? parseItemId(json.get("item2").getAsString()) : null;
		Identifier imageId = json.has("image") ? parseResourceId(json.get("image").getAsString()) : null;
		int textureWidth = json.has("texture_width") ? json.get("texture_width").getAsInt() : 256;
		int textureHeight = json.has("texture_height") ? json.get("texture_height").getAsInt() : 256;
		int imageWidth = json.has("width") ? json.get("width").getAsInt() : 0;
		int imageHeight = json.has("height") ? json.get("height").getAsInt() : 0;
		int anchor = json.has("anchor") ? json.get("anchor").getAsInt() : 0;
		return new BookPage(type, textKey, titleKey, recipeId, recipeId2, itemId, itemId2, imageId, textureWidth, textureHeight, imageWidth, imageHeight, anchor);
	}

	private static String normalizeType(String rawType) {
		if (rawType.startsWith("patchouli:")) {
			return rawType.substring("patchouli:".length());
		}
		if (rawType.startsWith("reforestry:")) {
			return rawType.substring("reforestry:".length());
		}
		return rawType;
	}

	private static JsonObject readJson(Resource resource) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.open(), StandardCharsets.UTF_8))) {
			return JsonParser.parseReader(reader).getAsJsonObject();
		} catch (Exception exception) {
			throw new IllegalStateException("Failed to read book json: " + resource.sourcePackId(), exception);
		}
	}

	private static Identifier parseCategoryId(String value) {
		if (value.contains(":")) {
			return Identifier.parse(value);
		}
		return ReForestry.id(value);
	}

	private static Identifier parseItemId(String value) {
		if (value.startsWith("#")) {
			return ReForestry.id(value.substring(1));
		}
		return Identifier.parse(value);
	}

	private static Identifier parseRecipeId(String value) {
		return Identifier.parse(value);
	}

	private static Identifier parseResourceId(String value) {
		if (value.contains(":")) {
			return Identifier.parse(value);
		}
		return ReForestry.id(value);
	}

	private static ItemStack stackFromId(Identifier id) {
		return BuiltInRegistries.ITEM.get(id)
				.map(holder -> new ItemStack(holder.value()))
				.orElse(ItemStack.EMPTY);
	}
}
