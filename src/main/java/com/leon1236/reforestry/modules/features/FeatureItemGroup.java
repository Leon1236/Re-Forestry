package com.leon1236.reforestry.modules.features;

import java.util.function.BiFunction;

import com.google.common.collect.ImmutableMap;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import com.leon1236.reforestry.api.core.IItemSubtype;

public class FeatureItemGroup<I extends Item, S extends IItemSubtype> extends FeatureGroup<S, FeatureItem<I>> {

    private FeatureItemGroup(ImmutableMap<S, FeatureItem<I>> featureByType) {
        super(featureByType);
    }

    public I item(S subtype) {
        return get(subtype).item();
    }

    public static class Builder<I extends Item, S extends IItemSubtype> extends FeatureGroup.Builder<S, Builder<I, S>> {
        private final BiFunction<S, Item.Properties, I> constructor;

        public Builder(Identifier moduleId, BiFunction<S, Item.Properties, I> constructor) {
            super(moduleId);
            this.constructor = constructor;
        }

        public FeatureItemGroup<I, S> create() {
            ImmutableMap.Builder<S, FeatureItem<I>> map = ImmutableMap.builder();
            for (S subtype : subtypes) {
                Identifier registryId = getRegistryId(subtype);
                map.put(subtype, new FeatureItem<>(moduleId, getRegistryName(subtype), registryId,
                        () -> constructor.apply(subtype, FeatureItem.seededProperties(registryId))));
            }
            return new FeatureItemGroup<>(map.build());
        }
    }
}
