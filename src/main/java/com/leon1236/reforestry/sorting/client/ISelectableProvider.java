package com.leon1236.reforestry.sorting.client;

import java.util.Collection;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

public interface ISelectableProvider<S> {
	Collection<S> getEntries();

	void onSelect(@Nullable S selectable);

	void draw(S selectable, GuiGraphicsExtractor graphics, int y, int x);

	Component getName(S selectable);
}
