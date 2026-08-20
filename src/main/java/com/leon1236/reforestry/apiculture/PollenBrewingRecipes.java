package com.leon1236.reforestry.apiculture;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

import com.leon1236.reforestry.apiculture.features.ApicultureItems;
import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;

public final class PollenBrewingRecipes {
    private PollenBrewingRecipes() {
    }

    public static void init() {
        FabricPotionBrewingBuilder.BUILD.register(PollenBrewingRecipes::registerRecipes);
    }

    private static void registerRecipes(PotionBrewing.Builder builder) {
        FabricPotionBrewingBuilder fabric = (FabricPotionBrewingBuilder) builder;
        fabric.registerPotionRecipe(
                Potions.AWKWARD,
                Ingredient.of(ApicultureItems.POLLEN_CLUSTER.item(EnumPollenCluster.NORMAL)),
                Potions.HEALING);
        fabric.registerPotionRecipe(
                Potions.AWKWARD,
                Ingredient.of(ApicultureItems.POLLEN_CLUSTER.item(EnumPollenCluster.CRYSTALLINE)),
                Potions.REGENERATION);
    }
}
