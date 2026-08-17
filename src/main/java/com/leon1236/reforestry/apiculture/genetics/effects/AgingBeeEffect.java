package com.leon1236.reforestry.apiculture.genetics.effects;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.items.ItemBeeGE;
import com.leon1236.reforestry.core.genetics.ForestryAlleles;

public class AgingBeeEffect extends NonStackingBeeEffect {
	protected final boolean aging;
	private final float strength;

	public AgingBeeEffect(Identifier id, boolean dominant, boolean aging) {
		this(id, dominant, aging, 1.0f);
	}

	public AgingBeeEffect(Identifier id, boolean dominant, boolean aging, float strength) {
		super(id, dominant);
		this.aging = aging;
		this.strength = strength;
	}

	@Override
	protected void doEffectForHive(Level level, IBeeHousing housing) {
		if (housing.getErrorLogic().hasErrors()) {
			return;
		}
		ItemStack queenStack = housing.beeInventory().getQueen();
		if (!(queenStack.getItem() instanceof ItemBeeGE beeItem) || !"queen".equals(beeItem.lifeStage())) {
			return;
		}
		IGenome genome = queenStack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (genome == null) {
			return;
		}

		int maxHealth = genome.getActiveAllele(BeeChromosomes.LIFESPAN).value();
		if (maxHealth <= 1) {
			return;
		}

		RandomSource rand = level.getRandom();
		int normalLifespan = ForestryAlleles.LIFESPAN_NORMAL.value();
		int life = maxHealth / normalLifespan;
		if (rand.nextInt(normalLifespan) < maxHealth % normalLifespan) {
			life++;
		}
		int amount = Math.round(life * this.strength);
		int lifeUsed = queenStack.getOrDefault(ApicultureDataComponents.BEE_LIFE_USED.type(), 0);
		long remaining = (long) maxHealth - lifeUsed;
		if (this.aging) {
			remaining = Math.max(1, remaining - amount);
		} else {
			remaining = Math.min(maxHealth, Math.min(Integer.MAX_VALUE, remaining + (long) amount));
		}
		int newUsed = (int) Math.max(0, Math.min(Integer.MAX_VALUE, maxHealth - remaining));
		queenStack.set(ApicultureDataComponents.BEE_LIFE_USED.type(), newUsed);
		housing.beeInventory().setQueen(queenStack);
	}
}
