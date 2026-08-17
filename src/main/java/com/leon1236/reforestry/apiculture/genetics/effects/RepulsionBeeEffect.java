package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.world.entity.monster.Monster;

import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.entities.AIAvoidPlayers;

public class RepulsionBeeEffect extends ThrottledBeeEffect {
	public RepulsionBeeEffect() {
		super(ForestryBeeEffects.REPULSION, false, 100, true, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		List<Monster> mobs = ThrottledBeeEffect.getEntitiesInRange(genome, housing, Monster.class);
		for (Monster mob : mobs) {
			if (!isMobAvoidingPlayers(mob)) {
				mob.getGoalSelector().addGoal(3, new AIAvoidPlayers(mob, 6.0f, 0.25f, 0.3f));
				mob.getGoalSelector().tick();
			}
		}
		return storedData;
	}

	private boolean isMobAvoidingPlayers(Monster mob) {
		return mob.getGoalSelector().getAvailableGoals().stream()
				.anyMatch(task -> task.isRunning() && task.getGoal() instanceof AIAvoidPlayers);
	}
}
