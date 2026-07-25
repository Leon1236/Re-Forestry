package com.leon1236.reforestry.modules.features;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.resources.Identifier;

public final class ModFeatureRegistry {
    private static final Map<Identifier, IFeatureRegistry> REGISTRIES = new ConcurrentHashMap<>();

    private ModFeatureRegistry() {
    }

    public static IFeatureRegistry get(Identifier moduleId) {
        return REGISTRIES.computeIfAbsent(moduleId, id -> () -> id);
    }
}
