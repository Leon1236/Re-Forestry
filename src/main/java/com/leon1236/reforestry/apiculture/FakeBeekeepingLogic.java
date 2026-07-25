package com.leon1236.reforestry.apiculture;

import com.leon1236.reforestry.api.apiculture.IBeekeepingLogic;

public enum FakeBeekeepingLogic implements IBeekeepingLogic {
	INSTANCE;

	@Override
	public boolean canWork() {
		return false;
	}

	@Override
	public void doWork() {
	}

	@Override
	public int getWorkProgressPercent() {
		return 0;
	}
}
