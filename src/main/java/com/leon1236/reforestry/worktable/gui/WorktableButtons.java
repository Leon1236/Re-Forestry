package com.leon1236.reforestry.worktable.gui;

public final class WorktableButtons {
	public static final int RECALL_BASE = 0;
	public static final int LOCK_BASE = 10;
	public static final int CLEAR = 19;
	public static final int PREV_CONFLICT = 20;
	public static final int NEXT_CONFLICT = 21;

	private WorktableButtons() {
	}

	public static int recall(int recipeIndex) {
		return RECALL_BASE + recipeIndex;
	}

	public static int lock(int recipeIndex) {
		return LOCK_BASE + recipeIndex;
	}
}
