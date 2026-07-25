package com.leon1236.reforestry.api.modules;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.resources.Identifier;

import com.leon1236.reforestry.api.client.IClientModuleHandler;

public interface IForestryModule {
    Identifier getId();

    default List<Identifier> getModuleDependencies() {
        return List.of();
    }

    default List<String> getModDependencies() {
        return List.of();
    }

    default boolean isCore() {
        return false;
    }

    default void init() {
    }

    default void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
    }
}
