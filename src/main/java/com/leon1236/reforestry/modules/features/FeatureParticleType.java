package com.leon1236.reforestry.modules.features;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class FeatureParticleType<T extends ParticleOptions> extends ModFeature {
    private final ParticleType<T> type;

    public FeatureParticleType(Identifier moduleId, String name, Identifier registryId, ParticleType<T> type) {
        super(moduleId, name);
        this.type = Registry.register(BuiltInRegistries.PARTICLE_TYPE, registryId, type);
    }

    public ParticleType<T> type() {
        return type;
    }
}
