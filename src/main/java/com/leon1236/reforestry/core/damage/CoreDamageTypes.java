package com.leon1236.reforestry.core.damage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.ReForestry;

public final class CoreDamageTypes {
    public static final ResourceKey<DamageType> HIVE = ResourceKey.create(Registries.DAMAGE_TYPE, ReForestry.id("hive"));
    public static final ResourceKey<DamageType> AGGRESSIVE = ResourceKey.create(Registries.DAMAGE_TYPE, ReForestry.id("aggressive"));
    public static final ResourceKey<DamageType> HEROIC = ResourceKey.create(Registries.DAMAGE_TYPE, ReForestry.id("heroic"));
    public static final ResourceKey<DamageType> MISANTHROPE = ResourceKey.create(Registries.DAMAGE_TYPE, ReForestry.id("misanthrope"));
    public static final ResourceKey<DamageType> RADIOACTIVE = ResourceKey.create(Registries.DAMAGE_TYPE, ReForestry.id("radioactive"));

    private CoreDamageTypes() {
    }

    public static DamageSource source(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
