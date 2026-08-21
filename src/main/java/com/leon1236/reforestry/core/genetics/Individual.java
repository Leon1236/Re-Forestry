package com.leon1236.reforestry.core.genetics;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IIndividual;
import com.leon1236.reforestry.api.genetics.ILifeStage;
import com.leon1236.reforestry.api.genetics.ISpecies;
import com.leon1236.reforestry.api.genetics.ISpeciesType;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAllele;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.core.features.CoreDataComponents;

public abstract class Individual<S extends ISpecies<I>, I extends IIndividual, T extends ISpeciesType<S, I>>
		implements IIndividual {
	protected final S species;
	protected final S inactiveSpecies;
	protected final IGenome genome;

	@Nullable
	protected IGenome mate;
	protected boolean analyzed;

	protected Individual(IGenome genome) {
		this.species = speciesFrom(genome, true);
		this.inactiveSpecies = speciesFrom(genome, false);
		this.genome = genome;
	}

	protected Individual(IGenome genome, Optional<IGenome> mate, boolean analyzed) {
		this(genome);
		this.mate = mate.orElse(null);
		this.analyzed = analyzed;
	}

	@SuppressWarnings("unchecked")
	private S speciesFrom(IGenome genome, boolean active) {
		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		IRegistryAllele<?> allele = active
				? (IRegistryAllele<?>) genome.getActiveAllele((IChromosome) speciesChromosome)
				: (IRegistryAllele<?>) genome.getInactiveAllele((IChromosome) speciesChromosome);
		return (S) allele.value();
	}

	@Override
	public void setMate(@Nullable IGenome mate) {
		if (mate == null || genome.karyotype() == mate.karyotype()) {
			this.mate = mate;
		}
	}

	@Override
	@Nullable
	public IGenome getMate() {
		return mate;
	}

	@Override
	public Optional<IGenome> getMateOptional() {
		return Optional.ofNullable(mate);
	}

	@Override
	public IGenome getGenome() {
		return genome;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getType() {
		return (T) species.getType();
	}

	@Override
	public S getSpecies() {
		return species;
	}

	@Override
	public S getInactiveSpecies() {
		return inactiveSpecies;
	}

	@Override
	public boolean isAnalyzed() {
		return analyzed;
	}

	@Override
	public boolean analyze() {
		if (analyzed) {
			return false;
		}
		analyzed = true;
		return true;
	}

	@Override
	public I copy() {
		return copyWithGenome(genome);
	}

	@Override
	public I copyWithGenome(IGenome newGenome) {
		I individual = species.createIndividual(newGenome);
		copyPropertiesTo(individual);
		return individual;
	}

	protected void copyPropertiesTo(I other) {
	}

	@Override
	public void saveToStack(ItemStack stack) {
		if (analyzed) {
			stack.set(CoreDataComponents.ANALYZED.type(), true);
		} else {
			stack.remove(CoreDataComponents.ANALYZED.type());
		}
		savePropertiesToStack(stack);
	}

	protected void savePropertiesToStack(ItemStack stack) {
	}

	public void loadPropertiesFromStack(ItemStack stack) {
		analyzed = Boolean.TRUE.equals(stack.get(CoreDataComponents.ANALYZED.type()));
	}

	@Override
	public ItemStack createStack(ILifeStage stage) {
		ItemStack stack = new ItemStack(stage.getItemForm());
		saveToStack(stack);
		return stack;
	}
}
