package com.leon1236.reforestry.api.book;

import java.util.List;

import net.minecraft.resources.Identifier;

import org.jetbrains.annotations.Nullable;

public interface IForesterBook {
	String landingTextKey();

	Identifier bookTexture();

	List<? extends IBookCategory> categories();

	@Nullable
	IBookCategory category(Identifier id);

	@Nullable
	IBookEntry entry(Identifier id);

}
