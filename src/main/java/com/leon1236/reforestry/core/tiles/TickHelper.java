package com.leon1236.reforestry.core.tiles;

public final class TickHelper {
    private int tickCount;

    public TickHelper(int offset) {
        this.tickCount = offset % 2048;
    }

    public void onTick() {
        tickCount++;
    }

    public boolean updateOnInterval(int tickInterval) {
        return tickCount % tickInterval == 0;
    }
}
