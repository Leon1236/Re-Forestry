package com.leon1236.reforestry.core.features;

import net.fabricmc.fabric.api.registry.FuelValueEvents;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.circuits.EnumCircuitBoardType;
import com.leon1236.reforestry.core.circuits.EnumElectronTube;
import com.leon1236.reforestry.core.circuits.ItemCircuitBoard;
import com.leon1236.reforestry.core.circuits.ItemSolderingIron;
import com.leon1236.reforestry.core.items.EnumCraftingMaterial;
import com.leon1236.reforestry.core.items.EnumFruit;
import com.leon1236.reforestry.core.items.ItemBeesWax;
import com.leon1236.reforestry.core.items.ItemFertilizer;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class CoreItems {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("core"));

    public static final FeatureItem<ItemBeesWax> BEESWAX = REGISTRY.item("beeswax", ItemBeesWax::new);

    public static final FeatureItem<Item> APATITE = REGISTRY.item("apatite");
    public static final FeatureItem<Item> RAW_TIN = REGISTRY.item("raw_tin");
    public static final FeatureItem<Item> AMBER = REGISTRY.item("amber");

    public static final FeatureItem<Item> INGOT_TIN = REGISTRY.item("ingot_tin");
    public static final FeatureItem<Item> INGOT_BRONZE = REGISTRY.item("ingot_bronze");

    public static final FeatureItem<Item> GEAR_BRONZE = REGISTRY.item("gear_bronze");
    public static final FeatureItem<Item> GEAR_COPPER = REGISTRY.item("gear_copper");
    public static final FeatureItem<Item> GEAR_TIN = REGISTRY.item("gear_tin");

    public static final FeatureItem<Item> STURDY_CASING = REGISTRY.item("sturdy_machine");
    public static final FeatureItem<Item> HARDENED_CASING = REGISTRY.item("hardened_machine");
    public static final FeatureItem<Item> IMPREGNATED_CASING = REGISTRY.item("impregnated_casing");
    public static final FeatureItem<Item> FLEXIBLE_CASING = REGISTRY.item("flexible_casing");

    public static final FeatureItem<Item> CARTON = REGISTRY.item("carton");
    public static final FeatureItem<Item> BROKEN_BRONZE_PICKAXE = REGISTRY.item("broken_bronze_pickaxe");
    public static final FeatureItem<Item> BROKEN_BRONZE_SHOVEL = REGISTRY.item("broken_bronze_shovel");
    public static final FeatureItem<Item> BROKEN_BRONZE_AXE = REGISTRY.item("broken_axe");
    public static final FeatureItem<Item> BROKEN_BRONZE_SWORD = REGISTRY.item("broken_sword");
    public static final FeatureItem<Item> BROKEN_BRONZE_HOE = REGISTRY.item("broken_hoe");

    public static final FeatureItem<Item> MOULDY_WHEAT = REGISTRY.item("mouldy_wheat");
    public static final FeatureItem<Item> DECAYING_WHEAT = REGISTRY.item("decaying_wheat");

    public static final FeatureItem<Item> IODINE_CHARGE = REGISTRY.item("iodine_capsule");
    public static final FeatureItem<Item> DISSIPATION_CHARGE = REGISTRY.item("dissipation_charge");

    public static final FeatureItem<Item> REFRACTORY_WAX = REGISTRY.item("refractory_wax");
    public static final FeatureItem<Item> ASH = REGISTRY.item("ash");

    public static final FeatureItem<Item> PEAT = REGISTRY.item("peat");
    public static final FeatureItem<Item> BITUMINOUS_PEAT = REGISTRY.item("bituminous_peat");

    public static final FeatureItem<ItemFertilizer> COMPOST = REGISTRY.item("fertilizer_bio", ItemFertilizer::new);
    public static final FeatureItem<ItemFertilizer> FERTILIZER_COMPOUND = REGISTRY.item("fertilizer_compound", ItemFertilizer::new);
    public static final FeatureItem<ItemFertilizer> MULCH = REGISTRY.item("mulch", ItemFertilizer::new);

    public static final FeatureItemGroup<Item, EnumCraftingMaterial> CRAFTING_MATERIALS =
            REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumCraftingMaterial.values())
                    .create();

    public static final FeatureItemGroup<Item, EnumFruit> FRUITS =
            REGISTRY.itemGroup((type, properties) -> new Item(properties.food(
                    new FoodProperties.Builder()
                            .nutrition(type.nutrition)
                            .saturationModifier(type.saturationModifier)
                            .build(),
                    Consumable.builder()
                            .animation(ItemUseAnimation.EAT)
                            .sound(SoundEvents.GENERIC_EAT)
                            .consumeSeconds(type.useTicks / 20f)
                            .build())), EnumFruit.values())
                    .identifier("fruit")
                    .create();

    public static final FeatureItemGroup<ItemCircuitBoard, EnumCircuitBoardType> CIRCUITBOARDS =
            REGISTRY.itemGroup((type, properties) -> new ItemCircuitBoard(type, properties), EnumCircuitBoardType.values())
                    .identifier("circuit_board")
                    .create();

    public static final FeatureItemGroup<Item, EnumElectronTube> ELECTRON_TUBES =
            REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumElectronTube.values())
                    .identifier("electron_tube")
                    .create();

    public static final FeatureItem<ItemSolderingIron> SOLDERING_IRON =
            REGISTRY.item("soldering_iron", properties -> new ItemSolderingIron(properties.durability(59)));

    public static void init() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(PEAT.item(), 2000);
            builder.add(BITUMINOUS_PEAT.item(), 4200);
        });
    }
}
