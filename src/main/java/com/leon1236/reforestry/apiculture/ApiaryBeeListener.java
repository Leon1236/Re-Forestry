package com.leon1236.reforestry.apiculture;

import com.leon1236.reforestry.api.apiculture.IBeeListener;
import com.leon1236.reforestry.apiculture.tiles.TileBeeHousing;

public final class ApiaryBeeListener implements IBeeListener {
    private final TileBeeHousing apiary;

    public ApiaryBeeListener(TileBeeHousing apiary) {
        this.apiary = apiary;
    }

    @Override
    public void wearOutEquipment(int amount) {
        apiary.wearOutFrames(amount);
    }
}
