package com.leon1236.reforestry.core.genetics;

import java.util.Optional;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividualLiving;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;

public abstract class IndividualLiving<S extends ISpecies<I>, I extends IIndividualLiving, T extends ISpeciesType<S, I>>
		extends Individual<S, I, T> implements IIndividualLiving {
	protected int health;
	protected int maxHealth;

	protected IndividualLiving(IGenome genome) {
		super(genome);
		int lifespan = getMaxHealthFromGenome(genome);
		this.health = lifespan;
		this.maxHealth = lifespan;
	}

	protected IndividualLiving(IGenome genome, Optional<IGenome> mate, boolean analyzed, int health, int maxHealth) {
		super(genome, mate, analyzed);
		this.health = health;
		this.maxHealth = maxHealth;
	}

	protected abstract int getMaxHealthFromGenome(IGenome genome);

	@Override
	public boolean isAlive() {
		return health > 0;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public final void setHealth(int health) {
		this.health = Mth.clamp(health, 0, getMaxHealth());
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void age(Level level, float ageStep) {
		if (ageStep < 0f) {
			setHealth(0);
			return;
		}
		if (ageStep == 0f) {
			return;
		}
		while (ageStep > 1.0f) {
			decreaseHealth();
			ageStep--;
		}
		if (level.getRandom().nextFloat() < ageStep) {
			decreaseHealth();
		}
	}

	@Override
	protected void copyPropertiesTo(I other) {
		super.copyPropertiesTo(other);
		if (other instanceof IndividualLiving<?, ?, ?> living) {
			living.health = health;
			living.maxHealth = maxHealth;
		}
	}

	private void decreaseHealth() {
		if (health > 0) {
			setHealth(health - 1);
		}
	}
}
