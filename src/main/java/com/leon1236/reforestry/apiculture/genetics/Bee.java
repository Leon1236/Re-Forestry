package com.leon1236.reforestry.apiculture.genetics;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividualLiving;
import com.leon1236.reforestry.apiculture.BeeStackHelper;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.core.genetics.IndividualLiving;

public class Bee extends IndividualLiving<IBeeSpecies, Bee, BeeSpeciesType> implements IIndividualLiving {
	public static final Codec<Bee> CODEC = RecordCodecBuilder.create(instance -> {
		Codec<IGenome> genomeCodec = BeeChromosomes.KARYOTYPE.genomeCodec();
		return instance.group(
				genomeCodec.fieldOf("genome").forGetter(Bee::getGenome),
				genomeCodec.optionalFieldOf("mate").forGetter(Bee::getMateOptional),
				Codec.BOOL.fieldOf("analyzed").forGetter(Bee::isAnalyzed),
				Codec.INT.fieldOf("health").forGetter(Bee::getHealth),
				Codec.INT.fieldOf("max_health").forGetter(Bee::getMaxHealth),
				Codec.BOOL.fieldOf("pristine").forGetter(Bee::isPristine),
				Codec.INT.optionalFieldOf("generation", 0).forGetter(Bee::getGeneration)
		).apply(instance, Bee::new);
	});

	private boolean pristine = true;
	private int generation;

	public Bee(IGenome genome) {
		super(genome);
	}

	private Bee(IGenome genome, Optional<IGenome> mate, boolean analyzed, int health, int maxHealth, boolean pristine,
			int generation) {
		super(genome, mate, analyzed, health, maxHealth);
		this.pristine = pristine;
		this.generation = generation;
	}

	@Override
	protected int getMaxHealthFromGenome(IGenome genome) {
		return genome.getActiveAllele(BeeChromosomes.LIFESPAN).value();
	}

	public void setPristine(boolean pristine) {
		this.pristine = pristine;
	}

	public boolean isPristine() {
		return pristine;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	@Override
	protected void savePropertiesToStack(ItemStack stack) {
		super.savePropertiesToStack(stack);
		stack.set(ApicultureDataComponents.BEE_GENOME.type(), genome);
		if (mate != null) {
			stack.set(ApicultureDataComponents.BEE_MATE_GENOME.type(), mate);
		} else {
			stack.remove(ApicultureDataComponents.BEE_MATE_GENOME.type());
		}
		int lifeUsed = Math.max(0, maxHealth - health);
		if (lifeUsed == 0) {
			stack.remove(ApicultureDataComponents.BEE_LIFE_USED.type());
		} else {
			stack.set(ApicultureDataComponents.BEE_LIFE_USED.type(), lifeUsed);
		}
		BeeStackHelper.setPristine(stack, pristine);
		BeeStackHelper.setGeneration(stack, generation);
	}

	@Override
	public void loadPropertiesFromStack(ItemStack stack) {
		super.loadPropertiesFromStack(stack);
		setMate(stack.get(ApicultureDataComponents.BEE_MATE_GENOME.type()));
		int lifeUsed = stack.getOrDefault(ApicultureDataComponents.BEE_LIFE_USED.type(), 0);
		setHealth(maxHealth - lifeUsed);
		pristine = BeeStackHelper.isPristine(stack);
		generation = BeeStackHelper.getGeneration(stack);
	}

	@Override
	protected void copyPropertiesTo(Bee other) {
		super.copyPropertiesTo(other);
		other.pristine = pristine;
		other.generation = generation;
	}

	@Nullable
	public static Bee fromStack(ItemStack stack) {
		IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
		if (genome == null) {
			return null;
		}
		Bee bee = new Bee(genome);
		bee.loadPropertiesFromStack(stack);
		return bee;
	}
}
