package com.leon1236.reforestry.arboriculture.genetics;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.core.genetics.Individual;

public class Tree extends Individual<ITreeSpecies, Tree, TreeSpeciesType> {
	public static final Codec<Tree> CODEC = RecordCodecBuilder.create(instance -> {
		Codec<IGenome> genomeCodec = TreeChromosomes.KARYOTYPE.genomeCodec();
		return instance.group(
				genomeCodec.fieldOf("genome").forGetter(Tree::getGenome),
				genomeCodec.optionalFieldOf("mate").forGetter(Tree::getMateOptional),
				Codec.BOOL.fieldOf("analyzed").forGetter(Tree::isAnalyzed)
		).apply(instance, Tree::new);
	});

	public Tree(IGenome genome) {
		super(genome);
	}

	private Tree(IGenome genome, Optional<IGenome> mate, boolean analyzed) {
		super(genome, mate, analyzed);
	}

	@Override
	protected void savePropertiesToStack(ItemStack stack) {
		super.savePropertiesToStack(stack);
		stack.set(ArboricultureDataComponents.TREE_GENOME.type(), genome);
	}

	@Override
	public void loadPropertiesFromStack(ItemStack stack) {
		super.loadPropertiesFromStack(stack);
	}

	@Nullable
	public static Tree fromStack(ItemStack stack) {
		IGenome genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
		if (genome == null) {
			return null;
		}
		Tree tree = new Tree(genome);
		tree.loadPropertiesFromStack(stack);
		return tree;
	}
}
