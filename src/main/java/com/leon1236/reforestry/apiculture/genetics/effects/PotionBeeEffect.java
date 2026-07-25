package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.render.ParticleRender;

import net.minecraft.resources.Identifier;

public class PotionBeeEffect extends ThrottledBeeEffect {
    private final Holder<MobEffect> potion;
    private final int potionFXColor;
    private final int duration;
    private final float chance;

    public PotionBeeEffect(Identifier id, boolean dominant, Holder<MobEffect> potion, int duration) {
        this(id, dominant, potion, duration, 200, 1.0f);
    }

    public PotionBeeEffect(Identifier id, boolean dominant, Holder<MobEffect> potion, int duration, int throttle, float chance) {
        super(id, dominant, throttle, true, false);
        this.potion = potion;
        this.duration = duration;
        this.chance = chance;
        this.potionFXColor = PotionContents.getColorOptional(List.of(new MobEffectInstance(potion, 1, 0))).orElse(0xff00ff);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        RandomSource rand = housing.level().getRandom();
        List<LivingEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);

        for (LivingEntity entity : entities) {
            if (rand.nextFloat() >= this.chance) {
                continue;
            }

            if (!secondaryEntityCheck(entity)) {
                continue;
            }

            int dur = this.duration;
            if (this.potion.value().getCategory() == MobEffectCategory.HARMFUL) {
                int count = BeeManager.armorApiaristHelper.wearsItems(entity, this, true);
                if (count >= 4) {
                    continue;
                } else if (count == 3) {
                    dur = this.duration / 4;
                } else if (count == 2) {
                    dur = this.duration / 2;
                } else if (count == 1) {
                    dur = this.duration * 3 / 4;
                }
            } else if (this.potion.value().getCategory() == MobEffectCategory.BENEFICIAL && entity instanceof Enemy) {
                continue;
            }

            entity.addEffect(new MobEffectInstance(this.potion, dur, 0, true, true));
        }

        return storedData;
    }

    public boolean secondaryEntityCheck(LivingEntity entity) {
        return true;
    }

    @Override
    public IEffectData doFX(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        Level level = housing.level();
        if (level.getRandom().nextBoolean()) {
            return super.doFX(genome, storedData, housing);
        }
        Vec3 beeFXCoordinates = housing.getBeeFXCoordinates();
        ParticleRender.addEntityPotionFX(level, beeFXCoordinates.x, beeFXCoordinates.y + 0.5, beeFXCoordinates.z, this.potionFXColor);
        return storedData;
    }
}
