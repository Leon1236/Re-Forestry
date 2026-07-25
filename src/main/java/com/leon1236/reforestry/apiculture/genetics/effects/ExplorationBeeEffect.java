package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;

public class ExplorationBeeEffect extends ThrottledBeeEffect {
    public ExplorationBeeEffect() {
        super(ForestryBeeEffects.EXPLORATION, false, 80, true, false);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
        for (Player player : players) {
            player.giveExperiencePoints(2);
        }
        return storedData;
    }
}
