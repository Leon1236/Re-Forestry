package com.leon1236.reforestry.extra_bees.circuit;

import java.util.List;
import java.util.Locale;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.apiculture.IBeeModifier;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.core.circuits.EnumElectronTube;

public enum AlvearySimulatorCircuitType implements IBeeModifier {
	LOW_VOLTAGE(EnumElectronTube.IRON, 10),
	HIGH_VOLTAGE(EnumElectronTube.DIAMOND, 20),
	PLANT(EnumElectronTube.APATITE, 10),
	DEATH(EnumElectronTube.OBSIDIAN, 10),
	LIFE(EnumElectronTube.LAPIS, 10),
	NETHER(EnumElectronTube.BLAZE, 15),
	MUTATION(EnumElectronTube.GOLD, 15),
	INHIBITOR(EnumElectronTube.TIN, 10),
	TERRITORY(EnumElectronTube.BRONZE, 10);

	private final EnumElectronTube tube;
	private final int power;
	private float production = 1.0f;
	private float productionMax = 1.0f;
	private float lifespan = 1.0f;
	private float lifespanMax = 1.0f;
	private float flowering = 1.0f;
	private float floweringMax = 1.0f;
	private float mutation = 1.0f;
	private float mutationMax = 1.0f;
	private float territory = 1.0f;
	private float territoryMax = 1.0f;
	private float geneticDecay = 1.5f;
	private float geneticDecayMax = 10.0f;
	private boolean hellish;

	static {
		LOW_VOLTAGE.production = 1.5f;
		LOW_VOLTAGE.productionMax = 5.0f;
		HIGH_VOLTAGE.production = 2.5f;
		HIGH_VOLTAGE.productionMax = 10.0f;
		PLANT.flowering = 1.5f;
		PLANT.floweringMax = 5.0f;
		DEATH.lifespan = 0.8f;
		DEATH.lifespanMax = 0.2f;
		LIFE.lifespan = 1.5f;
		LIFE.lifespanMax = 5.0f;
		NETHER.hellish = true;
		MUTATION.mutation = 1.5f;
		MUTATION.mutationMax = 5.0f;
		INHIBITOR.territory = 0.4f;
		INHIBITOR.territoryMax = 0.1f;
		INHIBITOR.production = 0.9f;
		INHIBITOR.productionMax = 0.5f;
		TERRITORY.territory = 1.5f;
		TERRITORY.territoryMax = 5.0f;
	}

	AlvearySimulatorCircuitType(EnumElectronTube tube, int power) {
		this.tube = tube;
		this.power = power;
	}

	private static float apply(float mult, float limit, float current) {
		if (mult == 1.0f && limit == 1.0f) {
			return 1.0f;
		}
		if (limit >= 1.0f) {
			if (limit <= current) {
				return 1.0f;
			}
			return Math.min(limit / current, mult);
		}
		if (limit >= current) {
			return 1.0f;
		}
		return Math.max(limit / current, mult);
	}

	public EnumElectronTube getTube() {
		return tube;
	}

	public int getPower() {
		return power;
	}

	public String getCircuitUid() {
		return "stimulator." + name().toLowerCase(Locale.ENGLISH);
	}

	@Override
	public Vec3i modifyTerritory(IGenome genome, Vec3i currentModifier) {
		float current = Math.max(currentModifier.getX(), Math.max(currentModifier.getY(), currentModifier.getZ()));
		float factor = apply(territory, territoryMax, current <= 0f ? 1.0f : current);
		if (factor == 1.0f) {
			return currentModifier;
		}
		return new Vec3i(
				Math.max(1, Math.round(currentModifier.getX() * factor)),
				Math.max(1, Math.round(currentModifier.getY() * factor)),
				Math.max(1, Math.round(currentModifier.getZ() * factor)));
	}

	@Override
	public float modifyMutationChance(IGenome genome, IGenome mate, IMutation mutationType, float currentChance) {
		if (mutation == 1.0f) {
			return currentChance;
		}
		return currentChance * apply(mutation, mutationMax, currentChance);
	}

	@Override
	public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
		if (lifespan == 1.0f) {
			return currentAging;
		}
		float currentLifespan = currentAging == 0f ? Float.MAX_VALUE : 1.0f / currentAging;
		float lifespanFactor = apply(lifespan, lifespanMax, currentLifespan);
		if (lifespanFactor == 0f) {
			return currentAging * 10000f;
		}
		return currentAging / lifespanFactor;
	}

	@Override
	public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
		if (production == 1.0f) {
			return currentSpeed;
		}
		return currentSpeed * apply(production, productionMax, currentSpeed);
	}

	@Override
	public float modifyPollination(IGenome genome, float currentPollination) {
		if (flowering == 1.0f) {
			return currentPollination;
		}
		return currentPollination * apply(flowering, floweringMax, currentPollination);
	}

	@Override
	public float modifyGeneticDecay(IGenome genome, float currentDecay) {
		return currentDecay * apply(geneticDecay, geneticDecayMax, currentDecay);
	}

	@Override
	public boolean isHellish() {
		return hellish;
	}

	public void addModifierTooltip(List<Component> tooltip) {
		if (production != 1.0f) {
			tooltip.add(Component.translatable("item.reforestry.bee.modifier.production", production));
		}
		if (lifespan != 1.0f) {
			tooltip.add(Component.translatable("item.reforestry.bee.modifier.lifespan", lifespan));
		}
		if (flowering != 1.0f) {
			tooltip.add(Component.translatable("item.reforestry.bee.modifier.flowering", flowering));
		}
		if (mutation != 1.0f) {
			tooltip.add(Component.translatable("item.reforestry.bee.modifier.mutation", mutation));
		}
		if (territory != 1.0f) {
			tooltip.add(Component.translatable("item.reforestry.bee.modifier.territory", territory));
		}
		if (hellish) {
			tooltip.add(Component.translatable("for.gui.hellish"));
		}
		tooltip.add(Component.translatable("item.reforestry.bee.modifier.genetic.decay", geneticDecay));
	}
}
