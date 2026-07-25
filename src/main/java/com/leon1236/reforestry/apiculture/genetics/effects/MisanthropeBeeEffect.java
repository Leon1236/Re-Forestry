package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.core.damage.CoreDamageTypes;

public class MisanthropeBeeEffect extends ThrottledBeeEffect {
    public MisanthropeBeeEffect() {
        super(ForestryBeeEffects.MISANTHROPE, true, 20, false, false);
    }

    @Override
    public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
        if (!(housing.level() instanceof ServerLevel serverLevel)) {
            return storedData;
        }
        List<Player> players = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Player.class);
        for (Player player : players) {
            int damage = 4;
            int count = BeeManager.armorApiaristHelper.wearsItems(player, this, true);
            damage -= count;
            if (damage <= 0) {
                continue;
            }
            player.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.MISANTHROPE), damage);
        }
        return storedData;
    }
}
