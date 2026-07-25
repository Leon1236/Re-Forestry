package com.leon1236.reforestry.modules.features;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class FeatureItem<I extends Item> extends ModFeature {
    public static Item.Properties seededProperties(Identifier registryId) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryId));
    }

    public static Item.Properties seededBlockItemProperties(Identifier registryId) {
        return seededProperties(registryId).useBlockDescriptionPrefix();
    }

    private final I item;

    public FeatureItem(Identifier moduleId, String name, Identifier registryId, Supplier<I> itemConstructor) {
        super(moduleId, name);
        this.item = Registry.register(BuiltInRegistries.ITEM, registryId, itemConstructor.get());
    }

    public I item() {
        return item;
    }
}
