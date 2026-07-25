package com.leon1236.reforestry.api.plugin;

import net.minecraft.resources.Identifier;

public interface IForestryPlugin {
    Identifier id();

    default boolean shouldLoad() {
        return true;
    }

    default void registerApiculture(IApicultureRegistration registration) {
    }

    default void registerArboriculture(IArboricultureRegistration registration) {
    }
}
