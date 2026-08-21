package com.leon1236.reforestry.lepidopterology.genetics;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.lepidopterology.IButterflyCocoon;
import com.leon1236.reforestry.api.lepidopterology.IButterflyNursery;
import com.leon1236.reforestry.api.lepidopterology.IEntityButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterfly;
import com.leon1236.reforestry.api.lepidopterology.genetics.IButterflySpecies;
import com.leon1236.reforestry.core.genetics.IndividualLiving;

public class Butterfly extends IndividualLiving<IButterflySpecies, IButterfly, ButterflySpeciesType> implements IButterfly {
	public static final Codec<Butterfly> CODEC = RecordCodecBuilder.create(instance -> {
		Codec<IGenome> genomeCodec = ButterflyChromosomes.KARYOTYPE.genomeCodec();
		return instance.group(
				genomeCodec.fieldOf("genome").forGetter(Butterfly::getGenome),
				genomeCodec.optionalFieldOf("mate").forGetter(Butterfly::getMateOptional),
				Codec.BOOL.fieldOf("analyzed").forGetter(Butterfly::isAnalyzed),
				Codec.INT.fieldOf("health").forGetter(Butterfly::getHealth),
				Codec.INT.fieldOf("max_health").forGetter(Butterfly::getMaxHealth)
		).apply(instance, Butterfly::new);
	});

	public Butterfly(IGenome genome) {
		super(genome);
	}

	private Butterfly(IGenome genome, Optional<IGenome> mate, boolean analyzed, int health, int maxHealth) {
		super(genome, mate, analyzed, health, maxHealth);
	}

	@Override
	protected int getMaxHealthFromGenome(IGenome genome) {
		return genome.getActiveAllele(ButterflyChromosomes.LIFESPAN).value();
	}

	@Override
	public ButterflySpeciesType getType() {
		return ButterflySpeciesType.INSTANCE;
	}

	@Override
	public IButterflySpecies getSpecies() {
		return species;
	}

	@Override
	public IButterflySpecies getInactiveSpecies() {
		return inactiveSpecies;
	}

	@Override
	public IButterfly copy() {
		return super.copy();
	}

	@Override
	public boolean canSpawn(Level level, double x, double y, double z) {
		return false;
	}

	@Override
	public boolean canTakeFlight(Level level, double x, double y, double z) {
		return false;
	}

	@Override
	public boolean isAcceptedEnvironment(Level world, BlockPos pos) {
		return false;
	}

	@Override
	@Nullable
	public IButterfly spawnCaterpillar(IButterflyNursery nursery) {
		return null;
	}

	@Override
	public List<ItemStack> getLootDrop(IEntityButterfly entity, boolean playerKill, int lootLevel) {
		return List.of();
	}

	@Override
	public List<ItemStack> getCaterpillarDrop(IButterflyNursery nursery, boolean playerKill, int lootLevel) {
		return List.of();
	}

	@Override
	public List<ItemStack> getCocoonDrop(boolean includeButterfly, IButterflyCocoon cocoon) {
		return List.of();
	}

	@Override
	public Component getDisplayName() {
		return getSpecies().getDisplayName();
	}
}
