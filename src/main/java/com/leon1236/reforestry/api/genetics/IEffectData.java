package com.leon1236.reforestry.api.genetics;

public interface IEffectData {
    void setInteger(int index, int val);

    void setBoolean(int index, boolean val);

    int getInteger(int index);

    boolean getBoolean(int index);
}
