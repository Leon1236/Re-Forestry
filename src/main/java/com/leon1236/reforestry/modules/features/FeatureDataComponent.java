package com.leon1236.reforestry.modules.features;

import java.util.function.UnaryOperator;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class FeatureDataComponent<T> extends ModFeature {
    private final DataComponentType<T> type;

    public FeatureDataComponent(Identifier moduleId, String name, Identifier registryId,
                                 UnaryOperator<DataComponentType.Builder<T>> builder) {
        super(moduleId, name);
        DataComponentType.Builder<T> componentBuilder = builder.apply(DataComponentType.builder());
        this.type = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, registryId, componentBuilder.build());
    }

    public DataComponentType<T> type() {
        return type;
    }
}
