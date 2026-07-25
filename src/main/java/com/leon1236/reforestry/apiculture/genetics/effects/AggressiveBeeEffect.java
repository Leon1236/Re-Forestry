package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;

public class AggressiveBeeEffect extends ThrottledBeeEffect {
    public AggressiveBeeEffect() {
        super(ForestryBeeEffects.AGGRESSIVE, true, 40, false, false);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        if (!(housing.level() instanceof ServerLevel serverLevel)) {
            return storedData;
        }

        List<LivingEntity> entities = ThrottledBeeEffect.getEntitiesInRange(genome, housing, LivingEntity.class);
        for (LivingEntity entity : entities) {
            int damage = 4;
            int count = BeeManager.armorApiaristHelper.wearsItems(entity, this, true);
            damage -= count;
            if (damage <= 0) {
                continue;
            }
            entity.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.AGGRESSIVE), damage);
        }

        return storedData;
    }
}
