package com.leon1236.reforestry.sorting.client;

public interface IFilterScrollable {
	void onScroll(int value);

	boolean isFocused(int mouseX, int mouseY);
}
