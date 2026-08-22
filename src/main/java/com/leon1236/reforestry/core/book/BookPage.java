package com.leon1236.reforestry.core.book;

import net.minecraft.resources.Identifier;

import org.jetbrains.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.book.IBookPage;

public record BookPage(
		String type,
		@Nullable String textKey,
		@Nullable String titleKey,
		@Nullable Identifier recipeId,
		@Nullable Identifier recipeId2,
		@Nullable Identifier itemId,
		@Nullable Identifier itemId2,
		@Nullable Identifier imageId,
		int textureWidth,
		int textureHeight,
		int imageWidth,
		int imageHeight,
		int anchor
) implements IBookPage {
	public static final Identifier EMPTY_ID = ReForestry.id("_empty");

	@Override
	public String textKey() {
		return textKey == null ? "" : textKey;
	}

	@Override
	public String titleKey() {
		return titleKey == null ? "" : titleKey;
	}

	@Override
	public Identifier recipeId() {
		return recipeId == null ? EMPTY_ID : recipeId;
	}

	@Override
	public Identifier recipeId2() {
		return recipeId2 == null ? EMPTY_ID : recipeId2;
	}

	@Override
	public Identifier itemId() {
		return itemId == null ? EMPTY_ID : itemId;
	}

	@Override
	public Identifier itemId2() {
		return itemId2 == null ? EMPTY_ID : itemId2;
	}

	@Override
	public Identifier imageId() {
		return imageId == null ? EMPTY_ID : imageId;
	}
}
