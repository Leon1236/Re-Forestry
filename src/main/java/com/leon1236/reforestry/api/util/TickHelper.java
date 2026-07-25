package com.leon1236.reforestry.api.util;

public final class TickHelper {
    private int tickCount;

    public TickHelper(int offset) {
        this.tickCount = offset % 2048;
    }

    public void onTick() {
        this.tickCount++;
    }

    public boolean updateOnInterval(int tickInterval) {
        return this.tickCount % tickInterval == 0;
    }
}
