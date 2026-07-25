package com.leon1236.reforestry.modules.features;

import net.minecraft.resources.Identifier;

public abstract class ModFeature implements IModFeature {
    private final Identifier moduleId;
    private final String name;

    protected ModFeature(Identifier moduleId, String name) {
        this.moduleId = moduleId;
        this.name = name;
    }

    @Override
    public Identifier getModuleId() {
        return moduleId;
    }

    @Override
    public String getName() {
        return name;
    }
}
