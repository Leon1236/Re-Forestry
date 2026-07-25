package com.leon1236.reforestry.core.genetics;

import com.leon1236.reforestry.api.genetics.IEffectData;

public class EffectData implements IEffectData {
    private final int[] intData;
    private final boolean[] boolData;

    public EffectData(int intSize, int boolSize) {
        this.intData = new int[intSize];
        this.boolData = new boolean[boolSize];
    }

    @Override
    public void setInteger(int index, int val) {
        this.intData[index] = val;
    }

    @Override
    public void setBoolean(int index, boolean val) {
        this.boolData[index] = val;
    }

    @Override
    public int getInteger(int index) {
        return this.intData[index];
    }

    @Override
    public boolean getBoolean(int index) {
        return this.boolData[index];
    }

    public int getIntSize() {
        return this.intData.length;
    }
}
