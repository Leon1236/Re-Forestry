package com.leon1236.reforestry.api.apiculture;

import com.leon1236.reforestry.api.genetics.IGenome;

public interface IBeeListener {
    default void wearOutEquipment(int amount) {
    }

    default void onQueenDeath() {
    }

    default boolean onPollenRetrieved(IGenome pollen) {
        return false;
    }
}
