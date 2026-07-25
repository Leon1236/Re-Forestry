package com.leon1236.reforestry.api.apiculture;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.climate.IBiomeProvider;
import com.leon1236.reforestry.api.climate.IClimateProvider;
import com.leon1236.reforestry.api.core.IErrorLogicSource;

public interface IBeeHousing extends IErrorLogicSource, IClimateProvider, IBiomeProvider {
    Level level();

    BlockPos position();

    IBeeHousingInventory beeInventory();

    Iterable<IBeeModifier> getBeeModifiers();

    Iterable<IBeeListener> getBeeListeners();

    IBeekeepingLogic getBeekeepingLogic();

    int getBlockLightValue();

    boolean canBlockSeeTheSky();

    boolean isRaining();

    @Nullable
    GameProfile getOwner();

    Vec3 getBeeFXCoordinates();
}
