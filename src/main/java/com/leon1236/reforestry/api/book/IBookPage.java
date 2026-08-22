package com.leon1236.reforestry.api.book;

import net.minecraft.resources.Identifier;

public interface IBookPage {
	String type();

	String textKey();

	String titleKey();

	Identifier recipeId();

	Identifier recipeId2();

	Identifier itemId();

	Identifier itemId2();

	Identifier imageId();

	int textureWidth();

	int textureHeight();

	int imageWidth();

	int imageHeight();

	int anchor();

}
