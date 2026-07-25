package com.leon1236.reforestry.api.modules;

import java.util.Collection;

import net.minecraft.resources.Identifier;

public interface IModuleManager {
    Collection<IForestryModule> getLoadedModules();

    boolean isModuleLoaded(Identifier id);
}
