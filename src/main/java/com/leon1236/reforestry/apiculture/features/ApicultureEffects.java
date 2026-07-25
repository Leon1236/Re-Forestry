package com.leon1236.reforestry.apiculture.features;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import com.leon1236.reforestry.ReForestry;

public final class ApicultureEffects {
    public static final Holder<MobEffect> HAKUNA_MATATA = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ReForestry.id("hakuna_matata"),
            new ForestryMobEffect(MobEffectCategory.BENEFICIAL, 0x069af3)
                    .addAttributeModifier(Attributes.FOLLOW_RANGE, ReForestry.id("effect.hakuna_matata"), 0,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> MATATA = Registry.registerForHolder(
            BuiltInRegistries.MOB_EFFECT,
            ReForestry.id("matata"),
            new ForestryMobEffect(MobEffectCategory.NEUTRAL, 0x380835));

    private ApicultureEffects() {
    }

    public static void init() {
    }

    public static class ForestryMobEffect extends MobEffect {
        protected ForestryMobEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            return false;
        }
    }
}
