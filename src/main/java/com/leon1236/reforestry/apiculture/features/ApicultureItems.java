package com.leon1236.reforestry.apiculture.features;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.apiculture.items.EnumHoneyComb;
import com.leon1236.reforestry.apiculture.items.EnumPollenCluster;
import com.leon1236.reforestry.apiculture.items.EnumPropolis;
import com.leon1236.reforestry.apiculture.items.ItemArmorApiarist;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.apiculture.items.ItemCreativeHiveFrame;
import com.leon1236.reforestry.apiculture.items.ItemHiveFrame;
import com.leon1236.reforestry.apiculture.items.ItemScoop;
import com.leon1236.reforestry.apiculture.items.ItemSmoker;
import com.leon1236.reforestry.modules.features.FeatureItem;
import com.leon1236.reforestry.modules.features.FeatureItemGroup;
import com.leon1236.reforestry.modules.features.IFeatureRegistry;
import com.leon1236.reforestry.modules.features.ModFeatureRegistry;

public class ApicultureItems {
    private static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(ReForestry.id("apiculture"));

    public static final FeatureItem<Item> HONEY_DROP = REGISTRY.item("honey_drop");
    public static final FeatureItem<Item> HONEYDEW = REGISTRY.item("honeydew");
    public static final FeatureItem<Item> EXPERIENCE_DROP = REGISTRY.item("experience_drop");
    public static final FeatureItem<Item> ROYAL_JELLY = REGISTRY.item("royal_jelly");
    public static final FeatureItem<Item> AMBER_DRONE = REGISTRY.item("amber_drone");

    public static final FeatureItemGroup<Item, EnumPollenCluster> POLLEN_CLUSTER =
            REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumPollenCluster.values())
                    .identifier("pollen_cluster")
                    .create();

    public static final FeatureItemGroup<Item, EnumHoneyComb> BEE_COMBS =
            REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumHoneyComb.VALUES)
                    .identifier("bee_comb")
                    .create();

    public static final FeatureItemGroup<Item, EnumPropolis> PROPOLIS =
            REGISTRY.itemGroup((type, properties) -> new Item(properties), EnumPropolis.values())
                    .identifier("propolis")
                    .create();

    public static final FeatureItem<Item> HONEYED_SLICE = REGISTRY.item("honeyed_slice",
            properties -> new Item(properties.food(
                    new FoodProperties.Builder().nutrition(8).saturationModifier(0.6f).build())));

    public static final FeatureItem<Item> HONEY_POT = REGISTRY.item("honey_pot",
            properties -> new Item(properties.food(
                    new FoodProperties.Builder().nutrition(2).saturationModifier(0.2f).build(),
                    Consumable.builder()
                            .animation(ItemUseAnimation.DRINK)
                            .sound(SoundEvents.GENERIC_DRINK)
                            .build())));

    public static final FeatureItem<Item> AMBROSIA = REGISTRY.item("ambrosia",
            properties -> new Item(properties
                    .food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.6f).alwaysEdible().build(),
                            Consumable.builder()
                                    .animation(ItemUseAnimation.DRINK)
                                    .sound(SoundEvents.GENERIC_DRINK)
                                    .onConsume(new ApplyStatusEffectsConsumeEffect(
                                            new MobEffectInstance(MobEffects.REGENERATION, 40, 0)))
                                    .build())
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final FeatureItem<ItemBeeGE> BEE_QUEEN = REGISTRY.item("bee_queen_ge",
            properties -> new ItemBeeGE(properties.stacksTo(1), "queen"));
    public static final FeatureItem<ItemBeeGE> BEE_DRONE = REGISTRY.item("bee_drone_ge",
            properties -> new ItemBeeGE(properties.stacksTo(1), "drone"));
    public static final FeatureItem<ItemBeeGE> BEE_PRINCESS = REGISTRY.item("bee_princess_ge",
            properties -> new ItemBeeGE(properties.stacksTo(1), "princess"));
    public static final FeatureItem<ItemBeeGE> BEE_LARVAE = REGISTRY.item("bee_larvae_ge",
            properties -> new ItemBeeGE(properties.stacksTo(1), "larvae"));

    public static final FeatureItem<ItemHiveFrame> FRAME_UNTREATED = REGISTRY.item("frame_untreated",
            properties -> new ItemHiveFrame(properties, 80, 0.9f));
    public static final FeatureItem<ItemHiveFrame> FRAME_IMPREGNATED = REGISTRY.item("frame_impregnated",
            properties -> new ItemHiveFrame(properties, 240, 0.4f));
    public static final FeatureItem<ItemHiveFrame> FRAME_PROVEN = REGISTRY.item("frame_proven",
            properties -> new ItemHiveFrame(properties, 720, 0.3f));
    public static final FeatureItem<ItemCreativeHiveFrame> FRAME_CREATIVE = REGISTRY.item("frame_creative",
            ItemCreativeHiveFrame::new);

    public static final FeatureItem<ItemArmorApiarist> APIARIST_HELMET = REGISTRY.item("apiarist_helmet",
            properties -> new ItemArmorApiarist(properties, ArmorType.HELMET));
    public static final FeatureItem<ItemArmorApiarist> APIARIST_CHEST = REGISTRY.item("apiarist_chest",
            properties -> new ItemArmorApiarist(properties, ArmorType.CHESTPLATE));
    public static final FeatureItem<ItemArmorApiarist> APIARIST_LEGS = REGISTRY.item("apiarist_legs",
            properties -> new ItemArmorApiarist(properties, ArmorType.LEGGINGS));
    public static final FeatureItem<ItemArmorApiarist> APIARIST_BOOTS = REGISTRY.item("apiarist_boots",
            properties -> new ItemArmorApiarist(properties, ArmorType.BOOTS));

    public static final FeatureItem<ItemScoop> SCOOP = REGISTRY.item("scoop",
            properties -> new ItemScoop(properties, 16));
    public static final FeatureItem<ItemScoop> SCOOP_PROVEN = REGISTRY.item("scoop_proven",
            properties -> new ItemScoop(properties, 160));
    public static final FeatureItem<ItemSmoker> SMOKER = REGISTRY.item("smoker", ItemSmoker::new);

    public static void init() {
    }
}
