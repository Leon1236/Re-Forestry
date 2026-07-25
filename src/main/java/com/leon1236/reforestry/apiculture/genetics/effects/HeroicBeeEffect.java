package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Monster;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;

public class HeroicBeeEffect extends ThrottledBeeEffect {
    public HeroicBeeEffect() {
        super(ForestryBeeEffects.HEROIC, false, 40, true, false);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        if (!(housing.level() instanceof ServerLevel serverLevel)) {
            return storedData;
        }
        List<Monster> mobs = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Monster.class);
        for (Monster mob : mobs) {
            mob.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.HEROIC), 2);
        }
        return storedData;
    }
}
