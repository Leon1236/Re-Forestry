package com.leon1236.reforestry.modules.features;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class FeatureRecipeType<R extends Recipe<?>> extends ModFeature {
    private final RecipeType<R> type;
    private final RecipeSerializer<? extends R> serializer;

    public FeatureRecipeType(Identifier moduleId, String name, Identifier registryId,
                              Supplier<RecipeSerializer<? extends R>> serializer) {
        super(moduleId, name);
        this.type = Registry.register(BuiltInRegistries.RECIPE_TYPE, registryId, new RecipeType<R>() {
            @Override
            public String toString() {
                return registryId.toString();
            }
        });
        this.serializer = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, registryId, serializer.get());
    }

    public RecipeType<R> type() {
        return type;
    }

    public RecipeSerializer<? extends R> serializer() {
        return serializer;
    }
}
