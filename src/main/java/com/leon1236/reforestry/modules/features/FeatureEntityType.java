package com.leon1236.reforestry.modules.features;

import java.util.function.UnaryOperator;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class FeatureEntityType<T extends Entity> extends ModFeature {
    private final EntityType<T> entityType;

    public FeatureEntityType(Identifier moduleId, String name, Identifier registryId,
                             EntityType.EntityFactory<T> factory, MobCategory category,
                             UnaryOperator<EntityType.Builder<T>> configurator) {
        super(moduleId, name);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, registryId);
        EntityType.Builder<T> builder = configurator.apply(EntityType.Builder.of(factory, category));
        this.entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public EntityType<T> entityType() {
        return entityType;
    }
}
