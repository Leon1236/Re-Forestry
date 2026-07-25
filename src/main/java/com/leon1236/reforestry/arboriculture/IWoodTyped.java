package com.leon1236.reforestry.arboriculture;

import com.leon1236.reforestry.api.arboriculture.IWoodType;
import com.leon1236.reforestry.api.arboriculture.WoodBlockKind;

public interface IWoodTyped {
    WoodBlockKind getBlockKind();

    boolean isFireproof();

    IWoodType getWoodType();
}
