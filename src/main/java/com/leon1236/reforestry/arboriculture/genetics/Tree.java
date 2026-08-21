package com.leon1236.reforestry.arboriculture.genetics;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import com.leon1236.reforestry.api.arboriculture.genetics.ITree;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.core.genetics.Individual;

public class Tree extends Individual<ITreeSpecies, ITree, TreeSpeciesType> implements ITree {
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
	public ITreeSpecies getSpecies() {
		return species;
	}

	@Override
	public ITreeSpecies getInactiveSpecies() {
		return inactiveSpecies;
	}

	@Override
	public ITree copy() {
		return super.copy();
	}

	@Override
	public IEffectData[] doEffect(IEffectData[] storedData, Level level, BlockPos pos) {
		com.leon1236.reforestry.api.arboriculture.genetics.ITreeEffect effect = genome.getActiveAllele(TreeChromosomes.EFFECT).value();
		storedData[0] = effect.doEffect(genome, storedData[0], level, pos);
		return storedData;
	}

	@Override
	public IEffectData[] doFX(IEffectData[] storedData, Level level, BlockPos pos) {
		com.leon1236.reforestry.api.arboriculture.genetics.ITreeEffect effect = genome.getActiveAllele(TreeChromosomes.EFFECT).value();
		effect.doAnimationEffect(genome, level, pos, level.getRandom());
		return storedData;
	}

	@Override
	public List<ITree> getSaplings(Level level, BlockPos pos, @Nullable GameProfile playerProfile, float modifier) {
		return List.of(copy());
	}

	@Override
	public List<ItemStack> produceStacks(Level level, BlockPos pos, int ripeningTime) {
		return genome.getActiveAllele(TreeChromosomes.FRUIT).value().getFruits(genome, level, ripeningTime);
	}

	@Override
	public boolean canStay(BlockGetter level, BlockPos pos) {
		return level.getBlockState(pos.below()).is(Blocks.DIRT) || !level.getBlockState(pos.below()).isAir();
	}

	@Override
	public boolean hasFruitLeaves() {
		return genome.getActiveAllele(TreeChromosomes.FRUIT).value().isFruitLeaf();
	}

	@Override
	public int getRequiredMaturity() {
		return genome.getActiveAllele(TreeChromosomes.MATURATION).value();
	}

	@Override
	public int getResilience() {
		return 1;
	}

	@Override
	public Feature<NoneFeatureConfiguration> getTreeGenerator(WorldGenLevel level, BlockPos pos, boolean wasBonemealed) {
		return getSpecies().getGenerator().getTreeFeature(getSpecies());
	}

	@Override
	public List<? extends IProduct> getProducts() {
		return getSpecies().getProducts();
	}

	@Override
	public List<? extends IProduct> getSpecialties() {
		return getSpecies().getSpecialties();
	}

	@Override
	protected void savePropertiesToStack(ItemStack stack) {
		super.savePropertiesToStack(stack);
		stack.set(ArboricultureDataComponents.TREE_GENOME.type(), genome);
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
